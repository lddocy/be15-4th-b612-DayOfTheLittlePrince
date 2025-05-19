import CalendarView from "@/features/calendar/views/CalendarView.vue";
import PlanWriteForm from "@/features/calendar/views/PlanWriteForm.vue";
import PlanListView from "@/features/calendar/views/PlanListView.vue";
import ShortTermListView from "@/features/calendar/views/ShortTermListView.vue";
import LongTermListView from "@/features/calendar/views/LongTermListView.vue";

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
        name: 'PlanListView',
        component: PlanListView,
        meta: { layout: 'default' }
    },
    {
        path: '/calendar/:date/todo',
        name: 'ShortTermListView',
        component: ShortTermListView,
        meta: { layout: 'default' }
    },
    {
        path: '/calendar/:date/project/:projectId',
        name: 'LongTermListView',
        component: LongTermListView,
        meta: { layout: 'default' }
    }
];