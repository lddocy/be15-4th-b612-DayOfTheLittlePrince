<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import {getLongPlan, getShortDates} from "@/features/calendar/api.js";

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

    // dot 타입의 이벤트만 대상으로 필터링
    const eventsOnThisDay = info.view.calendar
        .getEvents()
        .filter(event => {
          const start = event.start.toISOString().split('T')[0];
          return start === currentDate && event.classNames.includes('fc-event-dot');
        });

    const count = eventsOnThisDay.length;

    if (count > 0) {
      const dot = document.createElement('div');
      dot.className = `w-[7px] h-[7px] rounded-full absolute top-[10px] left-[90%] rounded-full bg-pink-400 ${getTopClass(count)}`;

      info.el.classList.add('relative');
      info.el.appendChild(dot);
    }
  }
})

function getTopClass(count) {
  switch (count) {
    case 1:
      return 'top-1';
    case 2:
      return '-top-1';
    case 3:
      return '-top-2';
    case 4:
      return '-top-3';
    default:
      return '-top-4'; // 5개 이상
  }
}

function onDateSelected(date) {
  selectedDate.value = date
  calendarRef.value.getApi().gotoDate(date)
}

// 장기 플랜 API 호출
async function fetchLongPlans() {
  try {
    const res = await getLongPlan(authStore.accessToken)
    const plans = res.data.data.planDTOList

    // FullCalendar
    calendarEvents.value = plans.map((plan, index) => {
      const classIndex = (index % 5) + 1;

      return {
        title: plan.title,
        start: plan.startDate,
        end: plan.endDate,
        className: ['fc-event-bar', `bg-event-${classIndex}`],
        backgroundColor: `bg-event-${classIndex}`,
        borderColor: `bg-event-${classIndex}`
      }
    });

    // 캘린더
    const calendarApi = calendarRef.value?.getApi()
    if (calendarApi) {
      calendarApi.removeAllEvents()
      calendarEvents.value.forEach(event => calendarApi.addEvent(event))
    }
  } catch (err) {
    console.error('장기 플랜 조회 실패:', err)
  }
}

// 단기플랜 여부 조회
async function fetchShortPlans() {
  try {
    const res = await getShortDates(authStore.accessToken)
    const dates = res.data.data.planDateDTO

    // 각 날짜에 dot 이벤트 추가
    const shortEvents = dates.map(d => ({
      title: '', // 제목 없음
      start: d.date,
      className: ['fc-event-dot']
    }))

    const calendarApi = calendarRef.value?.getApi()
    if (calendarApi) {
      shortEvents.forEach(event => calendarApi.addEvent(event))
    }

  } catch (err) {
    console.error('단기 플랜 조회 실패:', err)
  }
}

onMounted(() => {
  fetchLongPlans()
  fetchShortPlans()
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
