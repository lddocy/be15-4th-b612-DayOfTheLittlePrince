<template>
  <div class="main-layout">
    <PlanetScene />
  </div>
</template>

<script setup>
import PlanetScene from "@/features/main/components/PlanetScene.vue";
</script>

<style scoped>
.main-layout {
  position: relative;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  display: flex;
}
</style>
