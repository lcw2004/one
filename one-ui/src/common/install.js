import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'

// 引入资源css资源文件
import 'kindeditor/kindeditor-all-min.js'
import 'kindeditor/themes/default/default.css'

// 引入自定义组件
import installViewComponents from '../module/index/views'
import installComponents from './components'
import installDirectives from './directives'
import installFilters from './filters'
import installMixins from './mixins'

// 设置其他组件
import installVeeValidate from './validator'
import installAxios from '@utils/init/installAxios.js'
import installElementUI from '@utils/init/installElementUI.js'

function install () {
  Vue.use(Vuex)
  Vue.use(VueRouter)
  Vue.config.devtools = true

  // 注册自定义组件
  installViewComponents()
  installComponents()
  installDirectives()
  installFilters()
  installMixins()

  // VeeValidate验证器
  installVeeValidate()

  // 注册API
  installAxios()

  // 注册element-ui
  installElementUI()
}

export default install
