<template>
  <button type="button" class="btn btn-box-tool" data-widget="collapse" @click="toggoleFullScreen()">
    <i class="fa" :class="faClass"></i>
  </button>
</template>

<script>
let $ = require('jquery')
import {isInFullScreen, toggleFullScreen, bindFullScreenEvent} from '../../utils/fullscreen'

export default {
  name: 'FullScreenBtn',
  props: {
    id: {
      type: String,
      required: true
    }
  },
  components: {
  },
  data: function () {
    return {
      isFullScreen: false,
      width: 0,
      height: 0
    }
  },
  mounted: function () {
    let self = this
    let targetElement = $(document.getElementById(self.id))
    self.width = targetElement.width()
    self.height = targetElement.height()

    // 绑定全屏切换事件，以适应esc退出全屏的情况
    // BUG IE下面全屏背景是黑色的
    bindFullScreenEvent(function () {
      self.isFullScreen = isInFullScreen()
      if (self.isFullScreen) {
        targetElement.width(document.body.clientWidth - 30)
        targetElement.height(document.body.clientHeight)
      } else {
        targetElement.width(self.width)
        targetElement.height(self.height)
      }
    })
  },
  methods: {
    // 全屏切换
    toggoleFullScreen: function () {
      toggleFullScreen(document.getElementById(this.id))
    }
  },
  computed: {
    // 根据不同状态显示不同的按钮
    faClass: function () {
      return this.isFullScreen ? 'fa-compress' : 'fa-arrows-alt'
    }
  }
}
</script>
