import Vue from 'vue'
import VeeValidate, { Validator } from 'vee-validate'
import cn from 'vee-validate/dist/locale/zh_CN'

// 配置vee-validate
function initVeeValidate () {
  Validator.addLocale(cn)
  const config = {
    locale: 'zh_CN'
  }
  Vue.use(VeeValidate, config)
}

export default initVeeValidate
