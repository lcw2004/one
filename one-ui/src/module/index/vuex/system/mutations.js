import * as types from './mutation-types'
import * as dateUtil from '@utils/date-util'
import api from '@api'
import overlay from '@components/form/overlay'

let timeTimer = null

export default {
  [types.INIT_TOP_MENU] (state, menu) {
    state.topMenu = menu
    state.menuMap = toMenuMapById(menu)
    state.permisionMap = getPermisionMap(state.menuMap)
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
  [types.ACTIVE_PAGE_TITLE] (state, title) {
    let menuPath = []
    menuPath.push({
      name: title
    })
    state.menuPath = menuPath
    state.thirdMenu = {
      name: title
    }
    state.secondMenu = {}
  },
  [types.INIT_DATA] (state, initData) {
    state.token = initData.token
    state.dictMap = initData.sysDict
    state.settings = initData.sysSetting
    state.userInfo = initData.userInfo
    state.supplierId = initData.supplierId
    state.userOffice = initData.userOffice
    state.userRoleType = initData.userRoleType

    // 初始化菜单
    const topMenu = initData.userMenu
    state.topMenu = topMenu
    state.menuMap = toMenuMapById(topMenu)
    state.permisionMap = getPermisionMap(state.menuMap)
    if (topMenu && topMenu.childList && topMenu.childList.length > 0) {
      state.firstMenu = topMenu.childList[0]
    }
  },
  [types.INIT_USER_INFO] (state, userInfo) {
    state.userInfo = userInfo
  },
  [types.INIT_SYSTEM_TIME] (state, systemTime) {
    state.systemTime = systemTime
    startTimeTimer(state)
  },
  [types.INIT_AREA] (state) {
    api.system.getAreaTree().then((response) => {
      let result = response.data
      if (result.ok) {
        state.topArea = result.data
      }
    })
  },
  [types.INIT_PURCHASE_TYPE] (state) {
    api.system.getPurchaseTypeTree().then((response) => {
      let result = response.data
      if (result.ok) {
        state.topPurchaseType = result.data
      }
    })
  },
  [types.INIT_ROLE_LIST] (state) {
    if (!state.roleList || state.roleList.length === 0) {
      api.system.listRole({pageNo: 1, pageSize: 1000}).then((response) => {
        let result = response.data
        if (result.ok) {
          state.roleList = result.data.list
        }
      })
    }
  },
  [types.LOGOUT] (state) {
    overlay.start('退出中')
    api.system.logout().then((response) => {
      window.location.href = 'login.html'
    })
    setTimeout(() => {
      window.location.href = 'login.html'
    }, 100)
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
  },
  [types.LOAD_SUPPLIER] (state, supplierId) {
    api.user.getSupplierInfo(supplierId).then((response) => {
      let result = response.data
      if (result.ok) {
        state.supplierInfo = result.data
      }
    })
  },
  [types.LOAD_CURRENT_SUPPLIER] (state) {
    api.system.getCurrentSupplierInfo().then((response) => {
      let result = response.data
      if (result.ok) {
        state.supplierInfo = result.data
      }
    })
  },
  [types.IS_SHOW_ROUTER_VIEW] (state, isShowRouterView) {
    state.isShowRouterView = isShowRouterView
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

  if (parentMenu.childList) {
    for (let childMenu of parentMenu.childList) {
      getMapRecursion(menuMap, childMenu)
    }
  }
}

function getPermisionMap (menuMap) {
  let permisionMap = {}
  for (let menu of Object.values(menuMap)) {
    if (menu.permission) {
      permisionMap[menu.permission] = menu
    }
  }
  return permisionMap
}
