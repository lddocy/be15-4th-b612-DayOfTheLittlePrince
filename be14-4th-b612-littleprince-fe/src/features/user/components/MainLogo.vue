<script setup>

import {useRouter} from "vue-router";

const router = useRouter();

const handleMainLogo = async() => {
  await router.push("/login")
}
</script>

<template>
  <div class="main-logo">
    <img src="@/assets/icons/main-logo.png" alt="main-logo" class="logo-img" @click="handleMainLogo"/>
  </div>
</template>

<style scoped>
.main-logo {
  max-width: 240px;
  max-height: 100%;
  cursor: pointer;
}
</style>