import template from 'url-template'

/**
 * 获取URL后的参数值
 */
export function getUrlQueryValue (name, url) {
  if (!url) {
    url = window.location.href
  }
  /* eslint-disable */
  name = name.replace(/[\[\]]/g, '\\$&')
  /* eslint-enable */
  let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)')
  let results = regex.exec(url)
  if (!results) {
    return null
  }
  if (!results[2]) {
    return ''
  }
  return decodeURIComponent(results[2].replace(/\+/g, ' '))
}

/**
 * 填充URL Template
 */
export function fillUrlTemplate (url, params = {}) {
  const urlTemplate = template.parse(url)
  return urlTemplate.expand(params)
}

/**
 * 将参数Map转为URL条件
 */
export function stringify (params) {
  let queryStr = ''
  for (let key in params) {
    let value = params[key]
    if (Array.isArray(value)) {
      for (let v of value) {
        queryStr += '&' + key + '=' + v
      }
    } else {
      queryStr += '&' + key + '=' + value
    }
  }
  return queryStr.substr(1)
}

export function stringifyAxios (params) {
  return {
    params: params,
    paramsSerializer: stringify
  }
}

export function setHtmlTitle (title) {
  document.title = title
  let mobile = navigator.userAgent.toLowerCase()
  if (/iphone|ipad|ipod/.test(mobile)) {
    let iframe = document.createElement('iframe')
    let iframeCallback = () => {
      setTimeout(() => {
        iframe.removeEventListener('load', iframeCallback)
        document.body.removeChild(iframe)
      }, 0)
    }

    iframe.style.display = 'none'
    iframe.setAttribute('src', '/favicon.ico')// 替换成站标favicon路径或者任意存在的较小的图片即可
    iframe.addEventListener('load', iframeCallback)
    document.body.appendChild(iframe)
  }
}

export function clearInputFile (file) {
  if (file.value) {
    // for IE11, latest Chrome/Firefox/Opera...
    try {
      file.value = ''
    } catch (err) {
    }

    // for IE5 ~ IE10
    if (file.value) {
      let form = document.createElement('form')
      let ref = file.nextSibling
      let p = file.parentNode
      form.appendChild(file)
      form.reset()
      p.insertBefore(file, ref)
    }
  }
}
