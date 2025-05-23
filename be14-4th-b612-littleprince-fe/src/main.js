import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import router from './router';
import './assets/styles/design-tokens.css';
import Toast, { TYPE } from 'vue-toastification';
import 'vue-toastification/dist/index.css';
import { useAuthStore } from './stores/auth';
import { refreshMemberToken } from './features/user/api';

async function bootstrap() {
    const app = createApp(App);
    app.use(createPinia());

    const authStore = useAuthStore();
    if (!authStore.isAuthenticated) {
        try {
            const response = await refreshMemberToken();
            authStore.setAccessToken(response.data.data.accessToken);
            console.log('엑세스 토큰 재발급 완료');
        } catch (e) {
            console.log(e);
            authStore.clearAccessToken();
            console.log('로그인 만료');
        }
    } else {
        console.log('기존 access token 유효, refresh 생략');
    }

    app.use(router);

    const notificationOptions = {
        toastDefaults: {
            [TYPE.ERROR]: {
                timeout: 2000,
            },
        },
    };

    app.use(Toast, notificationOptions);

    app.mount('#app');
}

// 우회 방식
(async () => {
    await bootstrap()
})()
