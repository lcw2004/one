// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import store from './vuex'
import router from './router'
import install from '../../common/install'
import 'babel-polyfill'
require('./style/index.less')
require('../../../static/adminlte/css/one.less')
install()

/* eslint-disable no-new */
new Vue({
  el: '#app',
  template: '<App/>',
  router: router,
  store,
  components: {App}
})
