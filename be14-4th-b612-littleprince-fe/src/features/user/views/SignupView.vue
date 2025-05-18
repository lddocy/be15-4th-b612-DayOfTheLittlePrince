<script setup>

import MainLogo from "@/features/user/components/MainLogo.vue";
import {ref} from "vue";


const email = ref('');
const password = ref('');
const confirmPassword = ref('');

const handleSignup = async () => {
  try {
    /* 1. 공백 제거*/

    /* 2. 입력 값 검증 */

    /* 3. 입력 전송 */


    /* 4. 성공 시 로그인 페이지로 이동*/

  }catch(e){
    console.log(e)
  }
}
</script>

<template>
  <div class="signup-layout">
    <MainLogo/>
    <div class="card-style w-[500px]">
      <div class="title text-center mb-8">
        <h1 class="text-dlp_title-lg font-bold">회원가입</h1>
      </div>

      <form class="login-form flex flex-col" @submit.prevent="handleSignup">
        <label class="input-box-label">이메일(아이디)</label>
        <input
            v-model="email"
            type="text"
            class="input-box"
        />
        <label class="input-box-label">비밀번호</label>
        <input
            v-model="password"
            type="password"
            class="input-box"
        />

        <label class="input-box-label">비밀번호 확인</label>
        <input
            v-model="confirmPassword"
            type="password"
            class="input-box"
        />

        <button
            type="submit"
            class="auth-primary-btn"
        >
          회원가입
        </button>
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