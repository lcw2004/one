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
    props: {
      title: {
        type: String
      },
      width: {
        type: Number,
        default: 500
      }
    },
    methods: {
      getIconWidth: function () {
        return 9
      }
    },
    computed: {
      iconClass: function () {
        return ['fa', 'fa-fw', this.isShow ? 'fa-angle-down' : 'fa-angle-right']
      },
      popoverStyle: function () {
        let left = this.getIconWidth() - this.width / 2
        return {
          left: left + 'px',
          top: '5px',
          width: this.width + 'px',
          display: 'block',
          maxWidth: '1000px'
        }
      }
    }
  }
</script>
