import Vue from 'vue'
import VueResource from 'vue-resource'

// 配置vue-resource
import handlerError from '../handlerError'

function logRequest (request) {
  console.log('-------------------------')
  console.log('url : ' + request.url)
}

function logResponse (response) {
  console.log('status : ' + response.status)
  console.log(response.body)
  console.log('-------------------------')
}

function initProgressBar () {
  Vue.http.interceptors.push(function (request, next) {
    this.$progress.start()
    next(function (response) {
      logRequest(request)
      logResponse(response)

      // 处理异常（成功都返回200状态码，200+的状态码不处理）
      if (response.status !== 200) {
        handlerError(this, response.status, response.body)
      } else {
        let result = response.body
        if (!result.ok && result.message) {
          this.$notify.danger(result.message)
        }
      }

      this.$progress.done()
      return response
    })
  })
}

function initVueResource () {
  Vue.use(VueResource)
  initProgressBar()
}

export default initVueResource
