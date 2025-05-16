<script setup>
import { ref } from 'vue'
import TodoList from '@/features/calendar/components/TodoList.vue'
import calendarBg from '@/assets/images/calendar-background.jpg'

const toggle = ref(false)
</script>

<template>
  <div :style="{ backgroundImage: `url(${calendarBg})` }" class="scene-container relative bg-cover bg-center w-full h-screen">
    <div class="fixed top-[100px] bottom-[100px] right-[5%] w-[25%] h-[70%]
                bg-[#E8D0FF]/30 backdrop-blur-md rounded-2xl p-6 flex flex-col gap-4 text-white shadow-lg z-10">

      <!-- 날짜 + 토글 -->
      <div class="flex items-center justify-between">
        <div class="flex items-center gap-2">
          <label class="px-3 py-[6px] bg-[#C9C3E3]/40 rounded-xl  text-black border-white/10">날짜</label>
          <input type="date" class="px-3 py-[6px] bg-[#C9C3E3]/40 text-black rounded-xl  border-white/10" />
          <span v-if="toggle" class="text-sm">~</span>
          <input v-if="toggle" type="date" class="px-3 py-[6px] bg-[#C9C3E3]/40 text-black rounded-xl border-white/10" />
        </div>
        <div @click="toggle = !toggle" class="w-12 h-6 rounded-full relative cursor-pointer transition-all duration-300"
             :class="toggle ? 'bg-[#60A5FA]/60' : 'bg-gray-400'">
          <div class="absolute top-[2px] w-5 h-5 bg-white rounded-full shadow transition-all duration-300"
               :class="toggle ? 'right-[2px]' : 'left-[2px]'" />
        </div>
      </div>

      <!-- 제목 입력 -->
      <div class="flex flex-col">
        <input v-if="toggle" type="text" placeholder="제목을 입력하세요."
               class="w-30% px-3 py-2 rounded-xl bg-[#C9C3E3]/40 text-black placeholder-[#161717]/50 border-none focus:outline-none text-sm" />
      </div>

      <div class="w-full h-[1px] bg-white/40" />

      <!-- Todo 리스트 + 내부 버튼 포함 -->
      <TodoList />

      <!-- 취소/확인 버튼 -->
      <div class="flex justify-between mt-auto">
        <div class="flex gap-2 ml-auto">
          <button class="bg-[#C9C3E3]/30 hover:bg-[#A49CAC]/60 text-black px-3 py-1 rounded-xl text-sm border border-white/10 transition">취소</button>
          <button class="bg-[#C9C3E3]/30 hover:bg-[#A49CAC]/60 text-black px-3 py-1 rounded-xl text-sm border border-white/10 transition">확인</button>
        </div>
      </div>
    </div>
  </div>
</template>