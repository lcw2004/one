/**
 * 限制字符串显示长度，如果超过限制长度，超出部分用...代替
 * 使用：
 *    {{ obj.name | fixed }}
 *    {{ obj.name | fixed(10) }}
 *
 * @param value 需要限制长度的字符串
 * @param maxLength 最大显示长度，默认为10
 */
let fixed = function (value, maxLength = 2) {
  if (value) {
    return Number(value).toFixed(maxLength)
  } else if (value === 0) {
    return 0
  } else {
    return '--'
  }
}

export default fixed
