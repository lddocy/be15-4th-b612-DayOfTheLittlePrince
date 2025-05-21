<template>
  <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black/40">
    <div class="bg-[#f9f9fb] w-[800px] rounded-[32px] p-10 shadow-lg relative border border-white/40">
      <!-- 닫기 버튼 -->
      <button class="absolute top-5 right-6 text-gray-400 text-2xl" @click="$emit('close')">×</button>

      <!-- 타이틀 -->
      <h2 class="text-center text-2xl font-bold text-gray-800 mb-6">마이페이지</h2>

      <!-- 탭 -->
      <div class="flex justify-center gap-4 mb-10">
        <button
            v-for="tab in tabs"
            :key="tab"
            @click="activeTab = tab"
            :class="[
            'px-6 py-1.5 rounded-full font-semibold transition text-[#C65D7B]'
          ]"
            :style="activeTab === tab
            ? 'background-color: #FED2E2; box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25); transform: translateY(1px);'
            : 'background-color: rgba(254,210,226,0.6); box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25); transform: translateY(-1px);'"
        >
          {{ tab }}
        </button>
      </div>

      <!-- 칭호 탭 -->
      <section v-if="activeTab === '칭호'" class="mb-8">
        <h3 class="font-semibold mb-3 text-lg text-left">⭐ 나의 칭호</h3>
        <div class="flex justify-center flex-wrap gap-3">
          <button
              v-for="(title, idx) in titles"
              :key="idx"
              @click="selectTitle(idx)"
              class="px-4 py-2 text-sm transition cursor-pointer rounded-[20px] text-[#5B5B5B]"
              :style="title.selected
              ? 'background-color: rgba(143,135,241,0.4); box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25);'
              : 'background-color: rgba(255,252,203,0.4); box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);'"
          >
            {{ title.label }}
          </button>
        </div>
      </section>

      <!-- 달성률 탭 -->
      <section v-if="activeTab === '달성률'" class="mb-8">
        <h3 class="font-semibold mb-6 text-lg text-left">⭐ 나의 달성률</h3>
        <div class="flex justify-center gap-16 mb-8">
          <div class="flex flex-col items-center">
            <div class="relative w-24 h-24 flex items-center justify-center">
              <div
                  class="w-full h-full rounded-full"
                  :style="`background: conic-gradient(#8F87F1 ${animatedTotalRate * 3.6}deg, #eee ${animatedTotalRate * 3.6}deg); transition: background 1s ease;`"
              ></div>
              <div class="absolute w-16 h-16 bg-[#f9f9fb] rounded-full z-10"></div>
              <div class="absolute text-lg font-bold text-[#333] z-20">{{ totalRate }}%</div>
            </div>
            <p class="text-sm text-[#8F87F1] mt-2">총 달성률</p>
          </div>
          <div class="flex flex-col items-center">
            <div class="relative w-24 h-24 flex items-center justify-center">
              <div
                  class="w-full h-full rounded-full"
                  :style="`background: conic-gradient(#8F87F1 ${animatedMonthlyRate * 3.6}deg, #eee ${animatedMonthlyRate * 3.6}deg); transition: background 1s ease;`"
              ></div>
              <div class="absolute w-16 h-16 bg-[#f9f9fb] rounded-full z-10"></div>
              <div class="absolute text-lg font-bold text-[#333] z-20">{{ monthlyRate }}%</div>
            </div>
            <p class="text-sm text-[#8F87F1] mt-2">이번 달 달성률</p>
          </div>
        </div>
      </section>

      <!-- 경험치 탭 -->
      <section v-if="activeTab === '경험치'" class="mb-10">
        <h3 class="font-semibold mb-6 text-lg text-left">⭐ 나의 경험치</h3>
        <div class="relative w-full max-w-3xl mx-auto mt-4 group">
          <div class="w-full h-6 bg-gray-300 rounded-full overflow-hidden relative">
            <div
                class="h-full rounded-full transition-all duration-700"
                :style="`width:${expPercent}%; background-color: #FFFCCB;`"
            ></div>
            <img
                src="@/assets/icons/star.svg"
                alt="exp-star"
                class="absolute w-6 h-6 transition-all duration-700"
                :style="`left: calc(${expPercent}% - 12px); top: 50%; transform: translateY(-50%);`"
            />
            <div class="absolute inset-0 flex items-center justify-center text-sm font-bold text-[#C6A82F]">
              {{ currentExp }} / {{ maxExp }}
            </div>
          </div>
          <div class="flex justify-between text-sm text-gray-600 mt-1">
            <span>LV.1</span>
            <span>LV.2</span>
          </div>
        </div>
      </section>

      <!-- 나의 아이템 -->
      <section>
        <h3 class="font-semibold mb-3 text-lg text-left">나의 아이템</h3>
        <div class="flex items-center justify-between">
          <button class="text-xl text-gray-400 hover:text-gray-600">◀</button>
          <div class="flex gap-6">
            <button
                v-for="(item, idx) in items"
                :key="idx"
                @click="selectItem(idx)"
                :style="item.selected ? 'background-color: #FFE5EC; box-shadow: inset 0px 4px 4px rgba(0,0,0,0.25);' : 'background-color: white; box-shadow: 0px 4px 4px rgba(0,0,0,0.25);'"
                class="w-20 h-20 rounded-2xl flex items-center justify-center transition"
            >
              <template v-if="item.emoji">
                <div class="text-2xl">{{ item.emoji }}</div>
              </template>
              <template v-else>
                <div class="text-sm text-gray-400">아이템 없음</div>
              </template>
            </button>
          </div>
          <button class="text-xl text-gray-400 hover:text-gray-600">▶</button>
        </div>
        <p class="text-sm text-right text-gray-400 mt-2">1/3</p>
      </section>

      <!-- 회원 탈퇴 -->
      <div class="mt-6 text-right">
        <button class="text-sm text-gray-500 underline" @click="showDeleteModal = true">회원 탈퇴</button>
      </div>
    </div>
    <DeleteAccountModal :isOpen="showDeleteModal" @close="showDeleteModal = false" @confirm="handleAccountDeletion" />
  </div>
