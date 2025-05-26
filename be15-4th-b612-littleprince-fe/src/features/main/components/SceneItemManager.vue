<script setup>
import { onMounted, watch } from 'vue';
import { loadGLTF } from '@/utils/loaders.js';
import { sceneItems } from '@/assets/data/SceneItems.js';

const props = defineProps({
    scene: Object,
    routePath: String,
    memberLevel: Number,
    itemVisibilityMap: Object,
});

const shouldShow = (path) => path === '/';

function addItem({ path, name, position, scale, rotation = [0, 0, 0], onTraverse, level = 0 }) {
    const isVisible = props.itemVisibilityMap?.[name] === 'N'; // isHidden === 'N'
    const levelValid = props.memberLevel >= level;

    if (!isVisible || !levelValid) return;

    loadGLTF(path, props.scene, (model) => {
        model.name = name;

        model.traverse((child) => {
            if (child.isMesh) {
                child.castShadow = true;
                child.receiveShadow = true;
                if (onTraverse) onTraverse(child);
            }
        });

        model.position.set(...position);
        model.scale.set(...scale);
        model.rotation.set(...rotation);
        model.visible = shouldShow(props.routePath);

        props.scene.add(model);
    });
}

onMounted(() => {
    if (!props.scene) return;
    sceneItems.forEach(addItem);
});

watch(
    () => props.routePath,
    (newPath) => {
        const show = shouldShow(newPath);
        props.scene.children.forEach((obj) => {
            if (sceneItems.some(i => i.name === obj.name)) {
                obj.visible = show;
            }
        });
    }

);
</script>

<template></template>
