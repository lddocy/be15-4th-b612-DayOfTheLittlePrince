<script setup>
import { useRoute } from 'vue-router';
import { computed, ref } from 'vue';

import SideBar from '@/components/layout/SideBar.vue';
import PlanetScene from "@/components/common/PlanetScene.vue";
import BgmPlayer from './components/common/BgmPlayer.vue';
import LoadingSpinner from '@/components/common/LoadingSpinner.vue';

const route = useRoute();
const useLayout = computed(() => route.meta.layout !== 'none');
const isSceneLoading = ref(true);

function handleSceneLoaded() {
    isSceneLoading.value = false;
}
</script>

<template>
    <div id="app" class="flex h-screen w-screen overflow-hidden relative">
        <LoadingSpinner v-if="isSceneLoading" />

        <SideBar v-if="useLayout" />

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
