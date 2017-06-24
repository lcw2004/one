/**
 * Created by lcw20 on 2017-05-21.
 */

let limitLength = function (value, maxLength = 10) {
  if (value.length > maxLength) {
    return value.substr(0, maxLength) + '...'
  } else {
    return value
  }
}

export default limitLength
