import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'
import Login from './Login.vue'
Vue.use(VueRouter)
Vue.use(Vuex)
import 'babel-polyfill'

/* eslint-disable no-new */
new Vue({
  el: '#login',
  template: '<Login></Login>',
  components: {
    Login
  }
})
