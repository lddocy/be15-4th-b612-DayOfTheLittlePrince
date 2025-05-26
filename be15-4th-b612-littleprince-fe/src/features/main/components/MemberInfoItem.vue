<script setup>
import { ref, watch } from 'vue';
import { useUserStore } from '@/stores/user';
const userStore = useUserStore();
import { computed } from 'vue';


const props = defineProps({
    memberInfo: Object,
    current: { type: Number, default: 0 },
    max: { type: Number, default: 0 },
});
const badge = computed(() => userStore.memberInfo?.badgeName?.trim() || '우주 먼지');
const emit = defineEmits(['edit-planet-name']);

const isEditing = ref(false);
const planetNameInput = ref('');

watch(
    () => props.memberInfo?.planetName,
    (newName) => {
        planetNameInput.value = newName || '';
    },
    { immediate: true }
);

const submitEdit = () => {
    const name = planetNameInput.value.trim();
    if (name) {
        emit('edit-planet-name', { planetName: name });
        isEditing.value = false;
    }
};

const cancelEdit = () => {
    planetNameInput.value = props.memberInfo?.planetName || '';
    isEditing.value = false;
};

const handleKey = (e) => {
    if (e.key === 'Enter') submitEdit();
    if (e.key === 'Escape') cancelEdit();
};

const barWidth = (current, max) => {
    if (props.memberInfo.level === 10) return '100%';
    if (!max || max === 0) return '0%';
    const ratio = Math.min(current / max, 1);
    return `${(ratio * 100).toFixed(1)}%`;
};
</script>

<template>
    <div
        v-if="$route.path === '/'"
        class="w-[350px] text-white text-right mb-1 group"
    >
        <!-- 칭호 -->
        <div style="font-size: var(--dlp-font-size-text-sm)">
            {{ badge }}
        </div>

        <!-- 레벨 + 행성 이름 -->
        <div
            class="flex items-center justify-end gap-2 mb-2"
            style="font-size: var(--dlp-font-size-text-lg)"
        >
            <template v-if="isEditing">
                <input
                    v-model="planetNameInput"
                    @keydown="handleKey"
                    maxlength="20"
                    class="rounded px-2 py-1 text-black w-[240px] h-[32px] mb-1 bg-white/20"
                />
                <button
                    @click="submitEdit"
                    class="rounded p-1"
                    style="background-color: var(--dlp-color-bg-button-primary); font-size: var(--dlp-font-size-text-sm);"
                >
                    완료
                </button>
            </template>
            <template v-else>
                <img
                    @click="isEditing = true"
                    src="@/assets/icons/edit.png"
                    alt="edit"
                    class="w-9 h-9 cursor-pointer hidden group-hover:block"
                />
                <span>Lv. {{ memberInfo.level }}</span>
                <span>{{ memberInfo.planetName }}</span>
            </template>
        </div>

        <!-- 경험치 바 -->
        <div class="w-full h-[30px] rounded-md bg-white/20 overflow-hidden relative">
            <div
                class="h-full"
                style="background-color: var(--dlp-purple-100)"
                :style="{ width: barWidth(memberInfo.exp, max) }"
            ></div>
        </div>

        <!-- 경험치 수치 -->
        <div style="font-size: var(--dlp-font-size-text-sm)">
            <template v-if="memberInfo.level === 10">
                max
            </template>
            <template v-else>
                {{ memberInfo.exp }} / {{ max }}
            </template>
        </div>

    </div>
</template>
