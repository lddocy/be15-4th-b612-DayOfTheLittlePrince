<script setup>
import { ref, computed, watch, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Calendar from '@/features/calendar/components/Calendar.vue'
import { useAuthStore } from '@/stores/auth'
import {
  getShortDates,
  getLongList,
  getReviewDates,
  reviewCreateOrUpdate,
  deleteLongTodo
} from '@/features/calendar/api.js'
import {useToast} from "vue-toastification";

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()
const toast = useToast()

const selectedDate = ref(route.params.date)

const dotDates = ref([])

const longTermTodos = ref([])

const todayLog = ref('')
const isEditingLog = ref(false)
const reviewId = ref(null)

const filteredProjects = computed(() =>
    longTermTodos.value.filter(todo => todo.date === selectedDate.value)
)

const isDotDate = computed(() =>
    dotDates.value.includes(selectedDate.value)
)

const goToDailyTodos = () => {
  router.push({
    name: 'ShortTermListView',
    params: { date: selectedDate.value }
  })
}

const goToProject = (projectId) => {
  router.push({
    name: 'LongTermListView',
    params: {
      date: selectedDate.value,
      projectId: projectId
    }
  })
}

const createNewTodo = () => {
  router.push({
    name: 'PlanWriteForm',
    query: { date: selectedDate.value }
  })
}

const startEditLog = () => {
  isEditingLog.value = true
}

const handleDateSelected = (dateStr) => {
  router.push({ name: 'PlanListView', params: { date: dateStr } })
}

/* 장기 플랜 삭제 (하위 단기 플랜 까지)*/
const deleteTodo = async (projectId) => {
  const confirmed = window.confirm('정말 이 장기 플랜을 삭제하시겠습니까?\n관련된 할 일도 함께 삭제됩니다.')

  if (!confirmed) return

  try {
    await deleteLongTodo(authStore.accessToken, projectId)

    window.location.reload()

  } catch (err) {
    console.error('삭제 실패:', err)
    toast.error('삭제에 실패했습니다.')
  }
}

// 날짜가 바뀔 때마다 장기 플랜/회고 조회
watch(() => route.params.date, async (newDate) => {
  selectedDate.value = newDate
  await fetchLongPlans(newDate)
  await fetchReview(newDate)
})

// 단기 플랜 조회
const fetchShortPlans = async () => {
  try {
    const res = await getShortDates(authStore.accessToken)
    dotDates.value = res.data.data.planDateDTO.map(d => d.date)
  } catch (err) {
    console.error('단기 일정 조회 실패:', err)
  }
}

// 장기 플랜 조회
const fetchLongPlans = async (date) => {
  try {
    const res = await getLongList(authStore.accessToken, date)
    longTermTodos.value = res.data.data.longListDTOS.map(item => ({
      id: item.projectId,
      title: item.title,
      date: date
    }))
  } catch (err) {
    console.error('장기 플랜 조회 실패:', err)
    longTermTodos.value = []
  }
}

// 회고 조회
const fetchReview = async (date) => {
  try {
    const res = await getReviewDates(authStore.accessToken, date)
    const review = res.data.data

    if (review) {
      todayLog.value = review.reviewContent
      reviewId.value = review.reviewId
    } else {
      todayLog.value = ''
      reviewId.value = null
    }
  } catch (err) {
    console.error('회고 조회 실패:', err)
    todayLog.value = ''
    reviewId.value = null
  }
}

/* 회고 저장 or 수정 */
const saveLog = async () => {
  try {
    await reviewCreateOrUpdate(authStore.accessToken, selectedDate.value, todayLog.value)
    isEditingLog.value = false
    toast.success('회고가 반영되었습니다!')
  } catch (err) {
    console.error('회고 저장 실패:', err)
    toast.error('회고 저장에 실패했습니다.')
  }
}

onMounted(async () => {
  await fetchShortPlans()
  await fetchLongPlans(selectedDate.value)
  await fetchReview(selectedDate.value)
})
</script>


<template>
  <div class="w-screen h-screen flex justify-center items-center">
    <!-- 가운데 너비 확보 -->
    <div class="flex gap-12 w-[1240px]">

      <!-- 캘린더 -->
      <div class="w-[1000px] h-[90vh] rounded-2xl p-2 shadow-lg overflow-hidden">
        <Calendar @date-selected="handleDateSelected" />
      </div>

      <!-- 플랜 리스트 -->
      <div class="w-[500px] h-[75vh] bg-[#E8D0FF]/30 rounded-2xl p-6 flex flex-col text-black gap-4 shadow-lg overflow-y-auto self-center">

        <!-- 날짜 -->
        <div class="text-dlp_lavender font-semibold text-xl mb-1">
          {{ new Date(selectedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: 'long', day: 'numeric' }) }}
        </div>

        <!-- 오늘의 일기 -->
        <div class="flex items-center gap-2 text-sm text-gray-800 mb-1">
          <img src="@/assets/icons/note.png" alt="note" class="w-6 h-6" />
          <div v-if="!isEditingLog" class="flex items-center gap-1">
            <span>{{ todayLog || '하루를 기록해보세요' }}</span>
            <img
                src="@/assets/icons/edit.png"
                alt="edit"
                class="w-4 h-4 cursor-pointer opacity-70 hover:opacity-100"
                @click="startEditLog"
            />
          </div>
          <div v-else class="flex items-center gap-2">
            <input
                v-model="todayLog"
                type="text"
                class="text-sm bg-transparent border-b border-white/30 focus:outline-none"
                placeholder="오늘 하루를 회고해보세요"
            />
            <button @click="saveLog" class="text-xs text-dlp_lavender hover:underline">저장</button>
          </div>
        </div>

        <div class="w-full h-[1px] bg-white/20 mb-2" />

        <!-- 하루 일정 -->
        <button
            v-if="isDotDate"
            @click="goToDailyTodos"
            class="rounded-xl px-4 py-3 text-left text-black transition bg-dlp_card/40 hover:bg-dlp_card_hover/80"
        >
          하루 일정
        </button>

        <!-- 장기 프로젝트 -->
        <button
            v-for="item in filteredProjects"
            :key="item.id"
            @click="goToProject(item.id)"
            class="group relative rounded-xl px-4 py-3 text-left text-black transition bg-dlp_card/40 hover:bg-dlp_card_hover/80"
        >
          {{ item.title }}

          <span
              @click.stop="deleteTodo(item.id)"
              class="hidden group-hover:block absolute top-3 right-4 cursor-pointer transition bg-transparent p-0 border-none shadow-none"
          >
            <img src="@/assets/icons/trash.png" alt="삭제" class="w-6 h-6 object-contain" />
          </span>
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