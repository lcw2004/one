import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

import mutations from './mutations'
import actions from './actions'

// 模块
import setting from './setting'
import data from './data'
import system from './system'

const state = {
}

export default new Vuex.Store({
  state,
  mutations,
  actions,
  modules: {
    setting,
    data,
    system
  }
})
