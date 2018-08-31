<template>
  <OneTransition name="fadeDown">
    <div class="alert alert-dismissible" :class="style" v-show="isShow" style="margin-bottom: 10px;">
      <button type="button" class="close btn-lg" data-dismiss="alert" aria-hidden="true" @click="close">×</button>
      <i class="icon fa" :class="iconClass"></i>
      {{ message }}
    </div>
  </OneTransition>
</template>

<script>
import $ from 'jquery'

export default {
  name: 'Alert',
  props: {
    message: {
      type: String,
      required: true
    },
    type: {
      type: String,
      required: false,
      default: 'success'
    },
    duration: {
      type: Number,
      required: false,
      default: 3000
    }
  },
  data: function () {
    return {
      isShow: true
    }
  },
  beforeMount () {
    this.init()
  },
  mounted: function () {
    this.timer = setTimeout(() => this.close(), this.duration)
  },
  methods: {
    init () {
      let parent = document.querySelector(`.notifications`)
      if (!parent) {
        parent = document.createElement('div')
        parent.classList.add('notifications')
        document.body.appendChild(parent)
      }
      parent.appendChild(this.$el)
    },
    close: function () {
      this.isShow = false
      this.$destroy()
      $(this.$el).remove()
      clearTimeout(this.timer)
    }
  },
  computed: {
    style: function () {
      return 'alert-' + this.type
    },
    iconClass: function () {
      if (this.type === 'success') {
        return 'fa-check'
      } else if (this.type === 'info') {
        return 'fa-info'
      } else if (this.type === 'warning') {
        return 'fa-warning'
      } else if (this.type === 'danger') {
        return 'fa-ban'
      }
    }
  }
}
</script>

<style lang="less" type="text/less">
  .notifications {
    position: fixed;
    top: 60px;
    right: 10px;
    width: 300px;
    z-index: 10000;
  }
</style>
