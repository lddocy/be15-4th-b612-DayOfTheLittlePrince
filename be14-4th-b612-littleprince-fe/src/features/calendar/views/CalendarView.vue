<script setup>
import calendarBg from '@/assets/images/calendar-background.jpg'

</script>

<template>
  <div
      :style="{ backgroundImage: `url(${calendarBg})` }"
      class="scene-container bg-cover bg-center"
  >
    <!-- 내용 -->
  </div>
</template>

<style scoped>
.scene-container {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
}
</style>