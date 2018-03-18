/**
 * 限制字符串显示长度，如果超过限制长度，超出部分用...代替
 * 使用：
 *    {{ obj.name | limitLength }}
 *    {{ obj.name | limitLength(10) }}
 *
 * @param value 需要限制长度的字符串
 * @param maxLength 最大显示长度，默认为10
 */
let limitLength = function (value, maxLength = 15) {
  if (value && value.length > maxLength) {
    return value.substr(0, maxLength) + '...'
  } else {
    return value
  }
}

export default limitLength
