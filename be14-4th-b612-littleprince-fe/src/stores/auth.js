// src/stores/auth.js

import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', () => {
  const accessToken = ref(null)

  function setAccessToken(token) {
    accessToken.value = token
  }

  function clearAccessToken() {
    accessToken.value = null
  }

  return {
    accessToken,
    setAccessToken,
    clearAccessToken,
  }
})
