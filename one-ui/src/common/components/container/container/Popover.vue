<template>
  <OneTransition name="fade">
    <div class="popover bottom" :style="popoverStyle">
      <div class="arrow"></div>
      <h3 class="popover-title">{{ title }}</h3>
      <div class="popover-content" style="word-wrap: break-word">
        <slot></slot>
      </div>
    </div>
  </OneTransition>
</template>

<script>
export default {
  name: 'Popover',
  props: {
    title: {
      type: String
    },
    width: {
      type: Number,
      default: 500
    },
    left: {
      type: Number
    }
  },
  computed: {
    popoverStyle: function () {
      return {
        left: this.left + 'px',
        top: '15px',
        width: this.width + 'px',
        display: 'block',
        maxWidth: '1000px'
      }
    }
  }
}
</script>
