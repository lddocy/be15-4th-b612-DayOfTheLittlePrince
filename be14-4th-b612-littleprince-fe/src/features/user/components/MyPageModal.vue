<script setup>
import { ref, computed, watchEffect, onMounted } from 'vue';
import DeleteAccountModal from '@/features/user/components/DeleteAccountModal.vue';
import { useAuthStore } from '@/stores/auth';
import { fetchMyBadges, fetchMyExp, fetchMyItems, fetchExpHistory } from '@/features/user/api';

import { selectBadge } from '@/features/user/api';
import { toggleItemHidden } from '@/features/user/api';
import { fetchTaskCompletionRate } from '@/features/user/api';
import { useUserStore } from '@/stores/user';
import {useToast} from "vue-toastification";
import {useRouter} from "vue-router";

const authStore = useAuthStore();
const router = useRouter();
const toast = useToast();
const {isOpen, handleDeleteUser} = defineProps({ isOpen: Boolean, handleDeleteUser: Function });
const emit = defineEmits(['close', 'refresh-item-map']);

onMounted(async () => {
  try {
    /* 칭호 데이터 */
    const badgeRes = await fetchMyBadges();
    console.log('fetchMyBadges 응답:', badgeRes);

    titles.value = badgeRes.badges.map((badge) => ({
      badgeId: badge.badgeId,
      label: badge.badgeName,
      description: badge.badgeMean,
      isSelected: badge.isSelected,
    }));


    badgeRes.badges.forEach((badge, index) => {
      if (badge.isSelected === 'Y') {
        selectedTitleIndex.value = index;
        titles.value[index].selected = true;
      }
    });

    /* 경험치 */
    const expRes = await fetchMyExp();
    console.log('fetchMyExp 응답:', expRes);

    currentExp.value = expRes.currentExp;
    maxExp.value = expRes.totalExpToNextLevel;
    currentLevel.value = expRes.currentLevel;
    nextLevel.value = expRes.currentLevel + 1;

    /* 경험치 이력 조회 */
    const historyRes = await fetchExpHistory(authStore.accessToken);
    console.log('fetchExpHistory 응답:', historyRes);

// 응답 구조: { data: { data: { expHistoryDTO: [...] } } }
    const historyList = Array.isArray(historyRes.data?.data?.expHistoryDTO)
        ? historyRes.data.data.expHistoryDTO
        : [];

    expLogs.value = historyList.map(entry => {
      const point = entry.expPoint ?? 0;

      // 날짜는 endDate → createdAt 순으로 우선 사용
      const rawDate = entry.endDate || entry.createdAt || '';
      const date = rawDate
          ? new Date(rawDate).toISOString().slice(0, 10).replace(/-/g, '.')
          : '날짜 없음';

      // title이 존재하고, projectId가 null이 아닐 경우에만 표시
      const title = entry.projectId !== null && entry.title ? ` ${entry.title}` : '';

      return `+${point} exp · ${date}${title}`;
    });

    /* 아이템 */
    const itemRes = await fetchMyItems();
    console.log('fetchMyItems 응답:', itemRes);

    const levelToImageName = {
      1: 'fox',
      2: 'water-well',
      3: 'wine',
      4: 'sheep',
      5: 'flowers',
      6: 'baobab-tree',
      7: 'bigplane',
      8: 'blue-planet',
      9: 'royalty',
      10: 'crown'
    };

    items.value = itemRes
        .filter(item => item.level > 0)
        .map(item => ({
          itemId: item.itemId,
          imagePath: levelToImageName[item.level]
              ? `/itemimages/${item.level}-${levelToImageName[item.level]}.png`
              : null,
          isHidden: item.isHidden,
        }));

    /* 달성률 */
    const rateRes = await fetchTaskCompletionRate();
    console.log('fetchTaskCompletionRate 응답:', rateRes);

    totalRate.value = rateRes.data.totalRate;
    monthlyRate.value = rateRes.data.monthlyRate;


  } catch (e) {
    console.error('데이터 조회 실패:', e);
  }
});


