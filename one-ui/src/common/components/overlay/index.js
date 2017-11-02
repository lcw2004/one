import Vue from 'vue'
import Overlay from './Overlay.vue'

let overlay

let open = function (propsData) {
  let ProgressBarComponent = Vue.extend(Overlay)
  // 如果已经有进度条，则不再显示
  if (!overlay) {
    overlay = new ProgressBarComponent({
      el: document.createElement('div'),
      propsData
    })
  }
}

/**
  打开进度层
*/
let start = function (text) {
  let propsData = {
    text: text
  }
  open(propsData)
}

/**
  关闭进度层
*/
let done = function () {
  if (overlay) {
    overlay.done()
    overlay = null
  }
}

export default {
  start,
  done
}
