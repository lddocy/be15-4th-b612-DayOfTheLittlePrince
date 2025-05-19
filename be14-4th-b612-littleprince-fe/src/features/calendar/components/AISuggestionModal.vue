<script setup>
defineProps({
  visible: Boolean,               // 모달 열림 여부
  date: String,                   // 선택된 날짜
  suggestionList: Array           // 추천할 투두 배열
})

defineEmits(['close', 'addTodo']) // 닫기, 항목 추가
</script>

<template>
  <div v-if="visible" class="fixed inset-0 bg-black/30 backdrop-blur-sm flex justify-center items-center z-50">
    <div class="bg-white/90 w-[400px] rounded-2xl p-6 text-black shadow-lg">
      <div class="flex justify-between items-center mb-4">
        <h2 class="text-lg font-semibold">AI 할 일 추천</h2>
        <button @click="$emit('close')" class="text-xl">⌄</button>
      </div>

      <div class="flex flex-col gap-2">
        <div v-for="(item, index) in suggestionList" :key="index"
             class="flex items-center justify-between px-3 py-2 rounded-xl bg-dlp_card/40 hover:bg-dlp_card_hover/80 transition">
          <span class="text-sm">{{ item.content }}</span>
          <button @click="$emit('addTodo', item.content)" class="text-lg">+</button>
        </div>
      </div>
    </div>
  </div>
</template>