const tabs = ['칭호', '달성률', '경험치'];
const activeTab = ref(null);
const selectedTitleIndex = ref(-1);

// 칭호
const titles = ref([]);

// 달성률
const totalRate = ref(0);
const monthlyRate = ref(0);
const animatedTotalRate = ref(0);
const animatedMonthlyRate = ref(0);

watchEffect(() => {
  if (activeTab.value === '달성률') {
    animateProgress(animatedTotalRate, totalRate.value);
    animateProgress(animatedMonthlyRate, monthlyRate.value);
  }
});

function animateProgress(refValue, target, speed = 10) {
  refValue.value = 0;
  const interval = setInterval(() => {
    if (refValue.value < target) {
      refValue.value += 1;
    } else {
      refValue.value = target;
      clearInterval(interval);
    }
  }, speed);
}
const itemsPerPage = 4;
const currentPage = ref(1); // 0부터 시작

const paginatedItems = computed(() => {
  const start = currentPage.value * itemsPerPage;
  return items.value.slice(start, start + itemsPerPage);
});

const totalPages = computed(() => {
  return Math.ceil(items.value.length / itemsPerPage);
});

function goToPrevPage() {
  if (currentPage.value > 0) currentPage.value--;
}

function goToNextPage() {
  if (currentPage.value < totalPages.value - 1) currentPage.value++;
}


// 경험치 (동적으로 갱신)
const currentExp = ref(0);
const maxExp = ref(0);
const currentLevel = ref(0);
const nextLevel = ref(1);

const expPercent = computed(() =>
    Math.min((currentExp.value / maxExp.value) * 100, 100).toFixed(1)
);

/* 경험치 이력 조회 */
const expLogs = ref([]);

// 사용자 아이템
const items = ref([]);

/* 칭호 선택 함수 */
async function selectTitle(selectedIdx) {
  const selectedBadge = titles.value[selectedIdx];

  try {
    await selectBadge(selectedBadge.badgeId); // 서버에 선택 요청

    titles.value.forEach((t) => {
      t.isSelected = 'N';
    });

    // 선택된 것만 'Y'로 설정
    titles.value[selectedIdx].isSelected = 'Y';

    await userStore.loadMemberInfo();

  } catch (e) {
    console.error('칭호 선택 실패:', e);
  }
}
function selectItem(idx) {
  items.value[idx].selected = !items.value[idx].selected;
}
const showDeleteModal = ref(false);
const MAX_LEVEL = 10; // 만렙

const nextLevelText = computed(() => {
  return currentLevel.value >= MAX_LEVEL
      ? '최고 레벨입니다'
      : `LV.${nextLevel.value}`;
});

/* 아이템 토글 */
async function toggleItemVisibility(idx) {
  const item = items.value[idx];
  try {
    await toggleItemHidden(item.itemId); // 서버에 PATCH 요청
    item.isHidden = item.isHidden === 'Y' ? 'N' : 'Y'; // 상태 반전
    emit('refresh-item-map');
  } catch (e) {
    console.error('아이템 숨김 토글 실패:', e);
  }
}
const userStore = useUserStore();
function onClickTitle(title) {
  userStore.setTitle(title);
}
</script>

