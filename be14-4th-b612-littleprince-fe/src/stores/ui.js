import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUIStore = defineStore('ui', () => {
    const isCapturing = ref(false)
    return { isCapturing }
})