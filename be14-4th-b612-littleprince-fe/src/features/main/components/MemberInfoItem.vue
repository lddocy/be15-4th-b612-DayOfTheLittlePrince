<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();

const barWidth = (current, max) => {
    if (!max || max === 0) return '0%';
    const ratio = Math.min(current / max, 1); // 100% 이상 못 넘게 제한
    return `${(ratio * 100).toFixed(1)}%`;    // 소수점 한 자리로 고정
};

const isMain = computed(() =>
    route.path === '/'
);

defineProps({
    memberInfo: Object,
    current: { type: Number, default: 0 },
    max: { type: Number, default: 0 },
});
</script>

<template>
    <div class="w-[300px] text-white" v-if="isMain">
        <!-- 칭호 -->
        <div class="text-right mb-1" style="font-size: var(--dlp-font-size-text-md);">
            {{ memberInfo.badge }}
        </div>

        <!-- 레벨 -->
        <div class="text-right mb-1" style="font-size: var(--dlp-font-size-text-lg);">
            Lv. {{ memberInfo.level }}
        </div>

        <!-- 행성 이름 -->
        <div class="text-right mb-1" style="font-size: var(--dlp-font-size-text-lg);">
            {{ memberInfo.planetName }}
        </div>

        <!-- 경험치 -->
        <div class="w-full h-[30px] rounded-md bg-white/30 mb-1 relative overflow-hidden">
            <div class="h-full" style="background-color: var(--dlp-purple-100)"
                 :style="{ width: barWidth(memberInfo.exp, max) }"></div>
        </div>

        <!-- 수치 -->
        <div class="text-right" style="font-size: var(--dlp-font-size-text-sm)">
            {{ memberInfo.exp }} / {{ max }}
        </div>
    </div>
</template>

<style scoped>
</style>
