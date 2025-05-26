<script setup>
import '@/assets/styles/auth-container.css';
import MainLogo from '@/features/user/components/MainLogo.vue';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useToast } from 'vue-toastification';
import { signup } from '../api.js';

const router = useRouter();
const toast = useToast();

const email = ref('');
const password = ref('');
const confirmPassword = ref('');

const handleSignup = async () => {
    try {
        /* 1. 공백 제거 */
        email.value = email.value.trim();
        password.value = password.value.trim();
        confirmPassword.value = confirmPassword.value.trim();

        /* 2. 입력 값 검증 */
        const email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
        if (!email_regex.test(email.value)) {
            toast.error('유효하지 않은 이메일입니다.');
            return;
        }

        if (email.value === '' || password.value === '') {
            toast.error('이메일과 패스워드를 입력해주세요.');
            return;
        }

        if (password.value !== confirmPassword.value) {
            toast.error('비밀번호가 일치하지 않습니다.');
            return;
        }

        /* 3. 입력 전송 */
        const formData = {
            email: email.value,
            password: password.value,
            confirmPassword: confirmPassword.value,
        };
        const resp = await signup(formData);

        /* 4. 성공 시 로그인 페이지로 이동 */
        toast.success('회원가입이 완료되었습니다.');
        await router.replace('/login');
    } catch (e) {
        console.log(e);
        toast.error(e.response.data.message);
    }
};
</script>

<template>
    <div class="signup-layout">
        <MainLogo />
        <div class="card-style">
            <div class="title text-center mb-8">
                <h1 class="text-dlp_title-lg font-bold">회원가입</h1>
            </div>

            <form class="login-form flex flex-col" @submit.prevent="handleSignup">
                <label class="input-box-label">이메일(아이디)</label>
                <input v-model="email" type="text" class="input-box" />
                <label class="input-box-label">비밀번호</label>
                <input v-model="password" type="password" class="input-box" />

                <label class="input-box-label">비밀번호 확인</label>
                <input v-model="confirmPassword" type="password" class="input-box" />

                <button type="submit" class="auth-primary-btn">회원가입</button>
            </form>
        </div>
    </div>
</template>

<style scoped>
.signup-layout {
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
