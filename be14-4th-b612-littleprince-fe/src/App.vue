<script setup>
import SideBar from '@/components/layout/SideBar.vue';

import { useRoute } from 'vue-router';
import { computed } from 'vue';
import PlanetScene from "@/features/main/components/PlanetScene.vue";

const route = useRoute();
const useLayout = computed(() => route.meta.layout !== 'none');
</script>

<template>
  <div id="app" class="flex h-screen w-screen overflow-hidden">
    <SideBar v-if="useLayout" />
    <PlanetScene/>
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
  position: relative;
}

.layout {
  height: 100%;
  overflow: auto;
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
