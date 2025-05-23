<script setup>
import { computed, ref, onMounted} from 'vue';
import { useRoute, useRouter } from 'vue-router';
import ShortTermList from '@/features/calendar/components/ShortTermList.vue'
import MyPageModal from '@/features/user/components/MyPageModal.vue'
import NotificationModal from '@/features/main/components/NotificationModal.vue';
import { getNotifications } from '@/features/main/api';
import { getShortList, createShortTodo, deleteShortTodo, toggleCheck } from '@/features/calendar/api.js'
import { useAuthStore } from '@/stores/auth.js'
import { useToast } from 'vue-toastification';

const router = useRouter();
const route = useRoute();
const toast = useToast();


const authStore = useAuthStore();
const todos = ref([]);
const editable = ref({});
const isModalOpen = ref(false);
const todayDate = new Date().toISOString().slice(0, 10);

const showTodayModal = ref(false);
const showNotificationModal = ref(false);
const showMyPageModal = ref(false)


const closeModals = () => {
    showTodayModal.value = false;
    showNotificationModal.value = false;
};

const handleLevelUp = () => {
  showMyPageModal.value = true
}

const handleShowToday = () => {
  showNotificationModal.value = false
  showTodayModal.value = true
}

const aiSuggestions = ref([]);

const notifications = ref([])
const fetchNotifications = async () => {
  try {
    const res = await getNotifications({ limit: 30, offset: 0 })
    console.log('📦 API 응답 결과:', res)
    const notiList = res?.data?.data?.notifications ?? []
    console.log('🔔 알림 리스트:', notiList)
    notifications.value = notiList.map((n, idx) => ({
      noti_id: n.notificationId,
      content: n.content,
      isRead: n.isRead,
      createdAt: n.createdAt,
      categoryId: n.categoryId
    }))
    console.log('🎯 알림 상태값(vue ref):', notifications.value)

  } catch (err) {
    console.error('알림 불러오기 실패:', err)
  }
}
const unreadCount = computed(() =>
    notifications.value.filter(n => n.isRead === 'N').length
)

const fetchTodayTodos = async () => {
  try {
    const res = await getShortList(authStore.accessToken, todayDate);
    todos.value = res.data.data.shortList.map(item => ({
      task_id: item.taskId,
      content: item.content,
      is_checked: item.isChecked
    }));
  } catch (err) {
    console.error('오늘의 할 일 불러오기 실패:', err);
    toast.error('오늘의 할 일을 불러오지 못했어요.');
  }
};

onMounted(() => {
  fetchNotifications();
  fetchTodayTodos();
})

const addTodo = () => {
    const newId = Date.now()
    todos.value.push({ task_id: newId, content: '', is_checked: 'N' })
    editable.value[newId] = true
};

const deleteTodo = async (taskId) => {
  if (editable.value[taskId]) {
    todos.value = todos.value.filter(todo => todo.task_id !== taskId);
    delete editable.value[taskId];
  } else {
    try {
      await deleteShortTodo(authStore.accessToken, taskId);
      todos.value = todos.value.filter(todo => todo.task_id !== taskId);
      toast.success('할 일이 삭제되었습니다.');
    } catch (err) {
      console.error('삭제 실패:', err);
      toast.error('삭제에 실패했습니다.');
    }
  }
};

const updateTodoContent = (taskId, content) => {
    const target = todos.value.find(todo => todo.task_id === taskId)
    if (target) target.content = content
};

const toggleTodoChecked = async (taskId, checked) => {
  const target = todos.value.find(todo => todo.task_id === taskId);
  if (target) {
    try {
      await toggleCheck(authStore.accessToken, taskId);
      target.is_checked = checked ? 'Y' : 'N';
    } catch (err) {
      console.error('체크 상태 변경 실패:', err);
      toast.error('체크 상태 변경에 실패했어요.');
    }
  }
};

const addSuggestedTodo = (content) => {
    const newId = Date.now()
    todos.value.push({ task_id: newId, content, is_checked: 'N' })
    editable.value[newId] = true
};

const handleConfirm = async () => {
  const newTodos = todos.value.filter(todo => editable.value[todo.task_id]);
  if (newTodos.length === 0) {
    toast.error('저장할 새로운 할 일이 없어요.');
    return;
  }
  try {
    for (const todo of newTodos) {
      await createShortTodo(authStore.accessToken, {
        content: todo.content,
        date: todayDate
      });
    }
    toast.success('할 일이 저장되었습니다.');
    await fetchTodayTodos();
    editable.value = {};
  } catch (err) {
    console.error('저장 실패:', err);
    toast.error('할 일을 저장하는 데 실패했어요.');
  }
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
    route.path === '/' || route.path === '/calendar'
);

</script>

<template>
    <div class="icon-overlay" v-if="isMainOrCalendar">

      <div class="today-container">
        <img
            src="@/assets/icons/notification.png"
            alt="today-list"
            class="icon-img cursor-pointer"
            @click="toggleNotificationModal"
        />
        <div v-if="unreadCount > 0" class="notification-bubble">
          {{ unreadCount > 9 ? '9+' : unreadCount }}
        </div>

        <div v-if="showNotificationModal" class="modal-content" @click.self="closeModals">
          <NotificationModal
              :selected-date="todayDate"
              @cancel="closeModals"
              @level-up="handleLevelUp"
              @show-today="handleShowToday"
              :notifications="notifications"
          />
        </div>
      </div>
        <div class="today-container">
            <img
                src="@/assets/icons/today.png"
                alt="noti-list"
                class="icon-img cursor-pointer"
                @click="toggleTodayModal"
            />
            <div v-if="showTodayModal" class="modal-content z-99" @click.self="closeModals">
                <ShortTermList
                    :selected-date="todayDate"
                    :todos="todos"
                    :editable="editable"
                    :is-modal-open="isModalOpen"
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
    </div>
  <MyPageModal v-if="showMyPageModal" :isOpen="true" @close="showMyPageModal = false" />
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

.notification-bubble {
  position: absolute;
  top: 0;
  right: 0;
  transform: translate(45%, 1%);
  background-color: #FF6B6B;
  color: white;
  font-weight: bold;
  font-size: 0.85rem;
  width: 28px;
  height: 28px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
  font-family: 'Gowun Dodum', sans-serif;
  white-space: nowrap;
}

/* 말풍선 꼬리 제거 */
.notification-bubble::after {
  display: none;
}


</style>