<!--Todo: Loading Spinner 만들면 추가 예정.. 그대로 둘지도..-->
<template>
    <div
        class="fixed inset-0 z-[100] flex items-center justify-center font-bold"
        style="background-color: var(--dlp-babypink-40); color: var(--dlp-purple-100); font-size: var(--dlp-font-size-title-md)"
    >
        <slot>
            💫 어린왕자가 당신을 기다리고 있어요...
        </slot>
    </div>
</template>