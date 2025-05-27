<script setup>
import { useRouter } from 'vue-router';
import { useAuthStore } from '/src/stores/auth.js';
import { ref } from 'vue';
import MainLogo from '@/features/user/components/MainLogo.vue';
import FamousQuotesModal from '@/components/layout/FamousQuotesModal.vue';
import '@/assets/styles/auth-container.css';
import { useToast } from 'vue-toastification';
import { login } from '../api.js';
import { useUserStore } from '@/stores/user.js';
import { getFamousQuotes } from '@/features/main/api.js';

const router = useRouter();
const authStore = useAuthStore();
const userStore = useUserStore();
const toast = useToast();

const showModal = ref(false);
const quote = ref('');

const email = ref('');
const password = ref('');

// api 요청을 위한 핸들러
const handleLogin = async () => {
    try {
        /* 1. 공백 제거 */
        email.value = email.value.trim();
        password.value = password.value.trim();
        if (!email.value || !password.value) {
            toast.error('아이디와 비밀번호를 입력해주세요.');
            return;
        }

        /* 입력 전송 */
        const resp = await login({ email: email.value, password: password.value });
        const at = resp.data.data.accessToken;
        authStore.setAccessToken(at);
        console.log(email.value + ' ' + password.value);

        const { data } = await getFamousQuotes(at); // token으로 명언 불러오기
        quote.value = data?.data || '오늘의 명언을 불러오지 못했습니다.';
        showModal.value = true;

        setTimeout(() => {
            showModal.value = false;
            router.replace('/');
        }, 3000);
    } catch (e) {
        toast.error(e.response.data.message);
    }
};
</script>

<template>
    <div class="login-layout">
        <MainLogo />

        <div class="card-style">
            <div class="title text-center mb-8">
                <h1 class="text-dlp_title-lg font-bold">로그인</h1>
            </div>

            <form class="login-form flex flex-col" @submit.prevent="handleLogin">
                <label class="input-box-label">이메일</label>
                <input v-model="email" type="text" class="input-box" />
                <label class="input-box-label">비밀번호</label>
                <input v-model="password" type="password" class="input-box" />

                <button type="submit" class="auth-primary-btn">로그인</button>
            </form>
            <div class="flex justify-between mt-2 text-[1rem] text-black">
                <RouterLink to="findPassword">비밀번호 찾기</RouterLink>
                <RouterLink to="signup">회원가입</RouterLink>
            </div>
        </div>
        <FamousQuotesModal v-if="showModal" :title="quote" />
    </div>
</template>

<style scoped>
.login-layout {
    position: relative;
    width: 100vw;
    height: 100vh;
    overflow: hidden;

    display: flex;
    flex-direction: column; /* 세로 방향 정렬 */
    justify-content: flex-start; /* 수직 중앙 정렬 */
    align-items: center; /* 수평 중앙 정렬 */
}
</style>
