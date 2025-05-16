<script setup>
import calendarBg from '@/assets/images/calendar-background.jpg'
import Calendar from "@/features/calendar/components/Calendar.vue";
</script>

<template>
  <div
      class="w-screen h-screen bg-cover bg-center bg-no-repeat flex justify-center items-center box-border p-4 overflow-hidden"
      :style="{ backgroundImage: `url(${calendarBg})` }"
  >
    <Calendar />
  </div>
</template>
