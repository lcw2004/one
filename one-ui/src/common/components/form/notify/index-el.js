import { Notification } from 'element-ui'

let duration = 3000
let position = 'bottom-right'

let success = function (message) {
  Notification.success({
    title: '成功',
    message: message,
    duration: duration,
    position: position
  })
}

let info = function (message) {
  Notification.info({
    title: '消息',
    message: message,
    duration: duration,
    position: position
  })
}

let warn = function (message) {
  Notification.warning({
    title: '警告',
    message: message,
    duration: duration,
    position: position
  })
}

let danger = function (message) {
  Notification.error({
    title: '错误',
    message: message,
    duration: duration,
    position: position
  })
}

export default {success, info, warn, danger}
