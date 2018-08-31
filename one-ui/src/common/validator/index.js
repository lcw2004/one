import Vue from 'vue'
import VeeValidate, { Validator } from 'vee-validate'
import cn from 'vee-validate/dist/locale/zh_CN'

// 自定义规则
import attachIdentityCodeRule from './identity-code.js'
import attachOfficeCodeRule from './office-code.js'
import attachMobileRule from './mobile.js'
import attachPhoneRule from './phone.js'
import attachFaxRule from './fax.js'
import attachZipCodeRule from './zipCode.js'
import attachWebSiteRule from './webSite.js'
import attachPasswordRule from './password.js'
import attachBankCardRule from './bank-card.js'
import dictionary from './dictionary'

function attachValidRule () {
  attachIdentityCodeRule()
  attachOfficeCodeRule()
  attachMobileRule()
  attachPhoneRule()
  attachFaxRule()
  attachZipCodeRule()
  attachWebSiteRule()
  attachPasswordRule()
  attachBankCardRule()
}

/**
 * 配置vee-validate
 */
export default function installVeeValidate () {
  attachValidRule()

  // 设置本地化
  Validator.localize('zh_CN', cn)
  const config = {
    locale: 'zh_CN',
    dictionary: dictionary
  }
  Vue.use(VeeValidate, config)
}
