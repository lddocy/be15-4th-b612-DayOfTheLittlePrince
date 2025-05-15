import CalendarView from "@/features/calendar/views/CalendarView.vue";

export const calendarRoutes = [
    {
        path: '/calendar',
        name: 'calendar',
        component: CalendarView,
        meta: { layout: 'default' },
    },
];