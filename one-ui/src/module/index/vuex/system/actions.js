import * as types from './mutation-types'

export default {
  initTopMenu ({commit}, menu) {
    commit(types.INIT_TOP_MENU, menu)
  },
  activeFirstMenu ({commit}, menu) {
    commit(types.ACTIVE_FIRST_MENU, menu)
  },
  activeSecondMenu ({commit}, menu) {
    commit(types.ACTIVE_SECOND_MENU, menu)
  },
  activeThirdMenu ({commit}, menu) {
    commit(types.ACTIVE_THIRD_MENU, menu)
  },
  activePageTitle ({commit}, title) {
    commit(types.ACTIVE_PAGE_TITLE, title)
  },
  initData ({commit}, dictMap) {
    commit(types.INIT_DATA, dictMap)
    commit(types.INIT_SYSTEM_TIME, dictMap.systemTime)
  },
  initSystemTime ({commit}, systemTime) {
    commit(types.INIT_SYSTEM_TIME, systemTime)
  },
  initArea ({commit}) {
    commit(types.INIT_AREA)
  },
  initPurchaseType ({commit}) {
    commit(types.INIT_PURCHASE_TYPE)
  },
  initUserInfo ({commit}, userInfo) {
    commit(types.INIT_USER_INFO, userInfo)
  },
  initRoleList ({commit}) {
    commit(types.INIT_ROLE_LIST)
  },
  logout ({commit}) {
    commit(types.LOGOUT)
  },
  toggleSidebarState ({commit}, sidebarIsExpand) {
    commit(types.TOGGLE_SIDEBAR, sidebarIsExpand)
  },
  loadMessage ({commit}) {
    commit(types.LOAD_MESSAGE)
  },
  loadSupplier ({commit}, supplierId) {
    commit(types.LOAD_SUPPLIER, supplierId)
  },
  loadCurrentSupplier ({commit}) {
    commit(types.LOAD_CURRENT_SUPPLIER)
  },
  isShowRouterView ({commit}, isShowRouterView) {
    commit(types.IS_SHOW_ROUTER_VIEW, isShowRouterView)
  }
}
