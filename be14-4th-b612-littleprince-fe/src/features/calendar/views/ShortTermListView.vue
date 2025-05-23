<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Calendar from '@/features/calendar/components/Calendar.vue'
import ShortTermList from '@/features/calendar/components/ShortTermList.vue'
import {createShortTodo, deleteShortTodo, getShortList, toggleCheck} from "@/features/calendar/api.js"
import {useAuthStore} from "@/stores/auth.js";
import { useToast } from 'vue-toastification';

const toast = useToast();
const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()
const selectedDate = ref(route.params.date)

const todos = ref([])
const editable = ref({})
const isModalOpen = ref(false)

const aiSuggestions = ref([])

// 단기 투두 리스트 조회 API 호출
onMounted(async () => {
  const accessToken = authStore.accessToken
  try {
    const res = await getShortList(accessToken, selectedDate.value)
    todos.value = res.data.data.shortList.map(item => ({
      task_id: item.taskId,
      content: item.content,
      is_checked: item.isChecked
    }))
  } catch (e) {
    console.error('단기 투두 조회 실패:', e)
    toast.error('투두 리스트를 불러오지 못했습니다.')
  }
})

// 투두 항목 추가
const addTodo = () => {
    const newId = Date.now()
    todos.value.push({ task_id: newId, content: '', is_checked: 'N' })
    editable.value[newId] = true
}

// 투두 삭제
const deleteTodo = async (taskId) => {
  const accessToken = authStore.accessToken

    if (editable.value[taskId]) {
        todos.value = todos.value.filter(todo => todo.task_id !== taskId)
        delete editable.value[taskId]
    } else {
      try {
        await deleteShortTodo(accessToken, taskId)
        todos.value = todos.value.filter(todo => todo.task_id !== taskId)
      } catch (e) {
        console.error('삭제 실패:', e)
        toast.error('삭제에 실패했습니다.')
      }
    }
}

// 내용 수정
const updateTodoContent = (taskId, content) => {
    const target = todos.value.find(todo => todo.task_id === taskId)
    if (target) target.content = content
}

// 체크 상태 토글
const toggleTodoChecked = async (taskId, checked) => {
  const accessToken = authStore.accessToken

    const target = todos.value.find(todo => todo.task_id === taskId)
    if (target) {
      try {
        await toggleCheck(accessToken, taskId)
        target.is_checked = checked ? 'Y' : 'N'
      } catch (e) {
        console.error('체크 상태 토글 실패:', e)
        toast.error('체크 상태 변경 실패')
      }
    }
}

// AI 추천 항목 추가
const addSuggestedTodo = (content) => {
    const newId = Date.now()
    todos.value.push({ task_id: newId, content, is_checked: 'N' })
}

const handleConfirm = async () => {
  const validTodos = todos.value.filter(todo => todo.content.trim() !== '')
  const accessToken = authStore.accessToken

  try {
    for (const todo of validTodos) {
      if (editable.value[todo.task_id]) {
        await createShortTodo(accessToken, {
          content: todo.content,
          date: selectedDate.value
        })
      }
    }

    const res = await getShortList(accessToken, selectedDate.value)
    todos.value = res.data.data.shortList.map(item => ({
      task_id: item.taskId,
      content: item.content,
      is_checked: item.isChecked
    }))

    editable.value = {}
    toast.success('할 일이 저장되었습니다.')
  } catch (e) {
    console.error('저장 실패:', e)
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
            <div class="w-[1000px] h-[90vh] rounded-2xl p-2 shadow-lg overflow-hidden">
                <Calendar />
            </div>
            <ShortTermList
                :selected-date="selectedDate"
                :todos="todos"
                :editable="editable"
                :is-modal-open="isModalOpen"
                :ai-suggestions="aiSuggestions"
                @update-content="updateTodoContent"
                @toggle-checked="toggleTodoChecked"
                @delete-todo="deleteTodo"
                @add-todo="addTodo"
                @add-suggested-todo="addSuggestedTodo"
                @confirm="handleConfirm"
                @cancel="goBack"
                @update:isModalOpen="val => isModalOpen = val"
            />
        </div>
    </div>
</template>