</template>

<script setup>
import { ref, computed, watchEffect, onMounted } from 'vue'
import DeleteAccountModal from '@/features/user/components/DeleteAccountModal.vue'
import { fetchMyBadges } from '@/features/user/api'
import { useAuthStore } from '@/stores/auth'


defineProps({ isOpen: Boolean })
defineEmits(['close'])

const tabs = ['칭호', '달성률', '경험치']
const activeTab = ref('칭호')

// 칭호
const titles = ref([])

onMounted(async () => {
  const authStore = useAuthStore()               // ✅ 여기서 store 사용
  console.log('accessToken:', authStore.accessToken)

  try {
    const data = await fetchMyBadges()
    console.log('칭호 API 응답:', data)

    titles.value = data.badges.map(badge => ({
      label: badge.badgeName,
      selected: false
    }))
  } catch (e) {
    console.error('칭호 조회 실패:', e)
  }
})




function selectTitle(selectedIdx) {
  const alreadySelected = titles.value[selectedIdx].selected
  if (alreadySelected) {
    titles.value[selectedIdx].selected = false
  } else {
    titles.value.forEach((t, idx) => {
      t.selected = idx === selectedIdx
    })
  }
}

// 달성률
const totalRate = ref(75)
const monthlyRate = ref(87)
const animatedTotalRate = ref(0)
const animatedMonthlyRate = ref(0)

watchEffect(() => {
  if (activeTab.value === '달성률') {
    animateProgress(animatedTotalRate, totalRate.value)
    animateProgress(animatedMonthlyRate, monthlyRate.value)
  }
})

function animateProgress(refValue, target, speed = 10) {
  refValue.value = 0
  const interval = setInterval(() => {
    if (refValue.value < target) {
      refValue.value += 1
    } else {
      refValue.value = target
      clearInterval(interval)
    }
  }, speed)
}

// 경험치
const currentExp = ref(430)
const maxExp = ref(600)
const expPercent = computed(() => Math.min((currentExp.value / maxExp.value) * 100, 100).toFixed(1))
const expLogs = [
  '5 exp · 2025.05.20 일기 완료',
  '10 exp · 2025.05.21 10개 완료',
  '30 exp · 2025.05.22 마이페이지 진입'
]

// 아이템
const items = ref([
  { emoji: '🌸', selected: false },
  { emoji: '🌼', selected: false },
  { emoji: '🌻', selected: false },
  { emoji: '🌷', selected: false },
  { emoji: '', selected: false }
])

function selectItem(idx) {
  items.value[idx].selected = !items.value[idx].selected
}

const showDeleteModal = ref(false)

function handleAccountDeletion() {
  console.log('회원 탈퇴 처리 실행됨 (임시 로직)')
}
</script>
