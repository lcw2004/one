import {handlerPass, handlerError, addRequiredClass} from '../utils/error-render'

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
    if (binding.modifiers.r) {
      addRequiredClass(el)
    }
    console.log('---------------------------------')
  },
  update: function (el, binding, vnode) {
    console.log('---------------------------------')
    console.log('update')
    console.log(binding)

    const bindName = el.getAttribute('name')
    if (!bindName) {
      console.error('Can not find name of element')
      return
    }
    console.log('Handler error of [' + name + ']')

    const isWithIcon = binding.modifiers.i
    const vm = vnode.context
    const isFailed = vm.errors.has(bindName)
    const field = vm.fields[bindName]
    const isPass = field.touched && field.valid
    console.log(vm.errors)

    if (isFailed) {
      let errorMsg = vm.errors.first(bindName)
      handlerError(el, isWithIcon, errorMsg)
    } else if (isPass) {
      handlerPass(el, isWithIcon)
    }

    console.log('---------------------------------')
  },
  unbind: function () {
  }
}

