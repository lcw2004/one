import * as types from './mutation-types'

export default {
  initMenu ({ commit }, menu) {
    commit(types.INIT_MENU, menu)
  },
  activeMenu ({ commit }, menu) {
    commit(types.ACTIVE_MENU, menu)
  },
  activeThirdMenu ({ commit }, menu) {
    commit(types.ACTIVE_THIRD_MENU, menu)
  },
  initDict ({ commit }, dictMap) {
    commit(types.INIT_DICT, dictMap)
  },
  initUserInfo ({ commit }, userInfo) {
    commit(types.INIT_USER_INFO, userInfo)
  },
  removeUserInfo ({ commit }) {
    commit(types.REMOVE_USER_INFO)
  }
}
