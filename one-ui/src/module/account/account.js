import Vue from 'vue'
import Vuex from 'vuex'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'

import initVeeValidate from '../../common/utils/init/initVeeValidate'
import initDirectives from '../../common/directives'
import initGlobalComponents from '../../common/components'
import router from './router'
import Account from './Account.vue'

Vue.use(VueRouter)
Vue.use(VueResource)
Vue.use(Vuex)

initVeeValidate()
initDirectives()
initGlobalComponents()

/* eslint-disable no-new */
new Vue({
  el: '#account',
  template: '<Account></Account>',
  components: {
    Account
  },
  router: router
})
