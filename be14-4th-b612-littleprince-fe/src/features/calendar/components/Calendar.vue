<script setup>
import { ref, onMounted, nextTick } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { getLongPlan, getShortDates } from "@/features/calendar/api.js"

import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import MonthPicker from '@/features/calendar/components/MonthPicker.vue'

import '@/assets/styles/calendar.css'
import '@/assets/styles/calendar-event.css'

const calendarKey = ref(0)
const calendarRef = ref(null)
const selectedDate = ref(new Date())
const calendarEvents = ref([])
const router = useRouter()
const authStore = useAuthStore()

function getTopClass(count) {
  switch (count) {
    case 1: return 'top-1';
    case 2: return '-top-1';
    case 3: return '-top-2';
    case 4: return '-top-3';
    default: return '-top-4'; // 5개 이상
  }
}

function updateStarIcons() {
  const calendarApi = calendarRef.value?.getApi()
  if (!calendarApi) return

  const dayEls = calendarRef.value.$el.querySelectorAll('.fc-daygrid-day')

  dayEls.forEach((el) => {
    const dateStr = el.getAttribute('data-date')
    const eventsOnThisDay = calendarApi.getEvents().filter(event => {
      const start = event.start.toLocaleDateString('sv-SE')
      return start === dateStr && event.classNames.includes('fc-event-dot')
    })

    const count = eventsOnThisDay.length

    // 중복 방지: 기존 별 삭제
    const oldStars = el.querySelectorAll('.custom-star')
    oldStars.forEach(star => star.remove())

    if (count > 0) {
      const star = document.createElement('div');
      star.className = `absolute left-[88%] text-dlp_yellow text-[13px] font-bold ${getTopClass(count)}`;
      star.textContent = '★'; // 여기!
      el.classList.add('relative')
      el.appendChild(star)
    }
  })
}

const calendarOptions = ref({
  plugins: [dayGridPlugin, interactionPlugin],
  initialView: 'dayGridMonth',
  height: '100%',
  events(fetchInfo, successCallback, failureCallback) {
    successCallback(calendarEvents.value)
  },
  headerToolbar: {
    left: '',
    center: '',
    right: 'today prev,next'
  },
  datesSet(info) {
    selectedDate.value = new Date(info.view.currentStart)
    nextTick(() => {
      updateStarIcons()
    })
  },
  dayCellDidMount(info) {
    nextTick(() => {
      updateStarIcons()
    })
  },
  dateClick(info) {
    router.push({ path: `/calendar/${info.dateStr}` })
  },
})

function onDateSelected(date) {
  selectedDate.value = date
  calendarRef.value.getApi().gotoDate(date)
}

async function fetchAllPlans() {
  try {
    const [longRes, shortRes] = await Promise.all([
      getLongPlan(authStore.accessToken),
      getShortDates(authStore.accessToken)
    ])

    const longPlans = longRes.data.data.planDTOList
    const shortDates = shortRes.data.data.planDateDTO

    const longEvents = longPlans.map((plan, index) => {
      const classIndex = (index % 5) + 1
      return {
        title: plan.title,
        start: plan.startDate,
        end: plan.endDate,
        className: ['fc-event-bar', `bg-event-${classIndex}`],
      }
    })

    const shortEvents = shortDates.map(d => ({
      title: '',
      start: d.date,
      className: ['fc-event-dot']
    }))

    calendarEvents.value = [...longEvents, ...shortEvents]

    calendarKey.value++ // 리렌더 트리거
  } catch (err) {
    console.error('플랜 조회 실패:', err)
  }
}

onMounted(() => {
  fetchAllPlans()
})
</script>

<template>
  <div
      class="relative w-full max-w-[1000px] h-[90vh] p-8 bg-[rgba(232,208,255,0.3)] rounded-2xl box-border flex flex-col"
  >
    <div class="absolute top-[50px]">
      <MonthPicker v-model="selectedDate" @change="onDateSelected" />
    </div>
    <FullCalendar :key="calendarKey" ref="calendarRef" :options="calendarOptions" />
  </div>
</template>

<style>
.fc .fc-daygrid-day-top {
  display: flex;
  flex-direction: row;
}
.custom-star {
  z-index: 10;
}
</style>