<template>
      <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black/40">
          <div
              class="bg-[#f9f9fb] w-[800px] rounded-[32px] p-10 shadow-lg relative border border-white/40">
              <!-- 닫기 버튼 -->
              <button class="absolute top-5 right-6 text-gray-400 text-2xl" @click="$emit('close')">
                  ×
              </button>

              <!-- 타이틀 -->
              <h2 class="text-center text-2xl font-bold text-gray-800 mb-6">마이페이지</h2>

              <!-- 탭 -->
              <div class="flex justify-center gap-4 mb-10">
                  <button
                      v-for="tab in tabs"
                      :key="tab"
                      @click="activeTab = tab"
                      :class="['px-6 py-1.5 rounded-full font-semibold transition text-[#C65D7B]']"
                      :style="
                          activeTab === tab
                              ? 'background-color: #FED2E2; box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25); transform: translateY(1px);'
                              : 'background-color: rgba(254,210,226,0.6); box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25); transform: translateY(-1px);'
                      ">
                      {{ tab }}
                  </button>
              </div>
            <!-- 칭호 탭 -->
            <section v-if="activeTab === '칭호'" class="mb-8">
              <h3 class="font-semibold mb-3 text-lg text-left">⭐ 나의 칭호</h3>

              <!-- 첫 줄 -->
              <div class="flex justify-center flex-wrap gap-3 mb-2">
                <div
                    v-for="(title, idx) in titles.slice(0, 5)"
                    :key="idx"
                    class="relative group"
                >
                  <button
                      @click="selectTitle(idx)"
                      class="px-4 py-2 text-sm transition cursor-pointer rounded-[20px] text-[#5B5B5B]"
                      :style="
                           title.isSelected === 'Y'
                          ? 'background-color: rgba(143,135,241,0.4); box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25);'
                          : 'background-color: rgba(255,252,203,0.4); box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);'
                          "
                  >
                    {{ title.label }}
                  </button>

                  <!-- 툴팁 -->
                  <div
                      class="absolute left-1/2 -translate-x-1/2 bottom-[calc(100%+12px)]
                        w-max max-w-xs px-3 py-2 rounded-xl text-xs text-white
                        bg-[#8F87F1]/80 shadow-lg opacity-0 group-hover:opacity-100
                      transition-opacity duration-200 z-50 text-center pointer-events-none"
                    >
                    {{ title.description }}
                  </div>


                </div>
              </div>

              <!-- 두 번째 줄 -->
              <div class="flex justify-center flex-wrap gap-3">
                <div
                    v-for="(title, idx) in titles.slice(5)"
                    :key="idx + 5"
                    class="relative group"
                >
                  <button
                      @click="selectTitle(idx + 5)"
                      class="px-4 py-2 text-sm transition cursor-pointer rounded-[20px] text-[#5B5B5B]"
                      :style="
                        title.isSelected === 'Y'
                        ? 'background-color: rgba(143,135,241,0.4); box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25);'
                        : 'background-color: rgba(255,252,203,0.4); box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);'
                      "
                  >
                    {{ title.label }}
                  </button>

                  <!-- 툴팁 -->
                  <div
                      class="absolute bottom-full left-1/2 transform -translate-x-1/2 -mb-2 w-max max-w-xs px-3 py-2 rounded-xl text-xs text-white bg-[#8F87F1] shadow-lg opacity-0 group-hover:opacity-100 transition-opacity z-50 text-center"
                  >
                    {{ title.description }}
                  </div>
                </div>
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
                                  :style="`background: conic-gradient(#8F87F1 ${animatedTotalRate * 3.6}deg, #eee ${animatedTotalRate * 3.6}deg); transition: background 1s ease;`"></div>

                              <!-- 도넛 구멍 -->
                              <div class="absolute w-16 h-16 bg-[#f9f9fb] rounded-full z-10"></div>

                              <!-- 텍스트 -->
                              <div class="absolute text-lg font-bold text-[#333] z-20">
                                  {{ totalRate }}%
                              </div>
                          </div>
                          <p class="text-sm text-[#8F87F1] mt-2">총 달성률</p>
                      </div>
                      <!-- 이번 달 달성률 -->
                      <div class="flex flex-col items-center">
                          <div class="relative w-24 h-24 flex items-center justify-center">
                              <div
                                  class="w-full h-full rounded-full"
                                  :style="`background: conic-gradient(#8F87F1 ${animatedMonthlyRate * 3.6}deg, #eee ${animatedMonthlyRate * 3.6}deg); transition: background 1s ease;`"></div>
                              <div class="absolute w-16 h-16 bg-[#f9f9fb] rounded-full z-10"></div>
                              <div class="absolute text-lg font-bold text-[#333] z-20">
                                  {{ monthlyRate }}%
                              </div>
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
                          class="absolute bottom-full left-1/2 transform -translate-x-1/2 mb-3 bg-[#FCEEF3] text-sm text-gray-700 px-4 py-2 rounded-xl whitespace-nowrap shadow transition-opacity duration-300 opacity-0 group-hover:opacity-100 z-50">
                          <ul class="space-y-1">
                              <li v-for="(log, idx) in expLogs" :key="idx">• {{ log }}</li>
                          </ul>
                      </div>

                      <!-- Exp Bar -->
                      <div class="w-full h-6 bg-gray-300 rounded-full overflow-hidden relative">
                          <div
                              class="h-full rounded-full transition-all duration-700"
                              :style="`width: ${expPercent}%; background-color: #FFFCCB;`"></div>

                          <img
                              src="@/assets/icons/star.svg"
                              alt="exp-star"
                              class="absolute w-6 h-6 transition-all duration-700"
                              :style="`left: calc(${expPercent}% - 12px); top: 50%; transform: translateY(-50%);`" />

                        <div
                            class="absolute inset-0 flex items-center justify-center text-sm font-bold text-[#C6A82F]">
                          <template v-if="currentLevel === MAX_LEVEL">
                            max
                          </template>
                          <template v-else>
                            {{ currentExp }} / {{ maxExp }}
                          </template>
                        </div>
                      </div>

                      <!-- 레벨 -->
                      <div class="flex justify-between text-sm text-gray-600 mt-1">
                        <span>LV.{{ currentLevel }}</span>
                        <span>{{ nextLevelText }}</span>
                      </div>
                  </div>
              </section>

            <!-- 나의 아이템 -->
            <section>
              <h3 class="font-semibold mb-3 text-lg text-left">나의 아이템</h3>
              <div class="flex items-center justify-between">
                <!-- ◀ 이전 버튼 -->
                <button
                    class="text-xl text-gray-400 hover:text-gray-600 disabled:opacity-30"
                    @click="goToPrevPage"
                    :disabled="currentPage === 0"
                >
                  ◀
                </button>

                <!-- 4개씩 표시되는 아이템 -->
                <div class="flex gap-6">
                  <button
                      v-for="(item, idx) in paginatedItems"
                      :key="idx"
                      @click="toggleItemVisibility(idx + currentPage * itemsPerPage)"
                      :style="
                      item.isHidden === 'N'
                      ? 'background-color: #FFE5EC; box-shadow: inset 0px 4px 4px rgba(0,0,0,0.25);'
                      : 'background-color: white; box-shadow: 0px 4px 4px rgba(0,0,0,0.25);'
                        "
                          class="w-20 h-20 rounded-2xl flex items-center justify-center transition"
                      >
                  <template v-if="item.imagePath">
                      <img :src="item.imagePath" alt="item" class="w-[95%] h-[95%] object-contain" />
                    </template>
                    <template v-else>
                      <div class="text-sm text-gray-400">아이템 없음</div>
                    </template>
                  </button>
                </div>

                <!-- ▶ 다음 버튼 -->
                <button
                    class="text-xl text-gray-400 hover:text-gray-600 disabled:opacity-30"
                    @click="goToNextPage"
                    :disabled="currentPage === totalPages - 1"
                >
                  ▶
                </button>
              </div>
              <!-- 페이지 번호 -->
              <p class="text-sm text-right text-gray-400 mt-2">
                {{ currentPage + 1 }} / {{ totalPages }}
              </p>
            </section>


            <!-- 회원 탈퇴 -->
              <div class="mt-6 text-right">
                  <button class="text-sm text-gray-500 underline" @click="showDeleteModal = true">
                      회원 탈퇴
                  </button>
              </div>
          </div>
          <DeleteAccountModal
              :isOpen="showDeleteModal"
              @close="showDeleteModal = false"
              @confirm="handleDeleteUser" />
      </div>
  </template>
