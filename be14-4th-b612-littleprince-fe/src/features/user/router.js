export const userRoutes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('@/features/user/views/LoginView.vue'),
        meta: {
            layout: 'none'
        },
    },
    {
        path: '/'
    },
    {
        path: '/signup',
        name: 'signup',
        component: () => import('@/features/user/views/SignupView.vue'),
        meta: {
            layout: 'none'
        },
    }
]