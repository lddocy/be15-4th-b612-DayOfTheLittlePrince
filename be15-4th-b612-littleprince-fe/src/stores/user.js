import { ref } from 'vue';
import { defineStore } from 'pinia';
import { fetchMemberInfo } from '@/features/main/api.js';
import { useAuthStore } from '@/stores/auth';


export const useUserStore = defineStore('user', () => {
    const memberInfo = ref(null);
    const selectedTitle = ref(''); // 현재 선택된 칭호

    const loadMemberInfo = async () => {
        const authStore = useAuthStore();
        const accessToken = authStore.accessToken;
        try {
            const resp = await fetchMemberInfo(accessToken);
            memberInfo.value = resp.data.data;
            selectedTitle.value = resp.data.data.currentTitle || ''; // 서버에 저장된 칭호 초기값 반영
        } catch (err) {
            console.error('사용자 정보 가져오기 실패:', err);
        }
    };

    // 칭호 선택 시 호출할 함수
    const setTitle = (title) => {
        selectedTitle.value = title;
        // 필요 시 API 호출로 서버에 반영하는 로직도 여기에 추가 가능
        // await updateTitle(title)
    };

    return {
        memberInfo,
        selectedTitle,
        loadMemberInfo,
        setTitle,
    };
});
