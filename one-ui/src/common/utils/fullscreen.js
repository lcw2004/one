// 文档 https://developer.mozilla.org/en-US/docs/Web/API/Fullscreen_API#AutoCompatibilityTable

/**
 * 全屏显示
 */
function fullScreen (element) {
  if (element.requestFullscreen) {
    element.requestFullscreen()
  } else if (element.mozRequestFullScreen) {
    element.mozRequestFullScreen()
  } else if (element.msRequestFullscreen) {
    element.msRequestFullscreen()
  } else if (element.webkitRequestFullscreen) {
    element.webkitRequestFullScreen()
  } else if (window.ActiveXObject) {
    let WsShell = new window.ActiveXObject('WScript.Shell')
    WsShell.SendKeys('{F11}')
  }
}

/**
 *退出全屏显示
 */
function exitFullScreen () {
  console.log(document.msExitFullscreen)
  if (document.exitFullscreen) {
    document.exitFullscreen()
  } else if (document.msExitFullscreen) {
    document.msExitFullscreen()
  } else if (document.mozCancelFullScreen) {
    document.mozCancelFullScreen()
  } else if (document.webkitExitFullscreen) {
    document.webkitExitFullscreen()
  } else if (window.ActiveXObject) {
    let wscript = new window.ActiveXObject('WScript.Shell')
    wscript.SendKeys('{F11}')
  }
}

/**
 * 绑定全屏切换事件
 */
function bindFullScreenEvent (handler) {
  if ('onfullscreenchange' in document) {
    document.addEventListener('fullscreenchange', handler)
  }
  if ('onmozfullscreenchange' in document) {
    document.addEventListener('mozfullscreenchange', handler)
  }
  if ('onwebkitfullscreenchange' in document) {
    document.addEventListener('webkitfullscreenchange', handler)
  }
  if ('onmsfullscreenchange' in document) {
    document.onmsfullscreenchange = handler
  }
}

/**
 * 获取当前全屏元素
 */
function getFullScreenElement () {
  return document.fullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement || document.msFullscreenElement
}

/**
 * 判断当前是否在全屏状态
 */
function isInFullScreen () {
  return getFullScreenElement() != null
}

/**
 * 切换全屏状态
 */
function toggleFullScreen (element) {
  if (isInFullScreen()) {
    exitFullScreen()
  } else {
    fullScreen(element)
  }
}

export { isInFullScreen, toggleFullScreen, bindFullScreenEvent }
