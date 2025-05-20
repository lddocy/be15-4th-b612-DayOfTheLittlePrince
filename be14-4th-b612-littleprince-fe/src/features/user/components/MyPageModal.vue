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

        <!-- 첫 줄 -->
        <div class="flex justify-center flex-wrap gap-3 mb-2">
          <button
              v-for="(title, idx) in titles.slice(0, 5)"
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

        <!-- 두 번째 줄 -->
        <div class="flex justify-center flex-wrap gap-3">
          <button
              v-for="(title, idx) in titles.slice(5)"
              :key="idx + 5"
              @click="selectTitle(idx + 5)"
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
          <!-- 총 달성률 -->
          <div class="flex flex-col items-center">
            <div class="relative w-24 h-24 flex items-center justify-center">
              <!-- 도넛 배경 -->
              <div
                  class="w-full h-full rounded-full"
                  :style="`background: conic-gradient(#8F87F1 ${animatedTotalRate * 3.6}deg, #eee ${animatedTotalRate * 3.6}deg); transition: background 1s ease;`"
              ></div>

              <!-- 도넛 구멍 -->
              <div class="absolute w-16 h-16 bg-[#f9f9fb] rounded-full z-10"></div>

              <!-- 텍스트 -->
              <div class="absolute text-lg font-bold text-[#333] z-20">{{ totalRate }}%</div>
            </div>
            <p class="text-sm text-[#8F87F1] mt-2">총 달성률</p>
          </div>
          <!-- 이번 달 달성률 -->
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

        <!-- Exp Bar + 툴팁 -->
        <div class="relative w-full max-w-3xl mx-auto mt-4 group">
          <!-- 툴팁 -->
          <div
              class="absolute bottom-full left-1/2 transform -translate-x-1/2 mb-3
             bg-[#FCEEF3] text-sm text-gray-700 px-4 py-2 rounded-xl
             whitespace-nowrap shadow transition-opacity duration-300
             opacity-0 group-hover:opacity-100 z-50"
          >
            <ul class="space-y-1">
              <li v-for="(log, idx) in expLogs" :key="idx">• {{ log }}</li>
            </ul>
          </div>

          <!-- Exp Bar -->
          <div class="w-full h-6 bg-gray-300 rounded-full overflow-hidden relative">
            <div
                class="h-full rounded-full transition-all duration-700"
                :style="`width: ${expPercent}%; background-color: #FFFCCB;`"
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

          <!-- 레벨 -->
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
        <button
            class="text-sm text-gray-500 underline"
            @click="showDeleteModal = true"
        >
          회원 탈퇴
        </button>

      </div>
    </div>
    <DeleteAccountModal
        :isOpen="showDeleteModal"
        @close="showDeleteModal = false"
        @confirm="handleAccountDeletion"
    />
  </div>
</template>

<script setup>
import { ref, computed, watchEffect } from 'vue'
import DeleteAccountModal from "@/features/user/components/DeleteAccountModal.vue";
import { fetchMyBadges } from '@/features/user/api'

onMounted(async () => {
  try {
    const res = await fetchMyBadges()
    titles.value = res.data.data.badges.map(badge => ({
      label: badge.badgeName,
      selected: false // 이건 착용 여부 체크하는 API 생기면 수정 가능
    }))
  } catch (e) {
    console.error('칭호 조회 실패:', e)
  }
})


defineProps({ isOpen: Boolean })
defineEmits(['close'])

const tabs = ['칭호', '달성률', '경험치']
const activeTab = ref(null)

// 칭호
const titles = ref([
  { label: '조용한 별빛', selected: true },
  { label: '밝은 장미', selected: false },
  { label: '꾸준한 여우', selected: false },
  { label: '성실한 여행자', selected: false },
  { label: '다정한 조용사', selected: false },
  { label: '생각하는 어린왕자', selected: false },
  { label: '지혜로운 행성지기', selected: false },
  { label: '온전한 친구', selected: false },
  { label: '영원한 별의 수호자', selected: false }
])

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
const expPercent = computed(() =>
    Math.min((currentExp.value / maxExp.value) * 100, 100).toFixed(1)
)
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

function selectTitle(selectedIdx) {
  const alreadySelected = titles.value[selectedIdx].selected

  if (alreadySelected) {
    // 이미 선택된 걸 다시 클릭한 경우 → 해제
    titles.value[selectedIdx].selected = false
  } else {
    // 나머지 전부 false로 만들고, 해당 칭호만 true
    titles.value.forEach((t, idx) => {
      t.selected = idx === selectedIdx
    })
  }
}

function selectItem(idx) {
  items.value[idx].selected = !items.value[idx].selected
}

const showDeleteModal = ref(false)
</script>
