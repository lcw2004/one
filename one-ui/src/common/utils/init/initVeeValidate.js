import Vue from 'vue'
import VeeValidate, { Validator } from 'vee-validate'
import cn from 'vee-validate/dist/locale/zh_CN'
import * as identityCode from '../identityCode'

/**
 * 需要将字典数据单独拿出来，取的是这个地方的数据
 */
const dictionary = {
  zh_CN: {
    messages: {
      'idNumber': (field) => `请输入正确的身份证号码`,
      'mobile': (field) => `必须是11位手机号码`,
      'password': (field) => `请输入6-20位的密码`
    }
  }
}

/**
 * 身份证号码验证规则
 */
function attachIdNumberRule () {
  Validator.extend('idNumber', {
    getMessage: dictionary.zh_CN.messages.idNumber,
    validate: value => {
      return identityCode.valid(value)
    }
  })
}

/**
 * 手机号码验证规则
 */
function attachMobileRule () {
  Validator.extend('mobile', {
    getMessage: dictionary.zh_CN.messages.mobile,
    validate: value => {
      return value.length === 11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/.test(value)
    }
  })
}

/**
 * 密码验证规则
 */
function attachPasswordRule () {
  Validator.extend('password', {
    getMessage: dictionary.zh_CN.messages.password,
    validate: value => {
      return value.length >= 6 && value.length <= 20
    }
  })
}

/**
 * 配置vee-validate
 */
function initVeeValidate () {
  attachIdNumberRule()
  attachMobileRule()
  attachPasswordRule()

  // 设置本地化
  Validator.localize('zh_CN', cn)
  const config = {
    locale: 'zh_CN',
    dictionary: dictionary
  }
  Vue.use(VeeValidate, config)
}

export default initVeeValidate
