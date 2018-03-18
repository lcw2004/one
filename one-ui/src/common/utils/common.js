import template from 'url-template'
import * as dateUtil from '@utils/date-util'

export function nextInt (min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min)
}

export function fillZero (input, length) {
  return ('000000000' + input).slice(0 - length)
}

export function getParameterByName (name, url) {
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

export function fillTemplate (url, params = {}) {
  const urlTemplate = template.parse(url)
  return urlTemplate.expand(params)
}

export function uuid () {
  return Math.floor((1 + Math.random()) * 0x100000000).toString(16)
}

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

export function parseBirthday (code) {
  if (code.length === 18) {
    let birthDay = code.substr(6, 8)
    return dateUtil.parseDate(birthDay, 'YYYYMMDD')
  } else {
    return ''
  }
}

export function isInList (target, list) {
  return list.length > 0 && list.indexOf(target) >= 0
}

export function hasNumber (password) {
  return /\d/.test(password)
}

export function hasCase (password) {
  return /[a-zA-Z]/.test(password)
}

export function hasLowerCase (password) {
  return /[a-z]/.test(password)
}

export function hasUpperCase (password) {
  return /[A-Z]/.test(password)
}

export function hasSpecilChar (password) {
  return /[~!@#$%^&*_+;':",<>?/-/=/{/}/(/)]/.test(password)
}

export function isEmpty (value) {
  return value === null || value === ''
}

export function isNotEmpty (value) {
  return !isEmpty(value)
}

export function isNumber (value) {
  return isNotEmpty(value) && Number(value) > 0
}

export function isNotNumber (value) {
  return !isNumber(value)
}

export default {}
