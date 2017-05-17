import $ from 'jquery'
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

    $(el).iCheck({
      checkboxClass: 'icheckbox_flat-blue',
      radioClass: 'iradio_flat-blue',
      increaseArea: '20%' // optional
    })

    $(el).on('ifChanged', function (event) {
      console.log(event.type + ' callback')
    })

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
