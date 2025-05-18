<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Calendar from '@/features/calendar/components/Calendar.vue'

const route = useRoute()
const router = useRouter()
const selectedDate = ref(route.params.date) // yyyy-mm-dd
const dateObj = computed(() => new Date(selectedDate.value))

const longTermTodos = ref([
  { id: 1, title: '바디 프로필', date: '2025-05-14' },
  { id: 2, title: '어린왕자 프로젝트', date: '2025-05-14' },
  { id: 3, title: '사이드 프로젝트', date: '2025-05-16' }
])

// 해당 날짜에 맞는 장기 프로젝트 필터링
const filteredProjects = computed(() =>
    longTermTodos.value.filter(todo => todo.date === selectedDate.value)
)

const goToDailyTodos = () => {
  router.push({
    name: 'PlanWriteForm',
    query: { date: selectedDate.value, type: 'short' }
  })
}

const goToProject = (projectId) => {
  alert(`장기 프로젝트 ${projectId} 상세 페이지로 이동`)
  // router.push(`/project/${projectId}`)
}

const createNewTodo = () => {
  router.push({
    name: 'PlanWriteForm',
    query: { date: selectedDate.value }
  })
}
</script>

<template>
  <div class="relative w-full h-screen flex justify-center items-center gap-12 px-8">

    <!-- 왼쪽 캘린더 -->
    <div class="w-[720px] h-[90vh] rounded-2xl p-6 shadow-lg">
      <Calendar />
    </div>

    <!-- 오른쪽 리스트 -->
    <div class="w-[500px] h-[75vh] bg-[#E8D0FF]/30 rounded-2xl p-6 flex flex-col text-black gap-4 shadow-lg overflow-y-auto">
      <div class="text-dlp_lavender font-semibold text-xl mb-1">
        {{ new Date(selectedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: 'long', day: 'numeric' }) }}
      </div>
      <p class="text-sm text-gray-700 mb-4">📰 오늘의 일기 · 오늘 하루는 정말 알찬 하루였다</p>

      <button @click="goToDailyTodos"
              class="rounded-xl px-4 py-3 bg-white/30 hover:bg-white/50 transition text-left">
        하루 일정
      </button>

      <button
          v-for="item in filteredProjects"
          :key="item.id"
          @click="goToProject(item.id)"
          class="rounded-xl px-4 py-3 bg-white/30 hover:bg-white/50 transition text-left"
      >
        {{ item.title }}
      </button>

      <button @click="createNewTodo"
              class="mt-2 w-8 h-8 rounded-full bg-white/30 hover:bg-white/50 flex items-center justify-center text-xl">
        +
      </button>
    </div>
  </div>
</template>