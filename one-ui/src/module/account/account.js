import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'

import installDirectives from '../../common/directives'
import installComponents from '../../common/components'
import installMixins from '../../common/mixins'
import installVeeValidate from '../../common/validator'
import installElementUI from '@utils/init/installElementUI'
import router from './router'
import Account from './Account.vue'

Vue.use(VueRouter)
Vue.use(Vuex)

import 'babel-polyfill'
require('./style/account.less')
require('../../../static/adminlte/css/one.less')

installDirectives()
installComponents()
installMixins()
installVeeValidate()
installElementUI()

/* eslint-disable no-new */
new Vue({
  el: '#account',
  template: '<Account></Account>',
  components: {
    Account
  },
  router: router
})
