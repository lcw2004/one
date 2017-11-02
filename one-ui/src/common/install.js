import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'

// 引入资源css资源文件
import 'kindeditor/kindeditor-all-min.js'
import 'kindeditor/themes/default/default.css'

// 引入自定义组件
import initGlobalComponents from './components'
import initViewComponents from '../module/index/views'
import initDirectives from './directives'
import initFilters from './filters'

import initVeeValidate from './utils/init/initVeeValidate.js'
import initVueResource from './utils/init/initVueResource.js'
import initElementUi from './utils/init/installElementUI.js'
import initGLobalMixins from './utils/init/initGLobalMixins.js'

function install () {
  Vue.config.devtools = true

  Vue.use(Vuex)
  Vue.use(VueRouter)

  // 注册自定义组件
  initGlobalComponents()
  initViewComponents()
  initDirectives()
  initFilters()
  initGLobalMixins()

  // VeeValidate locale
  initVeeValidate()

  // 启用进度条
  initVueResource()

  // 注册element-ui
  initElementUi()
}

export default install
