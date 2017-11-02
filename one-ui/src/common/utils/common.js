let nextInt = function (min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min)
}

let fillZero = function (input, length) {
  return ('000000000' + input).slice(0 - length)
}

let getParameterByName = function (name, url) {
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

export default {
  nextInt,
  fillZero,
  getParameterByName
}
