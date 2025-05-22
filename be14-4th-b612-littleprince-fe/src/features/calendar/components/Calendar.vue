<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { getLongPlan, getShortDates } from "@/features/calendar/api.js"

import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import MonthPicker from '@/features/calendar/components/MonthPicker.vue'

import '@/assets/styles/calendar.css'
import '@/assets/styles/calendar-event.css'

const calendarRef = ref(null)
const selectedDate = ref(new Date())
const calendarEvents = ref([])
const router = useRouter()
const authStore = useAuthStore()

const calendarOptions = ref({
  plugins: [dayGridPlugin, interactionPlugin],
  initialView: 'dayGridMonth',
  height: '100%',
  events: calendarEvents.value,
  headerToolbar: {
    left: '',
    center: '',
    right: 'today prev,next'
  },
  datesSet(info) {
    selectedDate.value = new Date(info.view.currentStart)
  },
  dateClick(info) {
    router.push({ path: `/calendar/${info.dateStr}` })
  },
  dayCellDidMount(info) {
    const currentDate = info.date.toISOString().split('T')[0];

    // dot 타입의 이벤트만 필터링
    const eventsOnThisDay = info.view.calendar
        .getEvents()
        .filter(event => {
          const start = event.start.toISOString().split('T')[0];
          return start === currentDate && event.classNames.includes('fc-event-dot');
        });

    const count = eventsOnThisDay.length;

    if (count > 0) {
      const dot = document.createElement('div');
      dot.className = `w-[7px] h-[7px] rounded-full absolute top-[10px] left-[90%] bg-pink-400 ${getTopClass(count)}`;
      info.el.classList.add('relative');
      info.el.appendChild(dot);
    }
  }
})

function getTopClass(count) {
  switch (count) {
    case 1: return 'top-1';
    case 2: return '-top-1';
    case 3: return '-top-2';
    case 4: return '-top-3';
    default: return '-top-4'; // 5개 이상
  }
}

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

    const allEvents = [...longEvents, ...shortEvents]

    const calendarApi = calendarRef.value?.getApi()
    if (calendarApi) {
      calendarApi.removeAllEvents()
      allEvents.forEach(event => calendarApi.addEvent(event))

      calendarApi.rerenderDates()
    }
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

    <FullCalendar ref="calendarRef" :options="calendarOptions" />
  </div>
</template>

<style>
.fc .fc-daygrid-day-top {
  display: flex;
  flex-direction: row;
}
</style>
