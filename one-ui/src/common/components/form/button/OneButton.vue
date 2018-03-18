<template>
  <button type="button" :class="btnClass" :disabled="loading || disabled" @click="$emit('click')">
    <i class="fa fa-fw fa-spinner fa-spin" v-if="loading"></i> <slot></slot>
  </button>
</template>

<script>
export default {
  name: 'OneButton',
  props: {
    btnClass: {
      type: String,
      default: 'btn btn-primary'
    },
    loading: {
      type: Boolean,
      default: false
    },
    disabled: {
      type: Boolean,
      default: false
    }
  }
}
</script>
