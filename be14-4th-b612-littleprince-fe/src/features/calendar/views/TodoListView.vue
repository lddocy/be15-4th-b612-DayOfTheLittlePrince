<script setup>
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Calendar from '@/features/calendar/components/Calendar.vue'

const route = useRoute()
const router = useRouter()
const selectedDate = ref(route.params.date)

const longTermTodos = ref([
  { id: 1, title: '바디 프로필', date: '2025-05-14' },
  { id: 2, title: '어린왕자 프로젝트', date: '2025-05-14' }
])

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
}

const createNewTodo = () => {
  router.push({
    name: 'PlanWriteForm',
    query: { date: selectedDate.value }
  })
}
</script>

<template>
  <div class="w-screen h-screen flex justify-center items-center bg-white">
    <!-- 가운데 너비 확보 -->
    <div class="flex gap-12 w-[1240px]">

      <!-- 캘린더 -->
      <div class="w-[1000px] h-[90vh] rounded-2xl p-2 shadow-lg overflow-hidden">
        <Calendar />
      </div>

      <!-- 리스트 -->
      <div class="w-[500px] h-[75vh] bg-[#E8D0FF]/30 rounded-2xl p-6 flex flex-col text-black gap-4 shadow-lg overflow-y-auto self-center">
        <div class="text-dlp_lavender font-semibold text-xl mb-1">
          {{ new Date(selectedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: 'long', day: 'numeric' }) }}
        </div>
        <p class="text-sm text-gray-700 mb-4">📰 오늘의 일기 · 오늘 하루는 정말 알찬 하루였다</p>

        <!-- 하루 일정 박스 -->
        <button
            @click="goToDailyTodos"
            class="rounded-xl px-4 py-3 text-left text-black transition bg-dlp_card/40 hover:bg-dlp_card_hover/80"
        >
          하루 일정
        </button>

        <!-- 장기 프로젝트 박스 -->
        <button
            v-for="item in filteredProjects"
            :key="item.id"
            @click="goToProject(item.id)"
            class="rounded-xl px-4 py-3 text-left text-black transition bg-dlp_card/40 hover:bg-dlp_card_hover/80"
        >
          {{ item.title }}
        </button>

        <!-- + 버튼 -->
        <button
            @click="createNewTodo"
            class="mt-2 w-8 h-8 rounded-full bg-dlp_card/40 hover:bg-dlp_card_hover/80
                 flex items-center justify-center text-xl"
        >
          +
        </button>
      </div>
    </div>
  </div>
</template>