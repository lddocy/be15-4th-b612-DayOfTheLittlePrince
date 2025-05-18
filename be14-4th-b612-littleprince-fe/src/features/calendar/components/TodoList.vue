<script setup>
const props = defineProps({
  todos: Array,
  editableMap: Object
})

// 임시
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

const addTodo = () => {
  const newId = Date.now()
  props.todos.push({
    task_id: newId,
    content: '',
    is_checked: 'N',
    project_id: null
  })
  props.editableMap[newId] = true
}
</script>

<template>
  <div class="flex flex-col gap-2 max-h-[300px] overflow-y-auto pr-1 w-[90%] ml-4">
    <div
        v-for="todo in todos"
        :key="todo.task_id"
        class="group flex items-center justify-between px-3 py-2 rounded-xl bg-[#C9C3E3]/40 hover:bg-[#A49CAC]/60 h-[40px]"
    >
      <!-- 체크를 했을 때 체크여부를 DB에 바로 반영하는게 아니고 (DB과부화..?
           새 플랜을 등록하거나 체크를 한 이후 확인 버튼을 누르면 새로운 사항을 업데이트 or 등록해주는걸로.. -->
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
            :readonly="!props.editableMap[todo.task_id]"
            :class="[
              'bg-transparent text-sm text-[#161717] outline-none w-full',
              todo.is_checked === 'Y' ? 'line-through opacity-60' : '',
              !props.editableMap[todo.task_id] ? 'cursor-default' : ''
            ]"
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

    <!-- + 버튼 & AI 버튼 -->
    <div class="flex gap-2 mt-2">
      <button @click="addTodo"
              class="bg-[#C9C3E3]/40 hover:bg-[#A49CAC]/60 text-black px-2 rounded-xl text-sm border border-white/10 transition">
        +
      </button>
      <button
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
