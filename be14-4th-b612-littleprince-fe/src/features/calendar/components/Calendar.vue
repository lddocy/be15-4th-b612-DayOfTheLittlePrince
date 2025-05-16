<script setup>
import { ref } from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import MonthPicker from '@/features/calendar/components/MonthPicker.vue'

import '@/assets/styles/calendar.css'

const calendarRef = ref(null)
const selectedDate = ref(new Date())

const calendarOptions = {
  plugins: [dayGridPlugin],
  initialView: 'dayGridMonth',
  height: '100%',
  events: [
    { title: '프론트 구현', start: '2025-05-08', end: '2025-05-10', className: 'fc-event-bar bg-event-1' },
    { title: '백엔드 구현', start: '2025-05-09', end: '2025-05-13', className: 'fc-event-bar bg-event-2' },
    { title: '데브옵스', start: '2025-05-14', end: '2025-05-16', className: 'fc-event-bar bg-event-3' },
    { title: '제주도 여행', start: '2025-05-18', end: '2025-05-20', className: 'fc-event-bar bg-event-4' },
    { title: '어린왕자 독서', start: '2025-05-22', end: '2025-05-25', className: 'fc-event-bar bg-event-5' },
    { title: '명상', start: '2025-05-20', className: 'fc-event-dot' },
    { title: '산책', start: '2025-05-21', className: 'fc-event-dot' },
  ],
  headerToolbar: {
    left: '',
    center: '',
    right: 'today prev,next'
  },
    datesSet(info) {
        selectedDate.value = new Date(info.view.currentStart)
    }
}

function onDateSelected(date) {
  selectedDate.value = date
  calendarRef.value.getApi().gotoDate(date)
}
</script>

<template>
  <div
      class="relative w-full max-w-[1000px] h-[90vh] p-8 bg-[rgba(232,208,255,0.3)] rounded-2xl box-border flex flex-col"
  >
    <div class="absolute top-[50px]">
      <MonthPicker
          v-model="selectedDate"
          @change="onDateSelected"
      />
    </div>

    <FullCalendar ref="calendarRef" :options="calendarOptions" />
  </div>
</template>