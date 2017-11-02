/**
 * Created by lcw20 on 2017-05-27.
 */
import moment from 'moment'

const FORMAT_YYYY_MM_DD_HH_MM_SS = 'YYYY-MM-DD HH:mm:ss'

export function parseDate (time, format = FORMAT_YYYY_MM_DD_HH_MM_SS) {
  return moment(time, format)
}

export function format (mTime, format = FORMAT_YYYY_MM_DD_HH_MM_SS) {
  return mTime.format(format)
}

export function isSameDay (time1, time2 = moment()) {
  if (!time1) {
    return false
  }
  return moment(time1, FORMAT_YYYY_MM_DD_HH_MM_SS).isSame(time2, 'day')
}

export function isBefore (time1, time2 = moment()) {
  if (!time1) {
    return false
  }
  return moment(time1, FORMAT_YYYY_MM_DD_HH_MM_SS).isBefore(time2)
}

export function isAfter (time1, time2 = moment()) {
  if (!time1) {
    return false
  }
  return moment(time1, FORMAT_YYYY_MM_DD_HH_MM_SS).isAfter(time2)
}

export function isBeforeDay (time1, time2 = moment()) {
  if (!time1) {
    return false
  }
  return moment(time1, FORMAT_YYYY_MM_DD_HH_MM_SS).isBefore(time2, 'day')
}

export function isAfterDay (time1, time2 = moment()) {
  if (!time1) {
    return false
  }
  return moment(time1, FORMAT_YYYY_MM_DD_HH_MM_SS).isAfter(time2, 'day')
}

export function hoursDiff (time, now = moment()) {
  return parseInt((moment(time, FORMAT_YYYY_MM_DD_HH_MM_SS) - now) / (1000 * 60 * 60))
}

export function minutesDiff (time, now = moment()) {
  return parseInt((moment(time, FORMAT_YYYY_MM_DD_HH_MM_SS) - now) / (1000 * 60))
}

export function hoursLeft (time) {
  let hours = hoursDiff(time)
  if (hours > 24) {
    let timeStr = parseInt((hours / 24)) + '天'
    if (hours % 24 > 0) {
      timeStr += (hours % 24) + '小时'
    }
    return timeStr
  } else if (hours <= 1) {
    let minutes = minutesDiff(time)
    return minutes + '分钟'
  } else if (hours <= 24) {
    return hours + '小时'
  }
}

export function timeLeft (time) {
  let hours = hoursDiff(time)
  if (hours <= 24) {
    return hours + '小时'
  } else {
    let timeStr = parseInt((hours / 24)) + '天'
    if (hours % 24 > 0) {
      timeStr += (hours % 24) + '小时'
    }
    return timeStr
  }
}

export function addDays (time, days) {
  let m = moment(time, FORMAT_YYYY_MM_DD_HH_MM_SS)
  m.add(days, 'd')
  return m.format(FORMAT_YYYY_MM_DD_HH_MM_SS)
}
