import axios from 'axios';
import { useAuthStore } from '/src/stores/auth.js';

const api = axios.create({
    baseURL: "/api/v1",
    withCredentials: true,
});

// 요청 시 accessToken 자동 삽입
api.interceptors.request.use((config) => {
    const authStore = useAuthStore();
    if (authStore.accessToken) {
        config.headers.Authorization = `Bearer ${authStore.accessToken}`;
    }
    return config;
});

export default api;
