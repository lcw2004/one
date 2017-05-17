import Vue from 'vue'
import Vuex from 'vuex'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'
Vue.use(VueRouter)
Vue.use(VueResource)
Vue.use(Vuex)

import initVeeValidate from '../../common/utils/init/initVeeValidate'
initVeeValidate()
import initDirectives from '../../common/directives'
initDirectives()

// init router start
import AgreementInfo from './agreement/AgreementInfo'
import RegistryUser from './user/RegistryUser'
import RegistrySuccess from './user/RegistrySuccess'
import ForgetAccount from './forget-account/ForgetAccount'
import ForgetPassword from './forget-password/ForgetPassword'

let router = {
  routes: [
    { path: '/', component: AgreementInfo },
    { path: '/registry', component: RegistryUser },
    { path: '/registry/success', component: RegistrySuccess },
    { path: '/forget-account', component: ForgetAccount },
    { path: '/forget-password', component: ForgetPassword }
  ]
}
let vueRouter = new VueRouter(router)
// init router end

import Registry from './Registry.vue'

/* eslint-disable no-new */
new Vue({
  el: '#registry',
  template: '<Registry></Registry>',
  components: {
    Registry
  },
  router: vueRouter
})
