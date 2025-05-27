export const userRoutes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('@/features/user/views/LoginView.vue'),
        meta: {
            layout: 'none',
        },
    },

    {
        path: '/findPassword',
        name: 'findPassword',
        component: () => import('@/features/user/views/FindPasswordView.vue'),
        meta: {
            layout: 'none',
        },
    },

    {
        path: '/signup',
        name: 'signup',
        component: () => import('@/features/user/views/SignupView.vue'),
        meta: {
            layout: 'none',
        },
    },

    {
        path: '/changePassword',
        name: 'changePassword',
        component: () => import('@/features/user/views/ChangePasswordView.vue'),
        meta: {
            layout: 'none',
        },
    },
];
