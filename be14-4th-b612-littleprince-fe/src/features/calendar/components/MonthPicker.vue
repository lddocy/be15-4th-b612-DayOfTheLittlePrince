<script setup>
import Flatpickr from 'vue-flatpickr-component'
import 'flatpickr/dist/flatpickr.css'
import monthSelectPlugin from 'flatpickr/dist/plugins/monthSelect'
import 'flatpickr/dist/plugins/monthSelect/style.css'

const props = defineProps({
    modelValue: Date
})

const emit = defineEmits(['update:modelValue', 'change'])

function onChange([date]) {
    emit('update:modelValue', date)
    emit('change', date)
}
</script>

<template>
    <Flatpickr
        :model-value="modelValue"
        @on-change="onChange"
        :config="{
          plugins: [new monthSelectPlugin({ shorthand: false, dateFormat: 'Y-m', altFormat: 'F Y' })],
          altInput: true,
          allowInput: false
    }"
        class="text-2xl font-semibold bg-transparent px-4 py-2 border border-transparent rounded-xl focus:outline-none focus:border-2 focus:border-dlp_lavender/20 hover:border-2 hover:border-dlp_lavender/20 hover:bg-dlp_lavender/10 transition-all duration-200"
    />
</template>
