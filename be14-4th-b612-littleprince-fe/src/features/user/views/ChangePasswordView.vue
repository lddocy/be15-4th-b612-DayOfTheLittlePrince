<script setup>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import MainLogo from '@/features/user/components/MainLogo.vue';
import '@/assets/styles/auth-container.css';
import { useToast } from 'vue-toastification';
import { changePassword } from '../api';

const router = useRouter();
const route = useRoute();
const toast = useToast();

const uuid = ref(null);
const newPassword = ref('');
const confirmNewPassword = ref('');

/* api 요청을 위한 핸들러 */
const handleChangePassword = async () => {
    try {
        /* 1. 공백 제거 */
        newPassword.value = newPassword.value.trim();
        confirmNewPassword.value = confirmNewPassword.value.trim();

        if (!newPassword.value || !confirmNewPassword.value) {
            toast.error('비밀번호를 입력해주세요.');
            return;
        }

        if (newPassword.value !== confirmNewPassword.value) {
            toast.error('비밀번호가 일치하지 않습니다.');
            return;
        }

        await changePassword({
            uuid: route.query.uuid,
            password: newPassword.value,
            confirmPassword: confirmNewPassword.value,
        });

        toast.success('비밀번호가 성공적으로 변경되었습니다.');
        await router.replace('/login');
    } catch (e) {
        toast.error(e.response.data.message);
    }
};
</script>

<template>
    <div class="changePassword-layout">
        <MainLogo />

        <div class="card-style">
            <div class="title text-center mb-8">
                <h1 class="text-dlp_title-lg font-bold">비밀번호 변경</h1>
            </div>

            <form class="login-form flex flex-col" @submit.prevent="handleChangePassword">
                <label class="input-box-label">비밀번호</label>
                <input v-model="newPassword" type="password" class="input-box" />

                <label class="input-box-label">비밀번호 확인</label>
                <input v-model="confirmNewPassword" type="password" class="input-box" />

                <button type="submit" class="auth-primary-btn">비밀번호 변경</button>
            </form>
        </div>
    </div>
</template>

<style scoped>
.changePassword-layout {
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
