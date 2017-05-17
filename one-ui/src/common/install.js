import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'

// 引入资源css资源文件
import 'eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css'
import 'bootstrap-daterangepicker/daterangepicker.css'
import 'bootstrap-switch/dist/css/bootstrap3/bootstrap-switch.min.css'

// 引入自定义组件
import initGlobalComponents from './components'
import initViewComponents from '../module/index/views'
import initDirectives from './directives'

import initVeeValidate from './utils/init/initVeeValidate.js'
import initVueResource from './utils/init/initVueResource.js'

function install () {
  Vue.use(Vuex)
  Vue.use(VueRouter)
  Vue.use(ElementUI)

  // VeeValidate locale
  initVeeValidate()

  // 启用进度条
  initVueResource()

  // 注册自定义组件
  initGlobalComponents()
  initViewComponents()
  initDirectives()
}

export default install
