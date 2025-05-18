<script setup>
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Calendar from '@/features/calendar/components/Calendar.vue'

const route = useRoute()
const router = useRouter()
const selectedDate = ref(route.params.date)

const todos = ref([
  { task_id: 1, content: '단기 리스트 조회하기', is_checked: 'N' },
  { task_id: 2, content: '인프런 스프링부트 1강 듣기', is_checked: 'N' },
  { task_id: 3, content: '오운완', is_checked: 'N' },
  { task_id: 4, content: '꽃동이 산책 20분 시키기', is_checked: 'N' },
  { task_id: 5, content: '정보처리기사 chap01 끝내기', is_checked: 'Y' }
])

const editable = ref({})

const deleteTodo = (taskId) => {
  if (editable.value[taskId]) {
    todos.value = todos.value.filter(todo => todo.task_id !== taskId)
    delete editable.value[taskId]
  } else {
    alert(`ID ${taskId} 삭제`)
  }
}

const addTodo = () => {
  const newId = Date.now()
  todos.value.push({
    task_id: newId,
    content: '',
    is_checked: 'N'
  })
  editable.value[newId] = true
}

const handleConfirm = () => {
  todos.value = todos.value.filter(todo => todo.content.trim() !== '')
  editable.value = {}
  alert('할 일이 저장되었습니다.')
}

const goBack = () => {
  router.push(`/calendar/${selectedDate.value}`)
}
</script>

<template>
  <div class="w-screen h-screen flex justify-center items-center">

    <div class="flex gap-12 w-[1240px]">

      <!-- 캘린더 -->
      <div class="w-[1000px] h-[90vh] rounded-2xl p-2 shadow-lg overflow-hidden">
        <Calendar />
      </div>

      <!-- 하루 일정 -->
      <div class="w-[500px] h-[75vh] bg-[#E8D0FF]/30 rounded-2xl p-6 flex flex-col text-black shadow-lg overflow-y-auto self-center">

        <!-- 날짜 -->
        <div class="text-dlp_lavender font-semibold text-xl mb-5">
          {{ new Date(selectedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: 'long', day: 'numeric' }) }}
        </div>

        <div class="w-full h-[1px] bg-white/20 mb-6" />

        <!-- 투두 리스트 -->
        <div class="flex flex-col gap-2 max-h-[300px] overflow-y-auto pr-1 w-[90%] ml-4">
          <div
              v-for="todo in todos"
              :key="todo.task_id"
              class="group flex items-center justify-between px-3 py-2 rounded-xl bg-dlp_card/40 hover:bg-dlp_card_hover/80 h-[40px]"
          >
            <div class="flex items-center gap-2 w-full">
              <input
                  type="checkbox"
                  :checked="todo.is_checked === 'Y'"
                  @change="todo.is_checked = $event.target.checked ? 'Y' : 'N'"
                  class="w-4 h-4 rounded bg-white/20 border-white/30
                    checked:bg-[#60A5FA] checked:border-[#60A5FA]
                    appearance-none relative cursor-pointer"
              >
              <input
                  v-model="todo.content"
                  :readonly="!editable[todo.task_id]"
                  :class="[ 'bg-transparent text-sm text-[#161717] outline-none w-full',
                            todo.is_checked === 'Y' ? 'line-through opacity-60' : '',
                            !editable[todo.task_id] ? 'cursor-default' : '' ]"
                  placeholder="할 일을 입력하세요"
              />
            </div>
            <button
                @click="deleteTodo(todo.task_id)"
                class="hidden group-hover:block transition bg-transparent p-0 border-none shadow-none"
            >
              <img src="@/assets/icons/trash.png" alt="삭제" class="w-4 h-4 object-contain" />
            </button>
          </div>
        </div>

        <!-- + 버튼 -->
        <div class="flex gap-2 mt-2 ml-4">
          <button @click="addTodo"
                  class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-2 rounded-xl text-sm border border-white/10 transition">
            +
          </button>
          <button
              class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-2 py-1 rounded-xl text-sm border border-white/10 transition">
            AI 생성하기
          </button>
        </div>

        <!-- 확인/취소 버튼 -->
        <div class="flex justify-end mt-auto gap-2">
          <button @click="goBack"
                  class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-4 py-1 rounded-xl text-sm border border-white/10 transition">
            취소
          </button>
          <button @click="handleConfirm"
                  class="bg-dlp_card/40 hover:bg-dlp_card_hover/80 text-black px-4 py-1 rounded-xl text-sm border border-white/10 transition">
            확인
          </button>
        </div>

      </div>
    </div>
  </div>
</template>

<style>
input[type="checkbox"]:checked::after {
  content: '';
  position: absolute;
  left: 50%;
  top: 50%;
  width: 10px;
  height: 5px;
  border-left: 3px solid white;
  border-bottom: 3px solid white;
  transform: translate(-50%, -60%) rotate(-45deg);
}
</style>