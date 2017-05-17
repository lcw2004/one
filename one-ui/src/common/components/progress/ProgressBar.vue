<template>
<div class="progress" style="margin: 0px; position: fixed; z-index: 99999; width: 100%; background-color: #8acaef" :class="progressSize">
  <div class="progress-bar" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" :class="progressClass" :style="progressStyle">
    <span class="sr-only">80% Complete</span>
  </div>
</div>
</template>

<script>
import common from '../../utils/common'

export default {
  name: 'Progress',
  props: {
    size: {},
    class: {}
  },
  data: function () {
    return {
      width: 1
    }
  },
  beforeMount () {
    document.body.insertBefore(this.$el, document.body.firstElementChild)
  },
  mounted () {
    this.walk()
  },
  methods: {
    close () {
      this.$destroy()
      this.$el.remove()
    },
    walk () {
      // 随机增长长度
      let width = this.width + common.nextInt(1, 4)
      this.width = width > 98 ? 98 : width

      // 随机时间继续增长
      let timeout = common.nextInt(50, 100)
      this.timer = setTimeout(() => this.walk(), timeout)
    },
    done () {
      // 清除定时器
      clearTimeout(this.timer)

      // 进度条走到 100%
      this.width = 100

      // 500ms后关闭组件
      setTimeout(() => this.close(), 500)
    }
  },
  computed: {
    progressStyle: function () {
      return {
        width: this.width + '%'
      }
    },
    progressClass: function () {
      return this.class
    },
    progressSize: function () {
      return this.size
    }
  }
}
</script>

<style>
.progress-xxxs {
  height: 2px
}
</style>
