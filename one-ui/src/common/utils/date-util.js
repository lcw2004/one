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

export function isSame (time1, time2) {
  if (!time1) {
    return false
  }
  return moment(time1, FORMAT_YYYY_MM_DD_HH_MM_SS).isSame(time2)
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

export function secondsDiff (time, now = moment()) {
  return parseInt((moment(time, FORMAT_YYYY_MM_DD_HH_MM_SS) - now) / 1000)
}

export function hoursLeft (time, now = moment()) {
  let hours = hoursDiff(time, now)
  if (hours > 24) {
    let timeStr = parseInt((hours / 24)) + '天'
    if (hours % 24 > 0) {
      timeStr += (hours % 24) + '小时'
    }
    return timeStr
  } else if (hours <= 24 && hours >= 1) {
    return hours + '小时'
  } else if (hours < 1) {
    let minutes = minutesDiff(time, now)
    if (minutes < 60 && minutes >= 1) {
      return minutes + '分钟'
    } else {
      let seconds = secondsDiff(time, now)
      if (seconds < 60 && seconds > 0) {
        return seconds + '秒钟'
      }
    }
    return '已截止'
  }
}

export function timeLeft (time, now = moment()) {
  let hours = hoursDiff(time, now)
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

export function addSeconds (time, seconds = 1) {
  let m = moment(time, FORMAT_YYYY_MM_DD_HH_MM_SS)
  m.add(seconds, 'seconds')
  return m.format(FORMAT_YYYY_MM_DD_HH_MM_SS)
}

export function addMinutes (time, minutes = 1) {
  let m = moment(time, FORMAT_YYYY_MM_DD_HH_MM_SS)
  m.add(minutes, 'minutes')
  return m.format(FORMAT_YYYY_MM_DD_HH_MM_SS)
}
