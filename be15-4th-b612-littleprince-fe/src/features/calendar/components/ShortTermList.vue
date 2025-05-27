<script setup>
import { ref, computed } from 'vue'
import AISuggestionModal from '@/features/calendar/components/AISuggestionModal.vue'
import { getAiList } from '@/features/calendar/api.js'
import { useAuthStore } from '@/stores/auth'
import { useToast } from 'vue-toastification'

const authStore = useAuthStore()
const toast = useToast()

const props = defineProps({
  selectedDate: String,
  todos: Array,
  editable: Object
})

const emit = defineEmits([
  'update-content',
  'toggle-checked',
  'delete-todo',
  'add-todo',
  'add-suggested-todo',
  'confirm',
  'cancel'
])

const isModalOpen = ref(false)
const aiSuggestions = ref([])
const isLoadingAi = ref(false)

const formattedDate = computed(() =>
    new Date(props.selectedDate).toLocaleDateString('ko-KR', {
      year: 'numeric', month: 'long', day: 'numeric'
    })
)

const handleContentChange = (id, value) => emit('update-content', id, value)
const handleChecked = (id, checked) => emit('toggle-checked', id, checked)
const handleDelete = (id) => emit('delete-todo', id)
const handleAdd = () => emit('add-todo')
const handleSuggestAdd = (content) => emit('add-suggested-todo', content)
const handleConfirm = () => emit('confirm')
const handleCancel = () => emit('cancel')

const fetchAiSuggestions = async () => {
  isLoadingAi.value = true
  try {
    const res = await getAiList(authStore.accessToken)
    aiSuggestions.value = res.data.data.generatePlanList.map(content => ({ content }))
    isModalOpen.value = true
  } catch (err) {
    toast.error('10일 이내에 5개 이상을 작성하셔야합니다.')
  } finally {
    isLoadingAi.value = false
  }
}
</script>

<template>
  <div class="w-[500px] h-[75vh] bg-[#E8D0FF]/30 rounded-2xl p-6 flex flex-col text-black shadow-lg overflow-y-auto self-center">

    <!-- 날짜 -->
    <div class="text-dlp_lavender font-semibold text-xl mb-5">
      {{ formattedDate }}
    </div>

    <div class="w-full h-[1px] bg-white/20 mb-6" />

    <!-- 투두 리스트 -->
    <div class="flex flex-col gap-2 max-h-[300px] overflow-y-auto pr-1 w-[90%] ml-4">
      <div
          v-for="todo in props.todos"
          :key="todo.task_id"
          class="group flex items-center justify-between px-3 py-2 rounded-xl bg-dlp_card/40 hover:bg-dlp_card_hover/80 h-[40px]"
      >
        <div class="flex items-center gap-2 w-full">
          <input
              type="checkbox"
              :checked="todo.is_checked === 'Y'"
              @change="handleChecked(todo.task_id, $event.target.checked)"
              class="w-4 h-4 rounded bg-white/20 border-white/30
                   checked:bg-[#60A5FA] checked:border-[#60A5FA]
                   appearance-none relative cursor-pointer"
          >
          <input
              :value="todo.content"
              @input="handleContentChange(todo.task_id, $event.target.value)"
              :readonly="!props.editable[todo.task_id]"
              :class="[ 'bg-transparent text-sm text-[#161717] outline-none w-full',
                      todo.is_checked === 'Y' ? 'line-through opacity-60' : '',
                      !props.editable[todo.task_id] ? 'cursor-default' : '' ]"
              placeholder="할 일을 입력하세요"
          />
        </div>
        <button
            @click="handleDelete(todo.task_id)"
            class="hidden group-hover:block transition bg-transparent p-0 border-none shadow-none"
        >
          <img src="@/assets/icons/trash.png" alt="삭제" class="w-4 h-4 object-contain" />
        </button>
      </div>
    </div>

    <!-- + 버튼 -->
    <div class="flex gap-2 mt-2 ml-4">
      <button @click="handleAdd"
              class="w-8 h-8 flex items-center justify-center text-lg font-bold
                     bg-[#C9C3E3]/40 hover:bg-[#A49CAC]/60
                     text-black rounded-full border border-white/10 transition">
        +
      </button>
      <button
          @click="fetchAiSuggestions"
          :disabled="isLoadingAi"
          class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-2 py-1 rounded-xl text-sm border border-white/10 transition disabled:opacity-50 disabled:cursor-not-allowed"
      >
        <span v-if="isLoadingAi">생성 중...</span>
        <span v-else>AI 생성하기</span>
      </button>
    </div>

    <!-- 확인/취소 버튼 -->
    <div class="flex justify-end mt-auto gap-2">
      <button @click="handleCancel"
              class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-4 py-1 rounded-xl text-sm border border-white/10 transition">
        취소
      </button>
      <button @click="handleConfirm"
              class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-4 py-1 rounded-xl text-sm border border-white/10 transition">
        확인
      </button>
    </div>

    <!-- AI 모달 -->
    <AISuggestionModal
        :visible="isModalOpen"
        :date="props.selectedDate"
        :suggestion-list="aiSuggestions"
        @close="isModalOpen = false"
        @addTodo="handleSuggestAdd"
    />
  </div>
</template>
