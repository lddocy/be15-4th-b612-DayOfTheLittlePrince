<template>
  <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black/40">
    <div
        class="w-[500px] rounded-[32px] p-8 border border-white/40 relative shadow-lg"
        style="background-color: #f9f9fb"
    >
      <!-- 닫기 버튼 -->
      <button class="absolute top-4 right-5 text-gray-400 text-2xl" @click="$emit('close')">×</button>

      <!-- 타이틀 -->
      <h2 class="text-center text-[30px] font-bold text-[rgba(143,135,241,1)] mb-6">
        정말 떠나시겠어요?
      </h2>

      <!-- 설명 -->
      <p class="text-center text-gray-700 text-[16px] leading-relaxed mb-8">
        회원 탈퇴 시<br />
        모든 정보가 삭제되며 복구는 불가능합니다.
      </p>

      <!-- 버튼 -->
      <div class="flex justify-center gap-4">
        <button
            class="w-[150px] h-[44px] rounded-full text-[14px] font-semibold text-[#C65D7B] bg-[rgba(254,210,226,0.6)] hover:bg-[rgba(254,210,226,1)] shadow-md"
            @click="$emit('close')"
        >
          돌아갈래요
        </button>
        <button
            class="w-[150px] h-[44px] rounded-full text-[14px] font-semibold text-white bg-[rgba(143,135,241,1)] hover:opacity-90 shadow-md"
            @click="$emit('confirm')"
        >
          탈퇴할게요
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  isOpen: Boolean,
})
defineEmits(['close', 'confirm'])
</script>
