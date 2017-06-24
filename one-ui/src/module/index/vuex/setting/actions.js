import * as types from './mutation-types'

export default {
  initSetting ({commit}, settings) {
    commit(types.INIT_SETTING, settings)
  }
}
