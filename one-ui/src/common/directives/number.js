function onlyNumber (value) {
  // 得到第一个字符是否为负号
  var firstChar = value.charAt(0)
  // 先把非数字的都替换掉，除了数字和.
  value = value.replace(/[^\d\\.]/g, '')
  // 必须保证第一个为数字而不是.
  value = value.replace(/^\./g, '')
  // 保证只有出现一个.而没有多个.
  value = value.replace(/\.{2,}/g, '.')
  // 保证.只出现一次，而不能出现两次以上
  value = value.replace('.', '$#$').replace(/\./g, '').replace('$#$', '.')
  // 如果第一位是负号，则允许添加
  if (firstChar === '-') {
    value = '-' + value
  }
  return value
}

function limitLength (value, maxLength) {
  let newValue = value
  if (maxLength && value && value.length > maxLength) {
    newValue = value.slice(0, maxLength)
  }
  return newValue
}

export default {
  bind: function (el, binding, vnode) {
    console.log('---------------------------------')
    console.log('bind')
    el.handler = function () {
      el.value = onlyNumber(el.value)
    }
    el.addEventListener('input', el.handler)
    console.log('---------------------------------')
  },
  inserted: function (el, binding, vnode) {
    console.log('---------------------------------')
    console.log('inserted')
    console.log(binding)
    console.log('vnode keys: ' + Object.keys(vnode).join(', '))
    console.log('---------------------------------')
  },
  update: function (el, binding, vnode) {
    console.log('---------------------------------')
    console.log('update')
    console.log(binding)
    if (!binding.value) {
      return
    }
    let newValue = onlyNumber(el.value)
    el.value = limitLength(newValue, binding.value.maxLength)
    console.log('---------------------------------')
  },
  unbind: function (el) {
    el.removeEventListener('input', el.handler)
  }
}
