export default {
  bind: function (el, binding, vnode) {
    console.log('---------------------------------')
    console.log('bind')
    console.log(binding)
    console.log('vnode keys: ' + Object.keys(vnode).join(', '))

    if (binding.modifiers.r) {
      addRequiredClass(el)
    }

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

    let isWithIcon = binding.modifiers.i

    let bindName = binding.value
    let vm = vnode.context
    // failed 失败一次之后触发，errors 实时触发
    let isFailed = vm.fields.failed(bindName) || vm.errors.has(bindName)
    let isPassed = vm.fields.passed(bindName)

    console.log(vm.errors)
    if (isFailed) {
      let errorMsg = vm.errors.first(bindName)
      handlerError(el, isWithIcon, errorMsg)
      console.log('valid failed')
      console.log(errorMsg)
    } else if (isPassed) {
      console.log('valid pass')
      handlerPass(el, isWithIcon)
    }

    console.log('---------------------------------')
  },
  unbind: function () {
  }
}

const FORM_CONTROL = 'form-control'
const CONTROL_LABEL = 'control-label'
const HELP_BLOCK = 'help-block'
const HAS_ERROR = 'has-error'
// const HAS_SUCCESS = 'has-success'
const HAS_FEEDBACK = 'has-feedback'
const FA = 'fa'
const FA_WARNING = 'fa-warning'
const FA_CHECK = 'fa-check'
const FORM_CONTROL_FEEDBACK = 'form-control-feedback'
const REQUIRED = 'required'

function handlerPass (el, isWithIcon) {
  removeClass(el, HAS_ERROR)
  removeErrorSpan(el)
  if (isWithIcon) {
    addSuccessIcon(el)
  }
}

function handlerError (el, isWithIcon, errorMsg) {
  addClass(el, HAS_ERROR)
  appendErrorSpan(el, errorMsg)
  if (isWithIcon) {
    addDangerIcon(el)
  }
}

function addRequiredClass (el) {
  let controlLabel = el.getElementsByClassName(CONTROL_LABEL)[0]
  addClass(controlLabel, REQUIRED)
}

function addSuccessIcon (el) {
  addIcon(el, FA_CHECK)
}
function addDangerIcon (el) {
  addIcon(el, FA_WARNING)
}

/**
* 添加Icon
*/
function addIcon (el, iconClass) {
  // 寻找错误挂载点
  let formControl = getIconMountElement(el)
  if (!formControl) {
    console.error('can not find form-control where error mount')
    return
  }

  addClass(el, HAS_FEEDBACK)
  let parentNode = formControl.parentNode
  let iconElement = getErrorIcon(parentNode)
  if (iconElement) {
    removeClass(iconElement, FA_CHECK)
    removeClass(iconElement, FA_WARNING)
    addClass(iconElement, iconClass)
  } else {
    parentNode.appendChild(createIcon(FA_CHECK))
  }
}

/**
* 添加错误信息
*/
function appendErrorSpan (el, errorMsg) {
  // 寻找错误挂载点
  let formControl = getErrorMountElement(el)
  if (!formControl) {
    console.error('can not find form-control where error mount')
    console.error('error message:' + errorMsg)
    return
  }

  // 判断是否已经有错误提示了，有则不添加
  let parentNode = formControl.parentNode
  let errorSpanList = parentNode.getElementsByClassName(HELP_BLOCK)
  if (errorSpanList.length === 0) {
    parentNode.appendChild(createErrorSpan(errorMsg))
    return
  }

  // 更新已有的错误提示
  let errorSpan = errorSpanList[0]
  removeAllChild(errorSpan)
  errorSpan.appendChild(document.createTextNode(errorMsg))
}

/**
* 移除错误信息
*/
function removeErrorSpan (el) {
  let formControl = getErrorMountElement(el)
  if (!formControl) {
    console.error('can not find form-control where error mount')
    return
  }

  let parentNode = formControl.parentNode
  let errorSpanList = parentNode.getElementsByClassName(HELP_BLOCK)
  if (errorSpanList.length >= 0) {
    for (let i = 0; i < errorSpanList.length; i++) {
      // errorSpanList[i].remove()
      // 之前是移除错误提示，改为将错误提示的文本置空，下一次直接复用
      errorSpanList[i].innerHTML = ''
    }
  }
}

/**
* 寻找错误挂载点
*/
function getErrorMountElement (el) {
  let formControl = el.getElementsByClassName(FORM_CONTROL)
  if (formControl.length > 0) {
    return formControl[0].parentNode
  } else {
    return null
  }
}

/**
* 寻找Icon挂载点
*/
function getIconMountElement (el) {
  let formControl = el.getElementsByClassName(FORM_CONTROL)
  if (formControl.length > 0) {
    return formControl[0]
  } else {
    return null
  }
}

/**
* 获取表单上的Icon
*/
function getErrorIcon (parentNode) {
  let checkIcons = parentNode.getElementsByClassName(FA_CHECK)
  let warningIcons = parentNode.getElementsByClassName(FA_WARNING)
  let icon = checkIcons.length > 0 ? checkIcons[0] : (warningIcons.length > 0 ? warningIcons[0] : null)
  return icon
}

/**
* 创建一条错误信息
*/
function createErrorSpan (errorMsg) {
  let span = document.createElement('span')
  span.classList.add(HELP_BLOCK)
  span.appendChild(document.createTextNode(errorMsg))
  return span
}

function createIcon (iconClass) {
  let icon = document.createElement('i')
  icon.classList.add(FA)
  icon.classList.add(FORM_CONTROL_FEEDBACK)
  icon.classList.add(iconClass)
  return icon
}

function addClass (element, className) {
  element.classList.add(className)
}

function removeClass (element, className) {
  element.classList.remove(className)
}

function removeAllChild (element) {
  while (element.firstChild) {
    element.removeChild(element.firstChild)
  }
}
