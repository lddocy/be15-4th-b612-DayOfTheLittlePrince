<template>
    <div
        class="fixed inset-0 z-[1000] flex flex-col items-center justify-center font-bold"
        style="background-color: var(--dlp-babypink-100); color: var(--dlp-lavender-100); font-size: var(--dlp-font-size-title-md);"
    >
      <img src="../../assets/images/loading.gif" alt="loading-gif" class="mb-8" />
      <!-- 저희가 가져온 폰트는 로딩 시간이 있어서 제때 안 뜨기 때문에 png 파일을 사용합니다 -->
      <img src="@/assets/images/loading-text.png" alt="loading-text" class="mb-4 w-[500px] h-auto" />
    </div>
</template>