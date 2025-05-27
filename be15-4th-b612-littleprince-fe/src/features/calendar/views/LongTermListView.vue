<script setup>
import {ref, onMounted} from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Calendar from '@/features/calendar/components/Calendar.vue'
import AISuggestionModal from '@/features/calendar/components/AISuggestionModal.vue'
import {
  createProjectTasks,
  deleteProjectTask,
  toggleProjectTaskCheck,
  getLongDetail,
  getLongList,
  getAiList
} from '@/features/calendar/api.js'
import { useAuthStore } from '@/stores/auth.js'
import {useToast} from "vue-toastification";

const props = defineProps({
  todos: Array,
  editableMap: Object
})

const authStore = useAuthStore()
const route = useRoute()
const router = useRouter()

const selectedDate = ref(route.params.date)
const projectId = route.params.projectId
const projectTitle = ref('')

const toast = useToast()

const isModalOpen = ref(false)
const aiSuggestions = ref([])
const isLoadingAi = ref(false)

const handleSuggestAdd = (content) => {
  const newId = Date.now()
  todos.value.push({ task_id: newId, content, is_checked: 'N' })
  editable.value[newId] = true
}

const todos = ref([])
const editable = ref({})

const fetchAiSuggestions = async () => {
  isLoadingAi.value = true
  try {
    const res = await getAiList(authStore.accessToken)
    aiSuggestions.value = res.data.data.generatePlanList.map(content => ({ content }))
    isModalOpen.value = true
  } catch (err) {
    toast.error('10일 이내에 플랜 5개 이상을 작성하셔야합니다.')
  } finally {
    isLoadingAi.value = false
  }
}

onMounted(async () => {
  const accessToken = authStore.accessToken
  try {
    // 1. 장기 프로젝트 상세 조회
    const detailRes = await getLongDetail(accessToken, selectedDate.value, projectId)
    todos.value = detailRes.data.data.detailDTOS.map(item => ({
      task_id: item.taskId,
      content: item.content,
      is_checked: item.isChecked
    }))

    // 2. 프로젝트 제목 불러오기
    const listRes = await getLongList(accessToken, selectedDate.value)
    const project = listRes.data.data.longListDTOS.find(p => String(p.projectId) === projectId)
    if (project) projectTitle.value = project.title
  } catch (e) {
    console.error('장기 체크리스트 또는 제목 조회 실패:', e)
    toast.error('장기 프로젝트 정보를 불러오지 못했습니다.')
  }
})

const deleteTodo = async (taskId) => {
  const accessToken = authStore.accessToken
  try {
    await deleteProjectTask(accessToken, taskId)
    todos.value = todos.value.filter(todo => todo.task_id !== taskId)
  } catch (e) {
    console.error('삭제 실패:', e)
    toast.error('삭제 실패')
  }
}

const toggleCheck = async (taskId, checked) => {
  const accessToken = authStore.accessToken
  try {
    await toggleProjectTaskCheck(accessToken, taskId)
    const target = todos.value.find(todo => todo.task_id === taskId)
    if (target) target.is_checked = checked ? 'Y' : 'N'
  } catch (e) {
    console.error('체크 실패:', e)
    toast.error('체크 상태 변경 실패')
  }
}

const addTodo = () => {
  const newId = Date.now()
  todos.value.push({
    task_id: newId,
    content: '',
    is_checked: 'N',
    project_id: null
  })
  editable.value[newId] = true

}

const handleConfirm = async () => {
  const accessToken = authStore.accessToken
  const newTodos = todos.value.filter(todo => editable.value[todo.task_id])

  if (newTodos.length === 0) {
    toast.error('저장할 새로운 할 일이 없습니다.')
    return
  }

  try {
    const payload = {
      tasks: newTodos.map(todo => ({
        content: todo.content,
        date: selectedDate.value
      }))
    }
    await createProjectTasks(accessToken, projectId, payload)
    editable.value = {}
    toast.success('저장 성공')
  } catch (e) {
    toast.error('저장에 실패했습니다.')
  }
}

const goBack = () => {
  router.push(`/calendar/${selectedDate.value}`)
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
                  @change="toggleCheck(todo.task_id, $event.target.checked)"
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
          <button
              @click="fetchAiSuggestions"
              :disabled="isLoadingAi"
              class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-2 py-1 rounded-xl text-sm border border-white/10 transition disabled:opacity-50 disabled:cursor-not-allowed"
          >
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
        :date="props.selectedDate"
        :suggestion-list="aiSuggestions"
        @close="isModalOpen = false"
        @addTodo="handleSuggestAdd"
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