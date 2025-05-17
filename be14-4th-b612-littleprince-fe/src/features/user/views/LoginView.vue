<script setup>

import {useRouter} from "vue-router";
import {ref} from "vue";
import MainLogo from "@/features/user/components/MainLogo.vue";
import '@/assets/styles/auth-container.css'

const router = useRouter();


const userId = ref('');
const password = ref('');

// api 요청을 위한 핸들러
const handleLogin = async () => {
  try {
    /* 1. 공백 제거 */
    userId.value = userId.value.trim();
    password.value = password.value.trim();
    if(!userId.value || !password.value){
      alert("아이디와 비밀번호를 입력해주세요.")
      return;
    }

    /* 입력 전송*/
    // const resp = await loginUser({userId: userId.value, password: password.value})
    console.log(userId.value + " " +  password.value)
    await router.replace('/')
  } catch (e) {
    console.log(e)
  }
}

</script>

<template>
  <div class="login-layout">
    <MainLogo/>

    <div class="card-style w-[500px]">
      <div class="title text-center mb-8">
        <h1 class="text-dlp_title-lg font-bold">로그인</h1>
      </div>

      <form class="login-form flex flex-col" @submit.prevent="handleLogin">
        <label class="input-box-label">이메일</label>
        <input
            v-model="userId"
            type="text"
            class="input-box"
        />
        <label class="input-box-label">비밀번호</label>
        <input
            v-model="password"
            type="password"
            class="input-box"
        />

        <button
            type="submit"
            class="auth-primary-btn"
        >
          로그인
        </button>
      </form>
      <div class="flex justify-between mt-2 text-[1rem] text-black ">
        <RouterLink to="findPassword">비밀번호 찾기</RouterLink>
        <RouterLink to="signup">회원가입</RouterLink>
      </div>
    </div>
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