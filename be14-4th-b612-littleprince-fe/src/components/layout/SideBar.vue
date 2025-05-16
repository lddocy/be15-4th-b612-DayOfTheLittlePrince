<script setup>
import {useRouter} from "vue-router";

const router = useRouter();

// const handleLogout = async () => {
//   try {
//     const accessToken = authStore.accessToken;
//     await logoutUser(accessToken);
//     authStore.clearAuth();
//     console.log('로그아웃이 완료되었습니다.');
//     router.replace('/');
//   } catch (e) {
//     console.log('로그아웃 실패 : ', e);
//   }
// };
function handleLogout() {
  console.log("로그아웃 완료");
}

function navigate(target) {
  switch (target) {
    case 'main':
      router.push('/');
      console.log('메인화면으로~');
      break;
    case 'calendar':
      router.push('/calendar');
      break;
  }
}

</script>

<template>
  <div class="sidebar-overlay flex flex-col justify-between items-center h-screen bg-white/20 py-10 w-[140px] lg:w-[100px] md:w-[70px]">
      <div class="flex flex-col items-center space-y-10">
        <!-- 로고 -->
        <div
            class="flex flex-col items-center"
            @click="navigate('main')"
        >
          <img src="@/assets/icons/mini-logo.png" class="w-20 h-20 md:w-24 md:h-24 lg:w-32 lg:h-32"/>
        </div>
      <div class="flex flex-col items-center space-y-12">
        <!-- 마이페이지 -->
        <div
            class="bg-white/20 p-1 rounded-2xl hover:bg-dlp_purple/20 transition w-12 h-12 md:w-16 md:h-16 lg:w-20 lg:h-20"
        >
          <img src="@/assets/icons/mypage.png" alt="mypage" class="w-12 h-12 md:w-16 md:h-16 lg:w-20 lg:h-20" />
        </div>
        <!-- 캘린더 -->
        <div
            class="bg-white/20 p-1 rounded-2xl hover:bg-dlp_purple/20 transition w-12 h-12 md:w-16 md:h-16 lg:w-20 lg:h-20"
            @click="navigate('calendar')"
        >
          <img src="@/assets/icons/calendar.png" alt="calendar" class="w-12 h-12 md:w-16 md:h-16 lg:w-20 lg:h-20" />
        </div>
        <!-- 캡처모드 -->
        <div class="bg-white/20 p-1 rounded-2xl hover:bg-dlp_purple/20 transition w-12 h-12 md:w-16 md:h-16 lg:w-20 lg:h-20">
          <img src="@/assets/icons/capture.png" alt="capture" class="w-12 h-12 md:w-16 md:h-16 lg:w-20 lg:h-20" />
        </div>
        <!-- 로그아웃 -->
        <div
            class="bg-white/20 p-1 rounded-2xl hover:bg-dlp_pinkPurple/20 transition w-12 h-12 md:w-16 md:h-16 lg:w-20 lg:h-20"
            @click="handleLogout"
        >

          <img src="@/assets/icons/logout.png" alt="logout" class="w-12 h-12 md:w-16 md:h-16 lg:w-20 lg:h-20" />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.sidebar-overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 140px;
    height: 100vh;
    z-index: 10;
    background-color: rgba(255, 255, 255, 0.1);
}

/* 브라우저 너비 1024px 이하일 때 */
@media (max-width: 1024px) {
  .sidebar-overlay {
    width: 100px;
  }
}

/* 브라우저 너비 768px 이하일 때 */
@media (max-width: 768px) {
  .sidebar-overlay {
    width: 70px;
  }
}
</style>

