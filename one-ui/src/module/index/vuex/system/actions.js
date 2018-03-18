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
  activeHomePage ({commit}, home) {
    commit(types.ACTIVE_HOME_PAGE, home)
  },
  initData ({commit}, dictMap) {
    commit(types.INIT_DATA, dictMap)
    commit(types.INIT_SYSTEM_TIME, dictMap.systemTime)
  },
  initSystemTime ({commit}, systemTime) {
    commit(types.INIT_SYSTEM_TIME, systemTime)
  },
  removeUserInfo ({commit}) {
    commit(types.REMOVE_USER_INFO)
  },
  toggleSidebarState ({commit}, sidebarIsExpand) {
    commit(types.TOGGLE_SIDEBAR, sidebarIsExpand)
  },
  loadMessage ({commit}) {
    commit(types.LOAD_MESSAGE)
  }
}
