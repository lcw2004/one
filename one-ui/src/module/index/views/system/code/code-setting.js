import moment from 'moment'

export function fillZero (length) {
  return ('000000000' + 1).slice(0 - length)
}

export function timeNow (type) {
  let format = null
  if (type === 2) {
    format = 'YYYY'
  } else if (type === 3) {
    format = 'YYYYMM'
  } else {
    format = 'YYYYMMDD'
  }
  return moment().format(format)
}
