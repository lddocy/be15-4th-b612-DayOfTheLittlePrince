import { createRouter, createWebHistory } from 'vue-router'
import {mainRoutes} from "@/features/main/router.js";

const router = createRouter({
  history: createWebHistory(),
  routes: [
      ...mainRoutes,
      // 다음 줄에 추가해주세요
  ],
})

export default router
