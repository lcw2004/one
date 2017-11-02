import * as types from './mutation-types'

export default {
  [types.INIT_TOP_MENU] (state, menu) {
    state.topMenu = menu
    state.menuMap = toMenuMapById(menu)
  },
  [types.ACTIVE_FIRST_MENU] (state, menu) {
    state.firstMenu = menu
  },
  [types.ACTIVE_SECOND_MENU] (state, menu) {
    state.secondMenu = menu
  },
  [types.ACTIVE_THIRD_MENU] (state, menu) {
    state.thirdMenu = menu
    state.menuPath = getMenuPath(state.menuMap, menu)
  },
  [types.ACTIVE_HOME_PAGE] (state, menu) {
    let menuPath = []
    menuPath.push({
      name: '首页'
    })
    state.menuPath = menuPath
    state.thirdMenu = {
      name: '首页'
    }
    state.secondMenu = {}
  },
  [types.INIT_DICT] (state, dictMap) {
    state.dictMap = dictMap
  },
  [types.INIT_USER_INFO] (state, userInfo) {
    state.userInfo = userInfo
  },
  [types.REMOVE_USER_INFO] (state) {
    state.userInfo = {}
  },
  [types.TOGGLE_SIDEBAR] (state, sidebarIsExpand) {
    state.sidebarIsExpand = sidebarIsExpand
  }
}

function getMenuPath (menuMap, menu) {
  let menuPath = []
  let parentMenu = menu
  while (parentMenu.id !== '1') {
    menuPath.unshift(parentMenu)
    parentMenu = menuMap[parentMenu.parentId]
  }
  return menuPath
}

function toMenuMapById (topMenu) {
  let menuMap = {}
  getMapRecursion(menuMap, topMenu)
  return menuMap
}

function getMapRecursion (menuMap, parentMenu) {
  menuMap[parentMenu.id] = parentMenu

  if (parentMenu.childList) {
    for (let childMenu of parentMenu.childList) {
      getMapRecursion(menuMap, childMenu)
    }
  }
}
