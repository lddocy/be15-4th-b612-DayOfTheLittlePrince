import CalendarView from "@/features/calendar/views/CalendarView.vue";
import PlanWriteForm from "@/features/calendar/views/PlanWriteForm.vue";
import TodoListView from "@/features/calendar/views/TodoListView.vue";

export const calendarRoutes = [
    {
        path: '/calendar',
        name: 'calendar',
        component: CalendarView,
        meta: { layout: 'default' },
    },
    {
        path: '/calendar/plan/write',
        name: 'PlanWriteForm',
        component: PlanWriteForm,
        meta: { layout: 'default' },
    },
    {
        path: '/calendar/:date',
        name: 'TodoListView',
        component: TodoListView,
        meta: { layout: 'default' }
    },
];