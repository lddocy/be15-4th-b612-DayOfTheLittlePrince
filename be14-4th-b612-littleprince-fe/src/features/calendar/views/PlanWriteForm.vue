<script setup>
import { ref } from 'vue'
import TodoList from "@/features/calendar/components/TodoList.vue";
import calendarBg from "@/assets/images/calendar-background.jpg";

const toggle = ref(false)
</script>

<template>
  <div
      :style="{ backgroundImage: `url(${calendarBg})` }"
      class="scene-container relative bg-cover bg-center w-full h-screen"
  >
    <div
        class="fixed top-[100px] bottom-[100px] right-[5%] w-[25%] h-[70%]
             bg-[#E8D0FF]/30 backdrop-blur-md rounded-2xl p-6 flex flex-col gap-4 text-white shadow-lg z-10"
    >
      <!-- 날짜 + 토글 -->
      <div class="flex items-center justify-between">
        <div class="flex items-center gap-2">
          <label class="px-3 py-[6px] bg-[#C9C3E3]/40 rounded-xl text-sm text-black">날짜</label>

          <!-- 시작 날짜 -->
          <input
              type="date"
              class="px-3 py-[6px] bg-[#C9C3E3]/40 text-black placeholder-white/60 rounded-xl text-sm border-none focus:outline-none appearance-none"
          />
          <span
              v-if="toggle"
              class="text-sm">~</span>

          <!-- 종료 날짜 -->
          <input
              v-if="toggle"
              type="date"
              class="px-3 py-[6px] bg-[#C9C3E3]/40 text-black placeholder-white/60 rounded-xl text-sm border-none focus:outline-none appearance-none"
          />
        </div>
        <!-- 토글 -->
        <div @click="toggle = !toggle" class="w-12 h-6 rounded-full relative cursor-pointer transition-all duration-300"
             :class="toggle ? 'bg-[#60A5FA]/60' : 'bg-gray-400'">
          <div
              class="absolute top-[2px] w-5 h-5 bg-white rounded-full shadow transition-all duration-300"
              :class="toggle ? 'right-[2px]' : 'left-[2px]'"
          ></div>
        </div>
      </div>
      <!-- 제목 입력 -->
      <div class="flex flex-col">
        <input
            v-if="toggle"
            type="text"
            placeholder="제목을 입력하세요."
            class="w-30% px-3 py-2 rounded-xl bg-[#C9C3E3]/40 text-black placeholder-[#161717]/50 border-none focus:outline-none text-sm"
        />
      </div>
      <div class="w-full h-[1px] bg-white/40"></div>
      <!-- Todo 리스트 -->
      <TodoList />
      <!-- 버튼 영역 -->

        <div class="flex gap-2">
          <button class="bg-[#C9C3E3]/30 hover:bg-[#A49CAC]/60 text-black px-3 py-1 rounded-xl text-sm
         border border-white/10 transition">+</button>
        </div>
        <div class="flex gap-2">
          <button
              class="bg-[#C9C3E3]/30 hover:bg-[#A49CAC]/60 text-black px-1 py-1 rounded-xl text-sm
         border border-white/10 transition"
          >AI 생성하기</button>
        </div>
      <div class="flex justify-between mt-auto">
        <div class="flex gap-2 ml-auto">
          <button class="bg-[#C9C3E3]/30 hover:bg-[#A49CAC]/60 text-black px-3 py-1 rounded-xl text-sm
         border border-white/10 transition">취소</button>
          <button class="bg-[#C9C3E3]/30 hover:bg-[#A49CAC]/60 text-black px-3 py-1 rounded-xl text-sm
         border border-white/10 transition">확인</button>
        </div>
      </div>
    </div>
  </div>
</template>

