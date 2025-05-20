// src/plugins/axios.js
import axios from 'axios'
import { useAuthStore } from '@/stores/auth.js'

const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    withCredentials: true,
})

// 요청 시 accessToken 자동 삽입
api.interceptors.request.use((config) => {
    const authStore = useAuthStore()
    if (authStore.accessToken) {
        config.headers.Authorization = `Bearer ${authStore.accessToken}`
    }
    return config
})

export default api
