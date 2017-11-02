function indexOfStr (target, list) {
  let index = -1
  if (list && list.length > 0) {
    for (let i = 0; i < list.length; i++) {
      if (list[i] === target) {
        index = i
      }
    }
  }
  return index
}

function indexOfById (target, list) {
  let index = -1
  if (list && list.length > 0 && target) {
    for (let i = 0; i < list.length; i++) {
      if (list[i].id === target.id) {
        index = i
      }
    }
  }
  return index
}

export function indexOf (targetElement, list) {
  if (typeof targetElement === 'object') {
    return indexOfById(targetElement, list)
  } else {
    return indexOfStr(targetElement, list)
  }
}
