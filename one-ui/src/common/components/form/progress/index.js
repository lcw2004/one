import Vue from 'vue'
import ProgressBar from './ProgressBar'

let progressBar

let start = function () {
  // 具体的样式可以参考adminlte的进度条示例
  // class: ['progress-bar-red', 'progress-bar-striped']
  let propsData = {
    size: ['progress-xxxs', 'active'],
    barClass: ['progress-bar-primary']
  }
  let ProgressBarComponent = Vue.extend(ProgressBar)

  // 如果已经有进度条，则不再显示
  if (!progressBar) {
    progressBar = new ProgressBarComponent({
      el: document.createElement('div'),
      propsData
    })
  }
}

let done = function () {
  if (progressBar) {
    progressBar.done()
    progressBar = null
  }
}

export default {
  start,
  done
}
