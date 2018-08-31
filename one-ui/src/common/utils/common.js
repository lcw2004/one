export * from './string-util'
export * from './html-util'

/**
 * 对Map按键进行排序
 * @param map
 * @returns {{}}
 */
export function sortMapByKey (map) {
  if (!map) {
    return {}
  }
  const keys = Object.keys(map).sort()
  let newMap = {}
  for (let key of keys) {
    newMap[key] = map[key]
  }
  return newMap
}
