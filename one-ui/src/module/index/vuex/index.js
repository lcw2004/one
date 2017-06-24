import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations'
import actions from './actions'
// 模块
import bidding from './bidding'
import setting from './setting'
import data from './data'
import system from './system'
Vue.use(Vuex)

const state = {}

export default new Vuex.Store({
  state,
  mutations,
  actions,
  modules: {
    bidding,
    setting,
    data,
    system
  }
})
