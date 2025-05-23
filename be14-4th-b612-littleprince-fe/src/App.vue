<script setup>
import { useRoute } from 'vue-router';
import { computed, ref } from 'vue';
import { onMounted } from 'vue';
import { subscribePush } from '@/features/user/api';

import SideBar from '@/components/layout/SideBar.vue';
import PlanetScene from "@/components/common/PlanetScene.vue";
import BgmPlayer from './components/common/BgmPlayer.vue';
import LoadingSpinner from '@/components/common/LoadingSpinner.vue';

const route = useRoute();
const useLayout = computed(() => route.meta.layout !== 'none');
const isSceneLoading = ref(true);
const uiStore = useUIStore();

function handleSceneLoaded() {
  isSceneLoading.value = false;
}
function urlBase64ToUint8Array(base64String) {
  const padding = '='.repeat((4 - base64String.length % 4) % 4);
  const base64 = (base64String + padding).replace(/-/g, '+').replace(/_/g, '/');
  const rawData = atob(base64);
  return Uint8Array.from([...rawData].map((char) => char.charCodeAt(0)));
}

async function subscribeUserToPush() {
  try {
    const permission = await Notification.requestPermission();
    if (permission !== 'granted') {
      console.warn("알림 권한이 거부되었습니다.");
      return;
    }

    const registration = await navigator.serviceWorker.register('/service-worker.js');

    const subscription = await registration.pushManager.subscribe({
      userVisibleOnly: true,
      applicationServerKey: urlBase64ToUint8Array(import.meta.env.VITE_VAPID_PUBLIC_KEY),
    });

    const sub = subscription.toJSON();

    await subscribePush({
      endpoint: subscription.endpoint,
      p256dh: sub.keys.p256dh,
      auth: sub.keys.auth,
    });

    console.log('✅ 푸시 구독 성공');
  } catch (error) {
    console.error('❌ 푸시 구독 실패:', error);
  }
}

onMounted(() => {
  if ('serviceWorker' in navigator && 'PushManager' in window) {
    subscribeUserToPush();
  }
});
</script>

<template>
  <div id="app" class="flex h-screen w-screen overflow-hidden relative">
    <LoadingSpinner v-if="isSceneLoading" />

        <!-- 사이드바 숨김 조건 -->
        <SideBar v-if="useLayout && !uiStore.isCapturing" />
    <SideBar v-if="useLayout" />

      <!-- 음악 플레이어 숨김 조건 -->
      <div
          v-show="!uiStore.isCapturing"
          class="absolute bottom-6 left-[90px] lg:left-[160px] md:left-[120px] z-50"
      >
        <BgmPlayer />
      </div>
    <div class="absolute bottom-6 left-[90px] lg:left-[160px] md:left-[120px] z-50">
      <BgmPlayer />
    </div>

    <!-- '/' 경로에서는 배경으로 안쓰이게 -->
    <PlanetScene
        :class="{'planet-front' : route.path === '/'}"
        @loaded="handleSceneLoaded"
    />

    <div class="flex-1" :class="{ layout: useLayout }">
      <router-view />
    </div>

  </div>
</template>

<style>
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  overflow: hidden;
}

#app {
  height: 100%;
  width: 100%;
  overflow: hidden;
}
/*.layout {
  height: 100%;
  overflow: auto;
}*/

.layout {
  position: relative;
  z-index: 1;
}

.planet-front {
  z-index: 2 !important;
  pointer-events: auto;
}

@font-face {
  font-family: 'Cafe24Oneprettynight';
  src: url('/fonts/Cafe24Oneprettynight-v2.0.woff2') format('woff2'),
  url('/fonts/Cafe24Oneprettynight-v2.0.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

* {
  font-family: 'Cafe24Oneprettynight', sans-serif;
}
</style>