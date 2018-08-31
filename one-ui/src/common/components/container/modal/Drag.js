function getStyle (element, prop) {
  return parseInt(window.getComputedStyle(element)[prop])
}

function setCanSelect (element, isCanSelect) {
  element.onselectstart = function () {
    return isCanSelect
  }
}

function setHeaderStyle (modalHeader) {
  modalHeader.style.cursor = 'move'
}

export function initDrag (modalHeader, modalDialog, modal) {
  // 设置Modal头部样式
  setHeaderStyle(modalHeader)

  let dragable = true
  let offsetX
  let offsetY
  let modalLeft
  let modalHeight

  modalHeader.addEventListener('mousedown', (e) => {
    // 初始化数据
    dragable = true
    offsetX = e.clientX
    offsetY = e.clientY
    modalLeft = getStyle(modalDialog, 'left')
    modalHeight = getStyle(modalDialog, 'top')

    // 设置元素不可选中
    setCanSelect(modalDialog, false)
  }, false)

  modal.addEventListener('mouseup', (e) => {
    dragable = false
    modalLeft = getStyle(modalDialog, 'left')
    modalHeight = getStyle(modalDialog, 'top')

    // 设置元素可以选中
    setCanSelect(modalDialog, true)
  }, false)

  document.addEventListener('mousemove', (e) => {
    if (dragable === true) {
      // 移动模态框
      let clientX = e.clientX
      let clientY = e.clientY
      let left = modalLeft + clientX - offsetX
      let top = modalHeight + clientY - offsetY

      let maxHeight = document.body.clientHeight
      let maxWidth = document.body.clientWidth
      let dialogHeight = modalDialog.clientHeight
      let dialogWidth = modalDialog.clientWidth
      let allowMaxHeight = maxHeight - dialogHeight - 10
      let allowMaxWidth = maxWidth - dialogWidth - 20
      if (left < 0) {
        left = 0
      }
      if (top < 0) {
        top = 0
      }
      if (left > allowMaxWidth) {
        left = allowMaxWidth
      }
      if (top > allowMaxHeight) {
        top = allowMaxHeight
      }

      modalDialog.style.left = left + 'px'
      modalDialog.style.top = top + 'px'
    }
  })
}

export function topCernter (modalDialog) {
  let maxHeight = document.body.clientHeight
  let maxWidth = document.body.clientWidth
  let dialogHeight = modalDialog.clientHeight
  let dialogWidth = modalDialog.clientWidth
  let left = (maxWidth - dialogWidth) / 2
  let top = 80
  let allowMaxHeight = maxHeight - dialogHeight - 10
  if (top > allowMaxHeight) {
    top = allowMaxHeight
  }
  modalDialog.style.left = left + 'px'
  modalDialog.style.top = top + 'px'
}

export default {}
