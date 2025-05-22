<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Calendar from '@/features/calendar/components/Calendar.vue'
import ShortTermList from '@/features/calendar/components/ShortTermList.vue'

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
const isModalOpen = ref(false)

const aiSuggestions = ref([
    { content: '어린왕자랑 놀아주기' },
    { content: '바오밥 나무' },
    { content: '푸바오밥주기' },
    { content: '얼른 쉬기' },
    { content: '프론트엔드 초기 세팅 끝내기' }
])

const addTodo = () => {
    const newId = Date.now()
    todos.value.push({ task_id: newId, content: '', is_checked: 'N' })
    editable.value[newId] = true
}

const deleteTodo = (taskId) => {
    if (editable.value[taskId]) {
        todos.value = todos.value.filter(todo => todo.task_id !== taskId)
        delete editable.value[taskId]
    } else {
        alert(`ID ${taskId} 삭제`)
    }
}

const updateTodoContent = (taskId, content) => {
    const target = todos.value.find(todo => todo.task_id === taskId)
    if (target) target.content = content
}

const toggleTodoChecked = (taskId, checked) => {
    const target = todos.value.find(todo => todo.task_id === taskId)
    if (target) target.is_checked = checked ? 'Y' : 'N'
}

const addSuggestedTodo = (content) => {
    const newId = Date.now()
    todos.value.push({ task_id: newId, content, is_checked: 'N' })
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
            <div class="w-[1000px] h-[90vh] rounded-2xl p-2 shadow-lg overflow-hidden">
                <Calendar />
            </div>
            <ShortTermList
                :selected-date="selectedDate"
                :todos="todos"
                :editable="editable"
                :is-modal-open="isModalOpen"
                :ai-suggestions="aiSuggestions"
                @update-content="updateTodoContent"
                @toggle-checked="toggleTodoChecked"
                @delete-todo="deleteTodo"
                @add-todo="addTodo"
                @add-suggested-todo="addSuggestedTodo"
                @confirm="handleConfirm"
                @cancel="goBack"
                @update:isModalOpen="val => isModalOpen = val"
            />
        </div>
    </div>
</template>
