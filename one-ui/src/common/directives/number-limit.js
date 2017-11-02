export default {
  bind: function (el, binding, vnode) {
    console.log('---------------------------------')
    console.log('bind')
    console.log(binding)
    console.log('vnode keys: ' + Object.keys(vnode).join(', '))
    console.log('---------------------------------')
  },
  inserted: function (el, binding, vnode) {
    console.log('---------------------------------')
    console.log('inserted')
    console.log(binding)
    console.log('vnode keys: ' + Object.keys(vnode).join(', '))
    limit(el, binding.value.min, binding.value.max)
    console.log('---------------------------------')
  },
  update: function (el, binding, vnode) {
    console.log('---------------------------------')
    console.log(binding)
    console.log(binding.value.min + ' - ' + binding.value.max)
    console.log(el.value)
    limit(el, binding.value.min, binding.value.max)
    console.log('---------------------------------')
  },
  unbind: function () {
  }
}

function limit (el, min = 0, max = 100) {
  let value = el.value
  if (value < min) {
    el.value = min
  } else if (value > max) {
    el.value = max
  }
}
