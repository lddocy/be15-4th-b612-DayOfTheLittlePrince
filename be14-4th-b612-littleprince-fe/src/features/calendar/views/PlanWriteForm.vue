<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Calendar from "@/features/calendar/components/Calendar.vue";
import TodoList from "@/features/calendar/components/TodoList.vue";
import AISuggestionModal from '@/features/calendar/components/AISuggestionModal.vue'

const route = useRoute()
const router = useRouter()
const toggle = ref(false)

// 날짜 값을 쿼리에서 받음
const startDate = ref(route.query.date || '')
const endDate = ref('')

const todos = ref([
  { task_id: 1, content: '요구사항 명세서 작성 끝내기', is_checked: 'N', project_id: null },
  { task_id: 2, content: '인프런 스프링부트 1강 듣기', is_checked: 'N', project_id: 5 },
  { task_id: 3, content: '피그마 작업 마무리하기', is_checked: 'N', project_id: null },
  { task_id: 4, content: 'ERD 작업 시작하기', is_checked: 'N', project_id: 5 },
  { task_id: 5, content: '프론트엔드 초기 세팅 끝내기', is_checked: 'Y', project_id: null }
])

const editable = ref({})

// AI 모달
const isModalOpen = ref(false)
const aiSuggestions = ref([
  { content: 'GitHub README 작성하기' },
  { content: 'API 명세 정리하기' },
  { content: 'AI 회의록 작성' },
  { content: '추천4' },
  { content: '추천5' }
])

const handleConfirm = () => {
  todos.value = todos.value.filter(todo => todo.content.trim() !== '')
  editable.value = {}
  alert('할 일이 등록되었습니다.')
}

// AI 추천 항목 추가 핸들러
const addSuggestedTodo = (content) => {
  const newId = Date.now()
  todos.value.push({
    task_id: newId,
    content,
    is_checked: 'N',
    project_id: null
  })
}
</script>

<template>
  <div class="w-screen h-screen flex justify-center items-center">
    <div class="flex gap-12 w-[1240px]">
      <div class="w-[1000px] h-[90vh] rounded-2xl p-2 shadow-lg overflow-hidden">
        <Calendar />
      </div>

      <!-- 플랜 리스트 -->
      <div class="w-[500px] h-[75vh] bg-[#E8D0FF]/30 rounded-2xl p-6 flex flex-col text-black gap-4 shadow-lg overflow-y-auto self-center">
        <!-- 날짜 -->
        <div class="flex items-center justify-between">
          <div class="flex items-center gap-2">
            <label class="px-3 py-[3px] bg-[#C9C3E3]/40 rounded-xl text-[12px] text-black border-white/10">날짜</label>
            <input v-model="startDate" type="date" class="px-3 py-[3px] bg-[#C9C3E3]/40 text-[12px] text-black rounded-xl border-white/10" />
            <span v-if="toggle" class="text-[20px]">~</span>
            <input v-if="toggle" v-model="endDate" type="date" class="px-3 py-[3px] bg-[#C9C3E3]/40 text-[12px] text-black rounded-xl border-white/10" />
          </div>
          <div @click="toggle = !toggle" class="w-10 h-6 rounded-full relative cursor-pointer transition-all duration-300"
               :class="toggle ? 'bg-[#60A5FA]/60' : 'bg-gray-400'">
            <div class="absolute top-[2px] w-5 h-5 bg-white rounded-full shadow transition-all duration-300"
                 :class="toggle ? 'right-[2px]' : 'left-[2px]'" />
          </div>
        </div>

        <!-- 제목 입력 -->
        <div class="flex flex-col">
          <input v-if="toggle" type="text" placeholder="제목을 입력하세요."
                 class="w-full px-3 py-2 rounded-xl bg-[#C9C3E3]/40 text-black placeholder-[#161717]/50 border-none focus:outline-none text-sm" />
        </div>

        <div class="w-full h-[1px] bg-white/40" />

        <!-- 투두 리스트 -->
        <TodoList :todos="todos"
                  :editable-map="editable"
                  @request-ai-modal="isModalOpen = true"
                  @add-suggested-todo="addSuggestedTodo"
        />

        <!-- 하단 버튼 -->
        <div class="flex justify-between mt-auto">
          <div class="flex gap-2 ml-auto">
            <button
                @click="router.push('../../calendar')"
                class="bg-[#C9C3E3]/30 hover:bg-[#A49CAC]/60 text-black px-3 py-1 rounded-xl text-sm border border-white/10 transition">취소</button>
            <button
                @click="handleConfirm"
                class="bg-[#C9C3E3]/30 hover:bg-[#A49CAC]/60 text-black px-3 py-1 rounded-xl text-sm border border-white/10 transition">확인</button>
          </div>
        </div>
      </div>
    </div>
    <AISuggestionModal
        :visible="isModalOpen"
        :date="startDate"
        :suggestion-list="aiSuggestions"
        @close="isModalOpen = false"
        @addTodo="addSuggestedTodo"
    />
  </div>
</template>
