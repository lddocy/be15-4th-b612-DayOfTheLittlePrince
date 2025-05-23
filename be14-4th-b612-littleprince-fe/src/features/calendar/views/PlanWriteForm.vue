<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Calendar from "@/features/calendar/components/Calendar.vue"
import TodoList from "@/features/calendar/components/TodoList.vue"
import AISuggestionModal from '@/features/calendar/components/AISuggestionModal.vue'
import { useToast } from 'vue-toastification'


import { useAuthStore } from '@/stores/auth'
import { createShortTodo, createLongTodo, createProjectTasks } from '@/features/calendar/api'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()
const toast = useToast();

const toggle = ref(false)

const startDate = ref(route.query.date || '')
const endDate = ref('')
const title = ref('')

const todos = ref([])

const editable = ref({})
const isModalOpen = ref(false)
const aiSuggestions = ref([])

const handleConfirm = async () => {
  todos.value = todos.value.filter(todo => todo.content.trim() !== '')
  editable.value = {}

  if (toggle.value) {
    // 장기 플랜
    if (!startDate.value || !endDate.value || !title.value.trim()) {
      toast.error('제목, 시작일, 종료일을 모두 입력해주세요.')
      return
    }

    try {
      // 1. 프로젝트 생성
      const res = await createLongTodo(authStore.accessToken, {
        title: title.value.trim() ,
        startDate: startDate.value,
        endDate: endDate.value,
      })

      const projectId = res.data.data

      if (!projectId) {
        toast.error("장기 플랜이 등록 실패")
      }

      // 2. 프로젝트에 연결된 체크리스트 생성
      const tasks = todos.value.map(todo => ({
        content: todo.content,
        date: todo.date || startDate.value, // 날짜 지정 없으면 시작일로
      }))

      await createProjectTasks(authStore.accessToken, projectId, { tasks })

      toast.success("장기 플랜이 등록되었습니다.")
      return router.push(`/calendar/${startDate.value}/project/${projectId}`)

    } catch (err) {
      console.error(err)
      toast.error('장기 플랜 등록 중 오류가 발생했습니다.')
    }

  } else {
    // 단기 플랜
    if (!startDate.value) {
      toast.error('날짜를 선택해주세요.')
      return
    }

    try {
      for (const todo of todos.value) {
        await createShortTodo(authStore.accessToken, {
          content: todo.content,
          date: startDate.value,
        })
      }

      toast.success('단기 플랜이 등록되었습니다.')
      router.push(`/calendar/${startDate.value}/todo`)

    } catch (err) {
      console.error(err)
      toast.error('단기 플랜 등록 중 오류가 발생했습니다.')
    }
  }
}

const addSuggestedTodo = (content) => {
  const newId = Date.now()
  todos.value.push({
    task_id: newId,
    content,
    is_checked: 'N',
    project_id: null
  })
}

const backRouter = () => {
  router.push(`/calendar/${route.query.date}`)
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
        <input v-if="toggle"
               v-model="title"
               type="text"
               placeholder="제목을 입력하세요."
               class="w-full px-3 py-2 rounded-xl bg-[#C9C3E3]/40 text-black placeholder-[#161717]/50 border-none focus:outline-none text-sm" />
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
                @click="backRouter"
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
