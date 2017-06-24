// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import install from '../../common/install'
// 引入根组件、路由、状态
import App from './App'
import store from './vuex'
import router from './router'
install()

/* eslint-disable no-new */
new Vue({
  el: '#app',
  template: '<App/>',
  router: router,
  store,
  components: {App}
})
