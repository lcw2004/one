
import * as dateUtil from '@utils/date-util'

/**
 * 生成一个Int类型的随机数
 */
export function nextInt (min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min)
}

/**
 * 生成一个UUID
 * @returns {string}
 */
export function uuid () {
  return Math.floor((1 + Math.random()) * 0x100000000).toString(16)
}

/**
 * 补0
 */
export function fillZero (input, length) {
  return ('000000000' + input).slice(0 - length)
}
/**
 * 解析身份证中的出生年月日
 */
export function parseBirthday (code) {
  if (code.length === 18) {
    let birthDay = code.substr(6, 8)
    return dateUtil.parseDate(birthDay, 'YYYYMMDD')
  } else {
    return ''
  }
}

/**
 * 判断target是否在List中
 * @param target
 * @param list
 * @returns {boolean}
 */
export function isInList (target, list) {
  return list.length > 0 && list.indexOf(target) >= 0
}

/**
 * 判断字符串中是否有数字
 */
export function hasNumber (password) {
  return /\d/.test(password)
}

/**
 * 判断字符串中是否有字母
 */
export function hasCase (password) {
  return /[a-zA-Z]/.test(password)
}

/**
 * 判断字符串中是否有小写字母
 */
export function hasLowerCase (password) {
  return /[a-z]/.test(password)
}

/**
 * 判断字符串中是否有大写字母
 */
export function hasUpperCase (password) {
  return /[A-Z]/.test(password)
}

/**
 * 判断字符串中是否有特殊字符
 */
export function hasSpecilChar (password) {
  return /[~!@#$%^&*_+;':",<>?/-/=/{/}/(/)]/.test(password)
}

/**
 * 判断是否为空
 */
export function isEmpty (value) {
  return value === undefined || value === null || value === ''
}

/**
 * 判断是否非空
 */
export function isNotEmpty (value) {
  return !isEmpty(value)
}

/**
 * 判断是否是数字
 */
export function isNumber (value) {
  return isNotEmpty(value) && Number(value) > 0
}

/**
 * 判断是否不是数字
 */
export function isNotNumber (value) {
  return !isNumber(value)
}
