import Vue from 'vue'
import VueResource from 'vue-resource'
// 配置vue-resource
import handlerError from '../handlerError'

function logRequest (request) {
  console.log('-------------------------')
  console.log(request.method + ' : ' + request.url)
}

function logResponse (response) {
  console.log(response.headers)
  console.log(response.body)
  console.log('-------------------------')
}

function initProgressBar () {
  Vue.http.interceptors.push(function (request, next) {
    if (this && this.$progress) {
      this.$progress.start()
    }
    next((response) => {
      logRequest(request)
      logResponse(response)

      // 处理异常（成功都返回200状态码，200+的状态码不处理）
      if (response.status !== 200) {
        handlerError(this, response.status, response.body)
      } else {
        if (isNeedReLogin(response)) {
          window.location.href = 'login.html'
          return
        }

        // 弹框显示错误信息
        let result = response.body
        if (!result.ok && result.message) {
          this.$notify.danger(result.message)
        }
      }

      if (this && this.$progress) {
        this.$progress.done()
      }
      return response
    })
  })
}

/**
 * 是否需要重新登录
 * @param response
 * @returns {boolean} true - 需要，false - 不需要
 */
function isNeedReLogin (response) {
  // 如果接口返回的是网页，是因为shiro将请求跳转到登录页面了
  let contentType = response.headers.get('Content-Type')
  if (contentType.indexOf('text/html') >= 0) {
    return true
  }

  // 判断状态码是否需要重新登录
  let responseBody = response.body
  return !responseBody.ok && responseBody.code === '999'
}

function initVueResource () {
  Vue.use(VueResource)
  initProgressBar()
}

export default initVueResource
