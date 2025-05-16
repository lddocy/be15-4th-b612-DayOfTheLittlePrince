<script setup>
import { ref } from 'vue'

const todos = ref([
  {
    task_id: 1,
    content: '요구사항 명세서 작성 끝내기',
    is_checked: 'N',
    project_id: null
  },
  {
    task_id: 2,
    content: '인프런 스프링부트 1강 듣기',
    is_checked: 'N',
    project_id: 5,
    project_title: '투두 프로젝트'
  },
  {
    task_id: 3,
    content: '피그마 작업 마무리하기',
    is_checked: 'N',
    project_id: null
  },
  {
    task_id: 4,
    content: 'ERD 작업 시작하기',
    is_checked: 'N',
    project_id: 5,
    project_title: '투두 프로젝트'
  },
  {
    task_id: 5,
    content: '프론트엔드 초기 세팅 끝내기',
    is_checked: 'Y',
    project_id: null
  }
])

</script>

<template>
  <div class="flex flex-col gap-2 max-h-[300px] overflow-y-auto pr-1">
    <div
        v-for="todo in todos"
        :key="todo.task_id"
        class="group flex items-center justify-between px-3 py-2 rounded-md bg-white/10 backdrop-blur-sm"
    >
      <div class="flex items-center gap-2">
        <input
            type="checkbox"
            v-model="todo.is_checked"
            true-value="Y"
            false-value="N"
            class="w-4 h-4 accent-blue-400 bg-white/20 border-white/30 rounded"
        />
        <span :class="{ 'line-through opacity-60': todo.is_checked === 'Y' }" class="text-sm">
          {{ todo.content }}
        </span>
      </div>

      <!-- 삭제 버튼 (trash.png) -->
      <button class="hidden group-hover:block transition bg-transparent p-0 border-none shadow-none">
        <img
            src="@/assets/icons/trash.png"
            alt="삭제"
            class="w-4 h-4 object-contain"
        />
      </button>
    </div>
  </div>
</template>



