<script setup>
import {onMounted, ref, watch} from 'vue';
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';

import { createCamera, createRenderer, addBasicLighting } from '@/utils/setupThreeScene.js';
import { loadHDRI, loadGLTF } from '@/utils/loaders.js';
import {useRoute} from "vue-router";
const container = ref(null);
const isLoading = ref(true);
const princeRef = ref(null);
const route = useRoute();

onMounted(() => {
  const scene = new THREE.Scene()
  loadHDRI('/models/space-background/NightSkyHDRI001_4K-HDR.exr', scene);
  addBasicLighting(scene);

  const camera = createCamera(container.value);
  const renderer = createRenderer(container.value);
  container.value.appendChild(renderer.domElement);

  const controls = new OrbitControls(camera, renderer.domElement);
  controls.enableDamping = true;
  controls.dampingFactor = 0.05;


  window.addEventListener('resize', () => {
    camera.aspect = container.value.clientWidth / container.value.clientHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(container.value.clientWidth, container.value.clientHeight);
  });

  loadGLTF('/models/planet-and-prince/scene.gltf', scene, (model) => {
    model.position.set(0, -10, 0);
    model.scale.set(0.5, 0.5, 0.5);
    model.visible = shouldShowPrince(route.path);
    scene.add(model);
    princeRef.value = model;
    isLoading.value = false;
  });

  const animate = () => {
    requestAnimationFrame(animate);
    controls.update();
    renderer.render(scene, camera);
  };
  animate();
});

const showPrince = () => {
  if (princeRef.value) princeRef.value.visible = true;
};
const hidePrince = () => {
  if (princeRef.value) princeRef.value.visible = false;
};

function shouldShowPrince(path) {
  return path === '/';
}

watch(
    () => route.path,
    (newPath) => {
      if (shouldShowPrince(newPath)) {
        showPrince();
      } else {
        hidePrince();
      }
    }
);

</script>

<template>
  <div class="scene-wrapper relative w-full h-full">
    <div v-if="isLoading" class="absolute inset-0 z-20 flex items-center justify-center bg-dlp_pinkPurple/20 text-dlp_purple text-xl font-bold">
        어린왕자가 당신을 기다리고 있어요...
    </div>
    <div ref="container" class="scene-container"></div>
  </div>
</template>

<style scoped>
.scene-container {
  position: absolute;
  width: 100%;
  height: 100%;
  overflow: hidden;
}
</style>
