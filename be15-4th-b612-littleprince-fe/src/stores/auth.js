import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import router from '@/router';

export const useAuthStore = defineStore('auth', () => {
    const accessToken = ref(null);
    const memberId = ref(null);
    const expirationTime = ref(null);

    const isAuthenticated = computed(() => {
        return !!accessToken.value && Date.now() < (expirationTime.value || 0);
    });

    function setAccessToken(token) {
        accessToken.value = token;
        try {
            const payload = JSON.parse(atob(token.split('.')[1]));
            memberId.value = payload.memberId;
            expirationTime.value = payload.exp * 1000;
        } catch (e) {
            console.log('엑세스 토큰 파싱 에러', e);
            accessToken.value = null;
            memberId.value = null;
            expirationTime.value = null;
        }
    }

    function clearAccessToken() {
        accessToken.value = null;
        memberId.value = null;
        expirationTime.value = null;
    }

    function logout() {
        clearAccessToken();
        router.push({ name: 'login' });
    }

    return {
        memberId,
        expirationTime,
        accessToken,
        isAuthenticated,
        setAccessToken,
        clearAccessToken,
        logout,
    };
});

router.beforeEach((to) => {
    const authStore = useAuthStore();
    const nonAuthRequirePages = ['login', 'findPassword', 'signup', 'changePassword'];

    const isNonAuthPage = nonAuthRequirePages.includes(to.name);

    // 인증이 필요한 페이지인데 인증되지 않은 경우
    if (!isNonAuthPage && !authStore.isAuthenticated) {
        return { name: 'login' };
    }

    // 인증된 사용자가 로그인, 회원가입 등의 비인증 페이지로 이동 시도
    if (isNonAuthPage && authStore.isAuthenticated) {
        return { name: 'main' };
    }
});
