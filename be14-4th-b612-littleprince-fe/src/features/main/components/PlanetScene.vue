<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';
import { useRoute } from 'vue-router';

import { createCamera, createRenderer, addBasicLighting } from '@/utils/setupThreeScene.js';
import { loadHDRI, loadGLTF } from '@/utils/loaders.js';
import SceneItemManager from '@/features/main/components/SceneItemManager.vue';
import MainIconItem from '@/features/main/components/MainIconItem.vue';
import MemberInfoItem from '@/features/main/components/MemberInfoItem.vue';
import { useAuthStore } from '@/stores/auth.js';
import { useUserStore } from '@/stores/user.js';
import { fetchExpInfo, getSelectedBadge, updatePlanetName } from '@/features/main/api.js';
import { useToast } from 'vue-toastification';
import { fetchMyItems } from '@/features/user/api.js';

const emit = defineEmits(['loaded']);

const props = defineProps({
    refreshFlag: Number,
});

const container = ref(null);
const sceneRef = ref(null);
const princeRef = ref([]);
const isSceneReady = ref(false);

const route = useRoute();
const toast = useToast();

const authStore = useAuthStore();
const userStore = useUserStore();

const memberInfo = computed(() => userStore.memberInfo || {});
const memberLevel = computed(() => memberInfo.value.level || 0);
const totalExp = ref(0);
const badge = ref('');
const itemVisibilityMap = ref({});

watch(() =>
    authStore.accessToken,
    token => {
        if (token) userStore.loadMemberInfo(token);
    }, { immediate: true }
);

onMounted(async () => {
    const scene = setupScene();
    setupCameraRenderer(container.value, scene);
    sceneRef.value = scene;

    await fetchInitialData();

    loadMainPlanet(scene);
});

async function fetchInitialData() {
    await Promise.all([
        fetchExp(),
        fetchBadge(),
        fetchItemMap()
    ]);
}

async function fetchExp() {
    try {
        const token = authStore.accessToken;
        if (!token) return;
        const { data: wrapper } = await fetchExpInfo(token);
        totalExp.value = wrapper?.data?.totalExpToNextLevel || 100;
    } catch (e) {
        console.error('경험치 조회 실패', e.message);
        toast.error('경험치를 불러오지 못했습니다.');
    }
}

async function fetchBadge() {
    try {
        const token = authStore.accessToken;
        if (!token) return;
        const { data: wrapper } = await getSelectedBadge(token);
        badge.value = wrapper?.data || '';
    } catch (e) {
        console.error('칭호 조회 실패', e.message);
        toast.error('칭호를 불러오지 못했습니다.');
    }
}

async function fetchItemMap() {
    try {
        const itemList = await fetchMyItems();

        const map = {};

        for (const item of itemList) {
            const key = item.itemName; // "여우", "우물", "바오밥나무" 등
            map[key] = item.isHidden;  // "Y" or "N"
        }

        itemVisibilityMap.value = map;
    } catch (e) {
        console.error('아이템 목록 조회 실패:', e);
        toast.error('아이템 정보를 불러오지 못했습니다.');
    }
}

watch(() => props.refreshFlag, async () => {
    await fetchItemMap();
});

const editPlanetName = async ({ planetName }) => {
    try {
        const token = authStore.accessToken;
        if (!token) return;

        const { data: wrapper } = await updatePlanetName(token, planetName);
        toast.success('행성 이름이 수정되었습니다.');
        await userStore.loadMemberInfo(token);
    } catch (e) {
        console.error('행성 이름 수정 실패', e.message);
        toast.error('행성 이름 수정에 실패했습니다.');
    }
};

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
            v-if="sceneRef && isSceneReady && Object.keys(itemVisibilityMap).length > 0"
            :scene="sceneRef"
            :route-path="route.path"
            :memberLevel="memberLevel"
            :itemVisibilityMap="itemVisibilityMap"
        />
        <MainIconItem v-if="isSceneReady" />
        <div class="fixed bottom-6 right-6 z-0">
            <MemberInfoItem
                :memberInfo="memberInfo"
                :max="totalExp"
                :badge="badge"
                @edit-planet-name="editPlanetName"
                v-if="isSceneReady"
            />
        </div>
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
