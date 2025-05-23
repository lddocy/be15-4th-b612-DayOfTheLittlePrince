<script setup>
import { ref, watch } from 'vue'

// Props 정의
const props = defineProps({
  visible: Boolean,
  date: String,
  suggestionList: {
    type: Array,
    default: () => []
  }
})

const emit = defineEmits(['close', 'addTodo'])

const internalList = ref([...props.suggestionList])

watch(() => props.suggestionList, (newList) => {
  internalList.value = [...newList]
})

const handleAdd = (content) => {
  emit('addTodo', content)
  internalList.value = internalList.value.filter(item => item.content !== content)
}
</script>

<template>
  <div v-if="visible" class="fixed inset-0 z-50 flex justify-center items-center bg-black/30 backdrop-blur-sm">
    <div class="w-[440px] bg-white/20 rounded-2xl px-6 py-4 shadow-2xl backdrop-blur-md">

      <!-- 상단 제목 & 닫기 버튼 -->
      <div class="flex justify-between items-center border-b border-white/20 pb-2 mb-4">
        <h2 class="text-lg font-bold text-white">AI 할 일 추천</h2>
        <button @click="$emit('close')" class="text-white hover:rotate-180 transition">
          <svg xmlns="http://www.w3.org/2000/svg" class="w-4 h-4" viewBox="0 0 20 20" fill="currentColor">
            <path fill-rule="evenodd" d="M10 14a1 1 0 0 1-.7-.3l-5-5a1 1 0 0 1 1.4-1.4L10 11.6l4.3-4.3a1 1 0 0 1 1.4 1.4l-5 5a1 1 0 0 1-.7.3z" clip-rule="evenodd" />
          </svg>
        </button>
      </div>

      <!-- 추천 항목 리스트 -->
      <div class="flex flex-col gap-2 max-h-[320px] overflow-y-auto">
        <div
            v-for="(item, idx) in internalList"
            :key="`${item.content}-${idx}`"
            class="flex justify-between items-center px-4 py-2 bg-dlp_card/40 hover:bg-dlp_card_hover/80 rounded-xl transition"
        >
          <span class="text-sm text-white">{{ item.content }}</span>
          <button @click="handleAdd(item.content)" class="text-lg text-white">+</button>
        </div>
      </div>
    </div>
  </div>
</template>
