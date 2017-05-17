import * as types from './mutation-types'

export default {
  [types.INIT_MENU] (state, menu) {
    state.topMenu = menu
  },
  [types.ACTIVE_MENU] (state, menu) {
    state.activedMenu = menu
  },
  [types.ACTIVE_THIRD_MENU] (state, menu) {
    state.thirdMenu = menu
  },
  [types.INIT_DICT] (state, dictMap) {
    state.dictMap = dictMap
  },
  [types.INIT_USER_INFO] (state, userInfo) {
    state.userInfo = userInfo
  },
  [types.REMOVE_USER_INFO] (state) {
    state.userInfo = {}
  }
}
