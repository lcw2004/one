import Vue from 'vue'
import PhotoSwipe from './PhotoSwipe.vue'

let photoSwipe

/**
 * 打开图片列表
 * @param index 序号
 * @param list 列表
 * @param params
 */
let openList = function (index, list, params) {
  if (!photoSwipe) {
    init()
  }

  photoSwipe.open(index, list, params)
}

/**
 * 打开单个图片
 * @param src
 * @param width
 * @param height
 * @param params
 */
let openImg = function (src, width = 0, height = 0, params) {
  let list = []
  list.push({
    src: src,
    w: width,
    h: height
  })
  openList(0, list, params)
}

/**
 * 关闭，回收资源
 */
let close = function () {
  if (photoSwipe) {
    photoSwipe.done()
    photoSwipe = null
  }
}

/**
 * 初始化组件
 */
let init = function () {
  let PhotoSwipeComponent = Vue.extend(PhotoSwipe)
  photoSwipe = new PhotoSwipeComponent({
    el: document.createElement('div')
  })
  document.body.appendChild(photoSwipe.$el)
}

export default {
  openList,
  openImg,
  close
}
