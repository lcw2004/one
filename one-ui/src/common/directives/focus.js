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
    console.log('---------------------------------')
  },
  update: function (el, binding, vnode) {
    console.log('---------------------------------')
    console.log(binding)
    console.log('---------------------------------')
  },
  unbind: function () {
  }
}
