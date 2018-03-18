import * as types from './mutation-types'
import * as dateUtil from '@utils/date-util'
import api from '@api'

let timeTimer = null

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
  [types.INIT_DATA] (state, initData) {
    state.token = initData.token
    state.dictMap = initData.sysDict
    state.userInfo = initData.userInfo
    state.supplierId = initData.supplierId
    state.settings = initData.sysSetting
    state.userOffice = initData.userOffice
    state.userRoleType = initData.userRoleType
  },
  [types.INIT_SYSTEM_TIME] (state, systemTime) {
    state.systemTime = systemTime
    startTimeTimer(state)
  },
  [types.REMOVE_USER_INFO] (state) {
    state.userInfo = {
      name: '---'
    }
  },
  [types.TOGGLE_SIDEBAR] (state) {
    state.sidebarIsExpand = !state.sidebarIsExpand

    let body = document.getElementsByTagName('body')[0]
    if (state.sidebarIsExpand) {
      addClass(body, 'sidebar-open')
      removeClass(body, 'sidebar-collapse')
    } else {
      addClass(body, 'sidebar-collapse')
      removeClass(body, 'sidebar-open')
    }
  },
  [types.LOAD_MESSAGE] (state) {
    api.system.listMessage({pageSize: 5, status: 0}).then((response) => {
      let result = response.data
      if (result.ok) {
        state.messageList = result.data.list
        state.unreadMessageCount = result.data.ext.unReadCount
      }
    })
  }
}

/**
 * 启动定时器更新时间
 * @param state
 */
function startTimeTimer (state) {
  if (timeTimer != null) {
    clearInterval(timeTimer)
  }
  timeTimer = setInterval(() => {
    state.systemTime = dateUtil.addSeconds(state.systemTime)
  }, 1000)
}

/**
 * 添加class
 * @param element
 * @param className
 */
function addClass (element, className) {
  element.classList.add(className)
}

/**
 * 移除class
 * @param element
 * @param className
 */
function removeClass (element, className) {
  element.classList.remove(className)
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
  parentMenu.isShow = 0

  if (parentMenu.childList) {
    for (let childMenu of parentMenu.childList) {
      getMapRecursion(menuMap, childMenu)
    }
  }
}

// function closeChildMenu (menu) {
//   if (menu.childList) {
//     for (let childMenu of menu.childList) {
//       childMenu.isOpen = 0
//     }
//   }
// }
