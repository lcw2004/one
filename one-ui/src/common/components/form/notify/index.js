import Vue from 'vue'
import Notify from './Notify'

let open = function (propsData) {
  let NotifyComponent = Vue.extend(Notify)
  return new NotifyComponent({
    el: document.createElement('div'),
    propsData
  })
}

let success = function (message) {
  return open({
    message: message,
    type: 'success'
  })
}

let info = function (message) {
  return open({
    message: message,
    type: 'info'
  })
}

let warn = function (message) {
  return open({
    message: message,
    type: 'warning'
  })
}

let danger = function (message) {
  return open({
    message: message,
    type: 'danger'
  })
}

export default {success, info, warn, danger}
