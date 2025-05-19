<script setup>
import { ref, watch } from 'vue'
import Flatpickr from 'vue-flatpickr-component'
import 'flatpickr/dist/flatpickr.css'
/* 요거 먼지 잘 모르겠어요.. */
import monthSelectPlugin from 'flatpickr/dist/plugins/monthSelect'
import 'flatpickr/dist/plugins/monthSelect/style.css'

const props = defineProps({
  modelValue: Date
})
const emit = defineEmits(['update:modelValue', 'change'])

const current = ref(props.modelValue)

watch(() => props.modelValue, (newVal) => {
  if (newVal?.getTime?.() !== current.value?.getTime?.()) {
    current.value = newVal
  }
})

function onChange([date]) {
  if (!date) return
  if (date.getTime() !== props.modelValue?.getTime?.()) {
    emit('update:modelValue', date)
    emit('change', date)
  }
}
</script>

<template>
  <Flatpickr
      :model-value="current"
      @on-change="onChange"
      :config="{
      plugins: [new monthSelectPlugin({ shorthand: false, dateFormat: 'Y-m', altFormat: 'F Y' })],
      altInput: true,
      allowInput: false
    }"
      class="text-2xl font-semibold bg-transparent px-4 py-2 border border-transparent rounded-xl focus:outline-none focus:border-2 focus:border-dlp_lavender/20 hover:border-2 hover:border-dlp_lavender/20 hover:bg-dlp_lavender/10 transition-all duration-200"
  />
</template>
