<template>
    <div class="bgm-toggle" @click="toggleAudio">
        <img
            :src="isPlaying ? bgmOn : bgmOff"
            alt="BGM Toggle"
            style="width: 70px; height: 70px; cursor: pointer;"
        />
    </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';

import bgmOn from '@/assets/icons/bgm-on.png';
import bgmOff from '@/assets/icons/bgm-off.png';

const props = defineProps({
    enabled: String
});

const isPlaying = ref(false);
let audio = null;

onMounted(() => {
    audio = new Audio('/audio/background.mp3');
    audio.loop = true;
    audio.volume = 0.5;
    audio.play()
        .then(() => {
            isPlaying.value = true;
        })
        .catch(e => {
            console.warn('자동 재생 실패:', e);
            isPlaying.value = false;
        });
});

onBeforeUnmount(() => {
    if (audio) audio.pause();
});

function toggleAudio() {
    if (!audio) {
        audio = new Audio('/audio/background.mp3');
        audio.loop = true;
        audio.volume = 0.5;
    }

    if (isPlaying.value) {
        audio.pause();
        isPlaying.value = false;
    } else {
        audio.play()
            .then(() => {
                isPlaying.value = true;
            })
            .catch(e => {
                console.warn('재생 실패:', e);
            });
    }
}
</script>
