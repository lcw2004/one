import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'
import Login from './Login.vue'
import 'babel-polyfill'
Vue.use(VueRouter)
Vue.use(Vuex)

require('element-ui/lib/theme-chalk/index.css')
import { RadioGroup, Radio, RadioButton } from 'element-ui'

/**
 * 因为只用到了部分组件，固单独注册
 */
function install () {
  Vue.component(RadioGroup.name, RadioGroup)
  Vue.component(Radio.name, Radio)
  Vue.component(RadioButton.name, RadioButton)
}

install()

/* eslint-disable no-new */
new Vue({
  el: '#login',
  template: '<Login></Login>',
  components: {
    Login
  }
})
