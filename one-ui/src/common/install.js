import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'

// 引入资源css资源文件
import 'kindeditor/kindeditor-all-min.js'
import 'kindeditor/themes/default/default.css'

// 引入自定义组件
import installComponents from './components'
import installDirectives from './directives'
import installFilters from './filters'
import installMixins from './mixins'

// 设置其他组件
import installVeeValidate from './validator'
import installAxios from '@utils/init/installAxios'
import installElementUI from '@utils/init/installElementUI'

function install () {
  Vue.use(Vuex)
  Vue.use(VueRouter)
  Vue.config.devtools = true
  Vue.config.errorHandler = function (err, vm, info) {
    console.error('--------------- errorHandler ')
    console.error(err)
    console.error(vm)
    console.error(info)
  }

  // 注册自定义组件
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
