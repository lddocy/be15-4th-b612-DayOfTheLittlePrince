import { ref } from 'vue';
import { defineStore } from 'pinia';
import { fetchMemberInfo } from '@/features/main/api.js';
import { useAuthStore } from '@/stores/auth';

export const useUserStore = defineStore('user', () => {
    const memberInfo = ref(null);

    const loadMemberInfo = async () => {
        const authStore = useAuthStore();
        const accessToken = authStore.accessToken;
        try {
            const resp = await fetchMemberInfo(accessToken);
            memberInfo.value = resp.data.data;
        } catch (err) {
            console.error('사용자 정보 가져오기 실패:', err);
        }
    }

    return {
        memberInfo,
        loadMemberInfo,
    };
});
