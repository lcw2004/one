import Vue from 'vue'
import Confirm from './Confirm'

/**
 * 简单弹出一个文本字弹框
 */
let confirmSimple = function (message, onOk, onCancel, okBtnText, cancelBtnText) {
  let propsData = {
    title: '操作确认',
    message: message,
    type: 'default',
    size: 'md',
    onOk: onOk,
    onCancel: onCancel,
    okBtnText: okBtnText,
    cancelBtnText: cancelBtnText
  }

  let ConfirmComponent = Vue.extend(Confirm)
  return new ConfirmComponent({
    el: document.createElement('div'),
    propsData
  })
}

/**
 * 根据配置弹出弹框
 */
let confirmWithConfig = function (config, onOk, onCancel) {
  let propsData = Object.assign(config, {
    onOk: onOk,
    onCancel: onCancel
  })
  let ConfirmComponent = Vue.extend(Confirm)
  return new ConfirmComponent({
    el: document.createElement('div'),
    propsData
  })
}

let confirm = function (arg1, onOk, onCancel) {
  if (typeof arg1 === 'string') {
    return confirmSimple(arg1, onOk, onCancel)
  } else {
    return confirmWithConfig(arg1, onOk, onCancel)
  }
}

export default confirm
