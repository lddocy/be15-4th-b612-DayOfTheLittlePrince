<script setup>
import { computed, ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import ShortTermList from '@/features/calendar/components/ShortTermList.vue'
import NotificationModal from '@/features/main/components/NotificationModal.vue';

const router = useRouter();
const route = useRoute();

const showTodayModal = ref(false);
const showNotificationModal = ref(false);

const closeModals = () => {
    showTodayModal.value = false;
    showNotificationModal.value = false;
};

const todayDate = new Date().toISOString().slice(0, 10);

const todos = ref([
    { task_id: 1, content: '단기 리스트 조회하기', is_checked: 'N' },
    { task_id: 2, content: '인프런 스프링부트 1강 듣기', is_checked: 'N' },
    { task_id: 3, content: '오운완', is_checked: 'N' },
    { task_id: 4, content: '꽃동이 산책 20분 시키기', is_checked: 'N' },
    { task_id: 5, content: '정보처리기사 chap01 끝내기', is_checked: 'Y' }
]);

const editable = ref({});
const isModalOpen = ref(false);

const aiSuggestions = ref([
    { content: '어린왕자랑 놀아주기' },
    { content: '바오밥 나무' },
    { content: '푸바오밥주기' },
    { content: '얼른 쉬기' },
    { content: '프론트엔드 초기 세팅 끝내기' }
]);

const notifications = ref([
    { noti_id: 1, content: '경험치가 +10 증가하였습니다!' },
    { noti_id: 2, content: '오늘의 할 일을 확인해보세요!' },
    { noti_id: 3, content: '아직 남은 할 일이 있어요! 확인해보세요!' },
]);

const addTodo = () => {
    const newId = Date.now()
    todos.value.push({ task_id: newId, content: '', is_checked: 'N' })
    editable.value[newId] = true
};

const deleteTodo = (taskId) => {
    if (editable.value[taskId]) {
        todos.value = todos.value.filter(todo => todo.task_id !== taskId)
        delete editable.value[taskId]
    } else {
        alert(`ID ${taskId} 삭제`)
    }
};

const updateTodoContent = (taskId, content) => {
    const target = todos.value.find(todo => todo.task_id === taskId)
    if (target) target.content = content
};

const toggleTodoChecked = (taskId, checked) => {
    const target = todos.value.find(todo => todo.task_id === taskId)
    if (target) target.is_checked = checked ? 'Y' : 'N'
};

const addSuggestedTodo = (content) => {
    const newId = Date.now()
    todos.value.push({ task_id: newId, content, is_checked: 'N' })
};

const handleConfirm = () => {
    todos.value = todos.value.filter(todo => todo.content.trim() !== '')
    editable.value = {}
    alert('할 일이 저장되었습니다.')
};

const goBack = () => {
    showTodayModal.value = false
    router.push(`/`)
};

const toggleTodayModal = () => {
    showTodayModal.value = !showTodayModal.value
    if (showTodayModal.value) showNotificationModal.value = false
};

const toggleNotificationModal = () => {
    showNotificationModal.value = !showNotificationModal.value
    if (showNotificationModal.value) showTodayModal.value = false
};

const isMainOrCalendar = computed(() =>
    route.path === '/' || route.path.startsWith('/calendar')
);

</script>

<template>
    <div class="icon-overlay" v-if="isMainOrCalendar">

        <div class="today-container">
            <img
                src="@/assets/icons/notification.png"
                class="icon-img cursor-pointer"
                @click="toggleNotificationModal"
            />
            <div v-if="showNotificationModal" class="modal-content" @click.self="closeModals">
                    <NotificationModal
                        :selected-date="todayDate"
                        @cancel="closeModals"
                        :notifications="notifications"
                    />
            </div>
        </div>

        <div class="today-container">
            <img
                src="@/assets/icons/today.png"
                class="icon-img cursor-pointer"
                @click="toggleTodayModal"
            />
            <div v-if="showTodayModal" class="modal-content" @click.self="closeModals">
                <ShortTermList
                    :selected-date="todayDate"
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
                    @update:isModalOpen="val => isModalOpen.value = val"
                />
            </div>
        </div>
    </div>
</template>

<style scoped>
.icon-overlay {
    position: fixed;
    top: 20px;
    right: 20px;
    z-index: 10;
    display: flex;
    flex-direction: column;
    gap: 10px;
    pointer-events: auto;

}
.today-container {
    position: relative;
}
.modal-content {
    position: absolute;
    top: 0;
    right: 60%;
    padding: 1rem;
    z-index: 100;
    width: 540px;
    max-height: 80vh;
    overflow-y: auto;
}
.icon-img {
    width: 4rem;
    height: 4rem;
}

@media (min-width: 768px) {
    .icon-img {
        width: 5rem;
        height: 5rem;
    }
}
@media (min-width: 1024px) {
    .icon-img {
        width: 6rem;
        height: 6rem;
    }
}

</style>