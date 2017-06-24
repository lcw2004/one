/**
 * Created by lcw20 on 2017-05-20.
 */

const FORM_GROUP = 'form-group'
const INPUT_GROUP = 'input-group'
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

/**
 * 创建一个Icon对象
 * @param iconClass
 * @returns {Element}
 */
function createIconElement (iconClass) {
  let icon = document.createElement('i')
  icon.classList.add(FA)
  icon.classList.add(FORM_CONTROL_FEEDBACK)
  icon.classList.add(iconClass)
  return icon
}

/**
 * 创建一条错误信息
 */
function createErrorSpanElement (errorMsg) {
  let span = document.createElement('span')
  span.classList.add(HELP_BLOCK)
  span.appendChild(document.createTextNode(errorMsg))
  return span
}

/**
 * 添加class
 * @param element
 * @param className
 */
function addClass (element, className) {
  element.classList.add(className)
}

/**
 * 移除class
 * @param element
 * @param className
 */
function removeClass (element, className) {
  element.classList.remove(className)
}

/**
 * 判断元素是否有指定class
 * @param element
 * @param className
 * @returns {boolean}
 */
function hasClass (element, className) {
  return element.classList.contains(className)
}

/**
 * 移除元素的所有子节点
 * @param element
 */
function removeAllChild (element) {
  while (element.firstChild) {
    element.removeChild(element.firstChild)
  }
}

function handlerPass (el, isWithIcon) {
  // 寻找错误挂载节点
  let errorMountElement = getErrorMountElement(el)
  if (!errorMountElement) {
    console.error('找不到错误挂载节点')
    return
  }

  let formGroupElement = getFormGroupElement(errorMountElement)
  if (formGroupElement) {
    removeClass(formGroupElement, HAS_ERROR)
    removeErrorSpan(formGroupElement)
  }

  if (isWithIcon) {
    addSuccessIcon(el)
  }
}

function handlerError (el, isWithIcon, errorMsg) {
  // 寻找错误挂载节点
  let errorMountElement = getErrorMountElement(el)
  if (!errorMountElement) {
    console.error('找不到错误挂载节点')
    return
  }

  let formGroupElement = getFormGroupElement(errorMountElement)
  if (formGroupElement) {
    // 在 form-group下面添加has-error
    addClass(formGroupElement, HAS_ERROR)

    // 找form-group下面是否有help-block节点，如果有，则复用，如果没有，则在错误挂载节点下面添加一个help-block节点
    let helpBlockElement = getHelpBlockInFormGroup(formGroupElement)
    if (helpBlockElement) {
      updateErrorMsg(helpBlockElement, errorMsg)
    } else {
      appendErrorSpan(errorMountElement, errorMsg)
    }
  }
  if (isWithIcon) {
    addDangerIcon(el)
  }
}

function addRequiredClass (el) {
  // 寻找错误挂载节点
  let errorMountElement = getErrorMountElement(el)
  if (!errorMountElement) {
    console.error('找不到错误挂载节点')
    return
  }

  let formGroupElement = getFormGroupElement(errorMountElement)
  if (formGroupElement) {
    let controlLabel = formGroupElement.getElementsByClassName(CONTROL_LABEL)[0]
    addClass(controlLabel, REQUIRED)
  }
}

/**
 * 添加成功Icon
 * @param el
 */
function addSuccessIcon (el) {
  addIcon(el, FA_CHECK)
}

/**
 * 添加失败Icon
 * @param el
 */
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
    parentNode.appendChild(createIconElement(FA_CHECK))
  }
}

/**
 * 添加错误信息
 */
function appendErrorSpan (errorMountElement, errorMsg) {
  // 判断是否已经有错误提示了，有则不添加
  let errorSpanList = errorMountElement.getElementsByClassName(HELP_BLOCK)
  if (errorSpanList.length === 0) {
    errorMountElement.appendChild(createErrorSpanElement(errorMsg))
    return
  }
}

function updateErrorMsg (errorSpan, errorMsg) {
  removeAllChild(errorSpan)
  errorSpan.appendChild(document.createTextNode(errorMsg))
}

/**
 * 移除错误信息
 */
function removeErrorSpan (errorMountElement) {
  let errorSpanList = errorMountElement.getElementsByClassName(HELP_BLOCK)
  if (errorSpanList.length >= 0) {
    for (let i = 0; i < errorSpanList.length; i++) {
      // errorSpanList[i].remove()
      // 之前是移除错误提示，改为将错误提示的文本置空，下一次直接复用
      errorSpanList[i].innerHTML = ''
    }
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
  return checkIcons.length > 0 ? checkIcons[0] : (warningIcons.length > 0 ? warningIcons[0] : null)
}

/**
 * 寻找错误挂载点，即form-control上级的第一个col-*节点
 * el: form-control节点
 * 1. 如果form-control的父节点不是input-group，则form-control的父节点为错误挂载节点
 * 2. 如果是input-group，则取input-group的父节点为错误挂载节点
 */
function getErrorMountElement (formControlElement) {
  let errorMountElement = null
  let parentElement = formControlElement.parentNode
  if (hasClass(parentElement, INPUT_GROUP)) {
    errorMountElement = parentElement.parentNode
  } else {
    errorMountElement = parentElement
  }
  return errorMountElement
}

/**
 * 向上两级寻找form-group节点
 * @param errorMountElement 错误挂载节点
 * @returns {*}
 */
function getFormGroupElement (errorMountElement) {
  let formGroupElement = null
  let parent = errorMountElement.parentNode
  if (hasClass(parent, FORM_GROUP)) {
    formGroupElement = parent
  } else {
    if (hasClass(parent.parentNode, FORM_GROUP)) {
      formGroupElement = parent.parentNode
    }
  }
  return formGroupElement
}

function getHelpBlockInFormGroup (formGroupElement) {
  let helpBlockElement = null
  let helpBlockElementList = formGroupElement.getElementsByClassName(HELP_BLOCK)
  if (helpBlockElementList && helpBlockElementList.length >= 0) {
    helpBlockElement = helpBlockElementList[0]
  }
  return helpBlockElement
}

function handlerErrorBag (errorBag) {
  console.log('---------------------------')
  console.log('start handler error bag')
  console.log(errorBag.errors)
  for (let error of errorBag.errors) {
    const fieldName = error.field
    const errorMsg = error.msg
    const formControlElement = document.getElementsByName(fieldName)
    if (formControlElement) {
      handlerError(formControlElement[0], false, errorMsg)
    }
  }
  console.log('---------------------------')
}

export {handlerPass, handlerError, handlerErrorBag, addRequiredClass}
