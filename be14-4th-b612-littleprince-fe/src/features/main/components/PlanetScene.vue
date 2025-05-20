<script setup>
import { onMounted, ref, watch } from 'vue';
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';
import { useRoute } from 'vue-router';

import { createCamera, createRenderer, addBasicLighting } from '@/utils/setupThreeScene.js';
import { loadHDRI, loadGLTF } from '@/utils/loaders.js';
import SceneItemManager from '@/features/main/components/SceneItemManager.vue';

const emit = defineEmits(['loaded']);
const container = ref(null);
const sceneRef = ref(null);
const princeRef = ref([]);
const route = useRoute();
const isSceneReady = ref(false);

function setupScene() {
    const scene = new THREE.Scene();
    loadHDRI('/models/space-background/NightSkyHDRI001_4K-HDR.exr', scene);
    addBasicLighting(scene);
    sceneRef.value = scene;
    return scene;
}

function setupCameraRenderer(container, scene) {
    const camera = createCamera(container);
    const renderer = createRenderer(container);
    container.appendChild(renderer.domElement);

    const controls = new OrbitControls(camera, renderer.domElement);
    controls.enableDamping = true;
    controls.dampingFactor = 0.05;

    window.addEventListener('resize', () => {
        camera.aspect = container.clientWidth / container.clientHeight;
        camera.updateProjectionMatrix();
        renderer.setSize(container.clientWidth, container.clientHeight);
    });

    const animate = () => {
        requestAnimationFrame(animate);
        controls.update();
        renderer.render(scene, camera);
    };
    animate();
}

function loadMainPlanet(scene) {
    loadGLTF('/models/planet-and-prince/scene.gltf', scene, (model) => {
        model.name = 'planet-and-prince';
        model.position.set(0, -15, 0);
        model.scale.set(0.6, 0.6, 0.6);
        model.visible = shouldShowItems(route.path);
        scene.add(model);
        princeRef.value.push(model);
        isSceneReady.value = true;
        emit('loaded');
    });
}

function shouldShowItems(path) {
    return path === '/';
}

function updatePrinceVisibility(show) {
    princeRef.value.forEach((model) => (model.visible = show));
}

onMounted(() => {
    const scene = setupScene();
    setupCameraRenderer(container.value, scene);
    loadMainPlanet(scene);
});

watch(
    () => route.path,
    (newPath) => {
        updatePrinceVisibility(shouldShowItems(newPath));
    }
);
</script>

<template>
    <div class="scene-wrapper relative w-full h-full">
        <div ref="container" class="scene-container"></div>
        <SceneItemManager
            v-if="sceneRef && isSceneReady"
            :scene="sceneRef"
            :route-path="route.path"
        />
    </div>
</template>

<style scoped>
.scene-container {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 0;
    width: 100vw;
    height: 100vh;
}
</style>
