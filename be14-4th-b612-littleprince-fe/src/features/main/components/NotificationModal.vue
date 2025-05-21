<script setup>
const emit = defineEmits(['cancel'])
const props = defineProps({ notifications: Array })

const handleCancel = () => {
    emit('cancel')
}
</script>

<template>
    <div class="w-[500px] h-[75vh] bg-[#E8D0FF]/30 rounded-2xl p-6 flex flex-col text-black shadow-lg overflow-y-auto self-center">

        <!-- 제목 -->
        <div style="color: var(--dlp-lavender-100); font-size: var(--dlp-font-size-text-md); margin-bottom: 15px;">
            알림
        </div>

        <div class="w-full h-[1px] bg-white/20 mb-6" />

        <div class="flex flex-col gap-2 max-h-[300px] overflow-y-auto pr-1 w-[90%] ml-4">
            <div
                v-for="noti in props.notifications"
                :key="noti.noti_id"
                class="group flex items-center justify-between px-3 py-2 rounded-xl bg-dlp_card/40 hover:bg-dlp_card_hover/80 h-[40px]"
            >
                <span class="bg-transparent text-sm text-[#161717] outline-none w-full">
                    {{ noti.content }}
                </span>
            </div>

            <!-- 확인 버튼 -->
            <div class="flex justify-end mt-auto gap-2">
                <button
                    @click="handleCancel"
                    class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-4 py-1 rounded-xl text-sm border border-white/10 transition"
                >
                    확인
                </button>
            </div>
        </div>
    </div>
</template>
