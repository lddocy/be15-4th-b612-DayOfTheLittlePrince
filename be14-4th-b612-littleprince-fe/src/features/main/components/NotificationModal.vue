<script setup>
import { markNotificationAsRead } from '@/features/main/api'
import { ref } from 'vue'

const emit = defineEmits(['cancel', 'level-up']) // level-up: 상위에서 마이페이지 모달 띄움
const props = defineProps({ notifications: Array })

// ✅ 모달 닫기 버튼
const handleCancel = () => {
  emit('cancel') // 상위에서 showNotificationModal.value = false 처리됨
}

// ✅ 알림 클릭 처리
const handleNotificationClick = async (noti) => {
  if (noti.isRead === 'N') {
    try {
      await markNotificationAsRead(noti.noti_id)
      noti.isRead = 'Y'
    } catch (e) {
      console.error('읽음 처리 실패:', e)
    }
  }

  // 알림 카테고리별 분기 처리
  switch (noti.categoryId) {
    case 1:
      console.log('레벨업 알림 → 마이페이지 모달 열기')
      emit('level-up')
      break
    case 2:
      console.log('오늘의 할 일 알림 → 처리 예정')
      break
    default:
      console.log('기타 알림')
  }
}
</script>

<template>
    <div class="w-[500px] h-[75vh] bg-[#E8D0FF]/30 rounded-2xl p-6 flex flex-col text-black shadow-lg overflow-y-auto self-center">

        <!-- 제목 -->
        <div style="color: var(--dlp-lavender-100); font-size: var(--dlp-font-size-text-md); margin-bottom: 15px;">
            알림
        </div>

        <div class="w-full h-[1px] bg-white/20 mb-6" />

      <!--알림 목록-->
        <div class="flex flex-col gap-2 max-h-[300px] overflow-y-auto pr-1 w-[90%] ml-4">
          <div
              v-for="noti in props.notifications"
              :key="noti.noti_id"
              @click="handleNotificationClick(noti)"
              :class="[
              'group flex items-center justify-between px-3 py-2 rounded-xl h-[40px] cursor-pointer',
              noti.isRead === 'Y' ? 'bg-dlp_card/20' : 'bg-dlp_card/40 font-bold',
              'hover:bg-dlp_card_hover/80'
              ]"
                >
            <!-- 변경 -->
            <span class="bg-transparent text-sm text-[#161717] outline-none w-full">
              {{ noti.template }}
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
