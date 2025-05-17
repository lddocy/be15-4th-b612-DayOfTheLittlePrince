import { createRouter, createWebHistory } from 'vue-router'
import {mainRoutes} from "@/features/main/router.js";
import {calendarRoutes} from "@/features/calendar/router.js";
import {userRoutes} from "@/features/user/router.js";

const router = createRouter({
  history: createWebHistory(),
  routes: [
      ...mainRoutes,
      ...calendarRoutes,
      ...userRoutes
      // 다음 줄에 추가해주세요
  ],
})

export default router
