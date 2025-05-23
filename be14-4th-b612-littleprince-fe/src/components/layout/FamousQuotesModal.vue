<script setup>
defineProps({
  title: String
});
</script>

<template>
    <div
        class="fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2
           flex flex-col justify-center items-center
           gap-4 p-4 rounded-lg w-[580px] h-[350px]"
        style="background-color: var(--dlp-babypink-100); font-size: var(--dlp-font-size-text-md)"
    >
        <img
            src="@/assets/icons/shooting-star.png"
            alt="shooting-star"
            class="w-20 h-20 mt-4"
        />
        <span class="text-center break-words p-3">{{ title }}</span>
    </div>
</template>



