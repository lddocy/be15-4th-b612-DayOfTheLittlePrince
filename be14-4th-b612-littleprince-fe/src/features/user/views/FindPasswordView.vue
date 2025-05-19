<script setup>

import {useRouter} from "vue-router";
import {ref} from "vue";
import MainLogo from "@/features/user/components/MainLogo.vue";
import '@/assets/styles/auth-container.css'

const router = useRouter();

const userId = ref('');

/* api 요청을 위한 핸들러 */
const handleFindPassword = async () => {
  try {
    /* 1. 공백 제거 */
    userId.value = userId.value.trim();

    if (!userId.value) {
      alert("이메일을 입력해주세요.");
      return;
    }

    console.log(userId.value)
    await router.replace('/login')
  } catch(e) {
    console.log(e);
  }
}

</script>

<template>
  <div class="findPassword-layout">
    <MainLogo/>

    <div class="card-style">
      <div class="title text-center mb-8">
        <h1 class="text-dlp_title-lg font-bold">비밀번호 찾기</h1>
      </div>
      <form class="login-form flex flex-col" @submit.prevent="handleFindPassword">
        <label class="input-box-label">이메일(아이디)</label>
        <input
            v-model="userId"
            type="text"
            class="input-box"
        />
        <button
            type="submit"
            class="auth-primary-btn"
        >
          이메일 발송
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
  flex-direction: column;         /* 세로 방향 정렬 */
  justify-content: flex-start;    /* 수직 중앙 정렬 */
  align-items: center;            /* 수평 중앙 정렬 */
}
</style>