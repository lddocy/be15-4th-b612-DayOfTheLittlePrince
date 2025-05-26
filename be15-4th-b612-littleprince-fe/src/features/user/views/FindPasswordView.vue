<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import MainLogo from '@/features/user/components/MainLogo.vue';
import '@/assets/styles/auth-container.css';
import { findPassword } from '../api';
import { useToast } from 'vue-toastification';

const toast = useToast();
const router = useRouter();

const email = ref('');
const isLoading = ref(false);

/* api 요청을 위한 핸들러 */
const handleFindPassword = async () => {
    try {
        email.value = email.value.trim();

        if (!email.value) {
            toast.error('이메일을 입력해주세요');
            return;
        }

        isLoading.value = true;

        await findPassword(email.value);

        toast.success('비밀번호 변경 이메일이 전송되었습니다.');
        await router.replace('/login');
    } catch (e) {
        toast.error(e.response?.data?.message || '오류가 발생했습니다');
    } finally {
        isLoading.value = false;
    }
};
</script>

<template>
    <div class="findPassword-layout">
        <MainLogo />

        <div class="card-style">
            <div class="title text-center mb-8">
                <h1 class="text-dlp_title-lg font-bold">비밀번호 찾기</h1>
            </div>
            <form class="login-form flex flex-col" @submit.prevent="handleFindPassword">
                <label class="input-box-label">이메일(아이디)</label>
                <input v-model="email" type="text" class="input-box" />
                <button
                    type="submit"
                    class="auth-primary-btn hover:bg-dlp_hover active:bg-dlp_active disabled:bg-gray-400 disabled:text-white disabled:cursor-not-allowed disabled:hover:bg-gray-400"
                    :disabled="isLoading">
                    {{ isLoading ? '전송 중...' : '이메일 발송' }}
                </button>
            </form>
        </div>
    </div>
</template>

<style scoped>
.findPassword-layout {
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
