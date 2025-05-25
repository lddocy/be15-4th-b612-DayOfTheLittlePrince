<script setup>
import { ref, onMounted, computed } from 'vue';
import { fetchWeatherRecommendation } from '@/features/main/api.js';
import { useAuthStore } from '@/stores/auth';

const authStore = useAuthStore();
const weatherMain = ref('');
const recommendation = ref('');
const loading = ref(true);
const error = ref('');

const weatherMap = {
    Clear: { label: '맑음', emoji: '☀️' },
    Clouds: { label: '흐림', emoji: '☁️' },
    Rain: { label: '비', emoji: '🌧️' },
    Snow: { label: '눈', emoji: '❄️' },
    Thunderstorm: { label: '천둥번개', emoji: '⛈️' },
    Drizzle: { label: '이슬비', emoji: '🌦️' },
    Mist: { label: '안개', emoji: '🌫️' },
    Fog: { label: '안개', emoji: '🌁' },
    Haze: { label: '실안개', emoji: '🌫️' },
    Smoke: { label: '연기', emoji: '🔥' },
    Dust: { label: '먼지', emoji: '🌪️' },
    Sand: { label: '모래바람', emoji: '🌬️' }
};

const weatherDisplay = computed(() => {
    const info = weatherMap[weatherMain.value] || { label: weatherMain.value, emoji: '🌈' };
    return `${info.label} ${info.emoji}`;
});

onMounted(() => {
    navigator.geolocation.getCurrentPosition(
        async (position) => {
            const token = authStore.accessToken;
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;

            try {
                const { data } = await fetchWeatherRecommendation(token, lat, lon);
                const result = data.data;

                weatherMain.value = result.weatherMain;
                recommendation.value = result.recommendation;
            } catch (e) {
                error.value = '날씨 정보를 불러올 수 없습니다.';
                console.error(e);
            } finally {
                loading.value = false;
            }
        },
        (err) => {
            error.value = '위치 정보를 가져올 수 없습니다.';
            console.error('위치 오류:', err);
            loading.value = false;
        }
    );
});

</script>

<template>
    <div class="weather-container">
        <div class="bubble-box">
            <p class="font-bold mb-2">오늘은 {{ weatherDisplay }}</p>
            <p class="font-medium">어린왕자의 추천: {{ recommendation }}</p>
        </div>
        <div class="bubble-tail" />
    </div>
</template>

<style scoped>
.weather-container {
    position: fixed;
    top: 30px;
    left: 164px;
    z-index: 9999;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}

@media (max-width: 1024px) {
    .weather-container {
        left: 124px;
    }
}
@media (max-width: 768px) {
    .weather-container {
        left: 94px;
    }
}

.bubble-box {
    background-color: var(--dlp-yellow-50);
    padding: 16px 20px;
    border-radius: 16px;
    width: 310px;
    font-size: var(--dlp-font-size-text-md);
}

@media (min-width: 768px) {
    .bubble-box {
        width: 350px;
    }
}
@media (min-width: 1024px) {
    .bubble-box {
        width: 390px;
    }
}

.bubble-tail {
    width: 0;
    height: 0;
    border-left: 12px solid transparent;
    border-right: 12px solid transparent;
    border-top: 14px solid var(--dlp-yellow-50);
    margin-left: 30px;
}
</style>
