LongTermListView
<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Calendar from '@/features/calendar/components/Calendar.vue'
import AISuggestionModal from '@/features/calendar/components/AISuggestionModal.vue'
import { getAiList } from '@/features/calendar/api.js'
import { useAuthStore } from '@/stores/auth'
import { useToast } from 'vue-toastification'

const route = useRoute()
const router = useRouter()
const toast = useToast()
const authStore = useAuthStore()

const selectedDate = ref(route.params.date)
const projectId = route.params.projectId
const projectTitle = ref('')

const todos = ref([])
const editable = ref({})

const isModalOpen = ref(false)
const aiSuggestions = ref([])
const isLoadingAi = ref(false)

const deleteTodo = (taskId) => {
  if (editable.value[taskId]) {
    todos.value = todos.value.filter(todo => todo.task_id !== taskId)
    delete editable.value[taskId]
  }
}

const addTodo = () => {
  const newId = Date.now()
  todos.value.push({ task_id: newId, content: '', is_checked: 'N' })
  editable.value[newId] = true
}

const addSuggestedTodo = (content) => {
  const newId = Date.now()
  todos.value.push({ task_id: newId, content, is_checked: 'N' })
  editable.value[newId] = true
}

const goBack = () => {
  router.push(`/calendar/${selectedDate.value}`)
}

const handleConfirm = () => {
  todos.value = todos.value.filter(todo => todo.content.trim() !== '')
  editable.value = {}
}

const fetchAiSuggestions = async () => {
  isLoadingAi.value = true
  try {
    const res = await getAiList(authStore.accessToken)
    aiSuggestions.value = res.data.data.generatePlanList.map(content => ({ content }))
    isModalOpen.value = true
  } catch (err) {
    toast.error('AI 추천을 불러오는 데 실패했습니다.')
  } finally {
    isLoadingAi.value = false
  }
}
</script>

<template>
  <div class="w-screen h-screen flex justify-center items-center">
    <div class="flex gap-12 w-[1240px]">

      <!-- 캘린더 -->
      <div class="w-[1000px] h-[90vh] rounded-2xl p-2 shadow-lg overflow-hidden">
        <Calendar />
      </div>

      <!-- 장기 목표 리스트 패널 -->
      <div class="w-[500px] h-[75vh] bg-[#E8D0FF]/30 rounded-2xl p-6 flex flex-col text-black shadow-lg overflow-y-auto self-center">

        <!-- 목표 제목 -->
        <h2 class="text-xl font-bold mb-4">{{ projectTitle }}</h2>

        <div class="w-full h-[1px] bg-white/20 mb-4" />

        <!-- 날짜 -->
        <div class="text-dlp_lavender font-semibold text-xl mb-5">
          {{ new Date(selectedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: 'long', day: 'numeric' }) }}
        </div>

        <!-- 투두 리스트 -->
        <div class="flex flex-col gap-2 max-h-[300px] overflow-y-auto pr-1 w-[90%] ml-4">
          <div
              v-for="todo in todos"
              :key="todo.task_id"
              class="group flex items-center justify-between px-3 py-2 rounded-xl bg-dlp_card/40 hover:bg-dlp_card_hover/80 h-[40px]"
          >
            <div class="flex items-center gap-2 w-full">
              <input
                  type="checkbox"
                  :checked="todo.is_checked === 'Y'"
                  @change="todo.is_checked = $event.target.checked ? 'Y' : 'N'"
                  class="w-4 h-4 rounded bg-white/20 border-white/30
                         checked:bg-[#60A5FA] checked:border-[#60A5FA]
                         appearance-none relative cursor-pointer"
              >
              <input
                  v-model="todo.content"
                  :readonly="!editable[todo.task_id]"
                  :class="[ 'bg-transparent text-sm text-[#161717] outline-none w-full',
                            todo.is_checked === 'Y' ? 'line-through opacity-60' : '',
                            !editable[todo.task_id] ? 'cursor-default' : '' ]"
                  placeholder="할 일을 입력하세요"
              />
            </div>
            <button
                @click="deleteTodo(todo.task_id)"
                class="hidden group-hover:block transition bg-transparent p-0 border-none shadow-none"
            >
              <img src="@/assets/icons/trash.png" alt="삭제" class="w-4 h-4 object-contain" />
            </button>
          </div>
        </div>

        <!-- 버튼 -->
        <div class="flex gap-2 mt-2 ml-4">
          <button @click="addTodo"
                  class="w-8 h-8 flex items-center justify-center text-lg font-bold
                 bg-[#C9C3E3]/40 hover:bg-[#A49CAC]/60
                 text-black rounded-full border border-white/10 transition">
            +
          </button>
          <button @click="fetchAiSuggestions"
                  class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-2 py-1 rounded-xl text-sm border border-white/10 transition">
            <span v-if="isLoadingAi">생성 중...</span>
            <span v-else>AI 생성하기</span>
          </button>
        </div>

        <div class="flex justify-end mt-auto gap-2">
          <button @click="goBack" class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-4 py-1 rounded-xl text-sm border border-white/10 transition">취소</button>
          <button @click="handleConfirm" class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-4 py-1 rounded-xl text-sm border border-white/10 transition">확인</button>
        </div>
      </div>
    </div>

    <!-- AI 추천 모달 -->
    <AISuggestionModal
        :visible="isModalOpen"
        :date="selectedDate"
        :suggestion-list="aiSuggestions"
        @close="isModalOpen = false"
        @addTodo="addSuggestedTodo"
    />
  </div>
</template>

<style>
input[type="checkbox"]:checked::after {
  content: '';
  position: absolute;
  left: 50%;
  top: 50%;
  width: 10px;
  height: 5px;
  border-left: 3px solid white;
  border-bottom: 3px solid white;
  transform: translate(-50%, -60%) rotate(-45deg);
}
</style>