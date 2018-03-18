import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

// 模块
import data from './data'
import system from './system'

export default new Vuex.Store({
  modules: {
    data,
    system
  }
})
