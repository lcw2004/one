<template>
<span style="position: relative">
  <i :class="iconClass" @click="isShow = !isShow"></i>

  <OneTransition name="fade">
    <div class="popover bottom" :style="popoverStyle" v-if="isShow">
      <div class="arrow"></div>
      <h3 class="popover-title">{{ title }}</h3>
      <div class="popover-content">
        <slot></slot>
      </div>
    </div>
  </OneTransition>
</span>
</template>

<script>
export default {
  name: 'PopoverContainer',
  props: {
    title: {
      type: String
    },
    width: {
      type: Number,
      default: 500
    }
  },
  data: function () {
    return {
      isShow: false
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
        top: '20px',
        width: this.width + 'px',
        display: this.isShow ? 'block' : 'none',
        maxWidth: '1000px'
      }
    }
  }
}
</script>

<style>
i {
  font-size: 15px;
}
i:hover {
  font-weight: bold;
}
</style>
