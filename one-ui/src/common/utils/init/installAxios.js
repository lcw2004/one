import axios from 'axios'
import Vue from 'vue'
import api from '@api'
import progress from '@components/form/progress'
import notify from '@components/form/notify'
import store from '../../../module/index/vuex'

/**
 * 检查是否是JSON串
 *
 * @param response
 * @returns {boolean}
 */
const isHtml = function (response) {
  // 转为小写解决浏览器兼容性问题
  let contenType = response.headers['content-type'] + ''
  return contenType.indexOf('text/html') >= 0
}

/**
 * 添加过滤器
 */
function addInterceptor () {
  axios.interceptors.request.use(config => {
    progress.start()
    config.headers.common['Authorization'] = store.state.system.token

    console.log('========== interceptors start ==========')
    console.log(config.method + ' : ' + config.url)
    console.log(config)
    return config
  }, error => {
    return Promise.reject(error)
  })

  axios.interceptors.response.use(response => {
    // 如果不是JSON，则跳转到登录界面
    if (!response || isHtml(response)) {
      toLoginPage()
      return
    }

    if (response && response.data) {
      const result = response.data
      if (!result.ok) {
        handlerError(result)
      }
    }
    progress.done()

    console.log(response)
    console.log('========== interceptors end ==========')
    return response
  }, error => {
    console.log('========== http error ==========')
    console.log(error)
    if (error.config.url === '/api/initData' && error.response.status === 504) {
      toLoginPage()
    }
    return Promise.resolve(error.response)
  })
}

/**
 * 跳转到登录页面
 */
function toLoginPage () {
  window.location.href = 'login.html'
}

/**
 * 处理系统异常
 */
function handlerError (result) {
  if (result.code === '999') {
    toLoginPage()
  } else {
    notify.danger(result.message)
  }
}

export default function () {
  // 将系统API绑定到Vue上面
  Vue.prototype.$api = api
  Vue.prototype.$axios = axios

  addInterceptor()
}
