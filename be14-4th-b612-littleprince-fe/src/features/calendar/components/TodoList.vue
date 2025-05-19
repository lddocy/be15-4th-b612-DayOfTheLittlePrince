<script setup>
import { nextTick, ref } from 'vue'

const props = defineProps({
  todos: Array,
  editableMap: Object
})

const emit = defineEmits(['request-ai-modal', 'add-suggested-todo'])

const isModalOpen = ref(false)
const aiSuggestions = ref([
  { content: '요구사항 명세서 작성 끝내기' },
  { content: '인프런 스프링부트 1강 듣기' },
  { content: '피그마 작업 마무리하기' },
  { content: 'ERD 작업 시작하기' },
  { content: '프론트엔드 초기 세팅 끝내기' }
])

const listContainerRef = ref(null)

const deleteTodo = (taskId) => {
  if (props.editableMap[taskId]) {
    const index = props.todos.findIndex(todo => todo.task_id === taskId)
    if (index !== -1) {
      props.todos.splice(index, 1)
      delete props.editableMap[taskId]
    }
  } else {
    alert(`ID ${taskId} 삭제`)
  }
}

const addTodo = async () => {
  const newId = Date.now()
  props.todos.push({
    task_id: newId,
    content: '',
    is_checked: 'N',
    project_id: null
  })
  props.editableMap[newId] = true

  await nextTick()
  // 리스트 맨 아래로 스크롤 이동
  if (listContainerRef.value) {
    listContainerRef.value.scrollTop = listContainerRef.value.scrollHeight
  }
}

const addSuggestedTodo = (content) => {
  const newId = Date.now()
  props.todos.push({
    task_id: newId,
    content,
    is_checked: 'N',
    project_id: null
  })
}
</script>

<template>
  <div ref="listContainerRef" class="flex flex-col gap-2 max-h-[300px] overflow-y-auto pr-1 w-[90%] ml-4">
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
        />
        <input
            v-model="todo.content"
            :readonly="!props.editableMap[todo.task_id]"
            :class="[
            'bg-transparent text-sm text-[#161717] outline-none w-full',
            todo.is_checked === 'Y' ? 'line-through opacity-60' : '',
            !props.editableMap[todo.task_id] ? 'cursor-default' : ''
          ]"
            placeholder="할 일을 입력하세요"
        />
        <button
            @click="deleteTodo(todo.task_id)"
            class="hidden group-hover:block transition bg-transparent p-0 border-none shadow-none"
        >
          <img src="@/assets/icons/trash.png" alt="삭제" class="w-5 h-4 object-contain" />
        </button>
      </div>

    </div>

    <!-- + 버튼 & AI 버튼 -->
    <div class="flex gap-2 mt-2">
      <button @click="addTodo"
              class="bg-[#C9C3E3]/40 hover:bg-[#A49CAC]/60 text-black px-2 rounded-xl text-sm border border-white/10 transition">
        +
      </button>
      <button @click="emit('request-ai-modal')"
          class="bg-[#C9C3E3]/40 hover:bg-[#A49CAC]/60 text-black px-2 py-1 rounded-xl text-sm border border-white/10 transition">
        AI 생성하기
      </button>
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
