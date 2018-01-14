import Vue from 'vue'
import VeeValidate, { Validator } from 'vee-validate'
import cn from 'vee-validate/dist/locale/zh_CN'
import * as identityCode from '../identityCode'

/**
 * 身份证号码验证规则
 */
function attachIdNumberRule () {
  Validator.extend('idNumber', {
    getMessage (field, params, data) {
      return (data && data.message) || '请输入正确的身份证号码'
    },
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
    getMessage: field => {
      return {
        zh_CN: field => '必须是11位手机号码'
      }
    },
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
    getMessage (field, params, data) {
      return (data && data.message) || '请输入6-20位的密码'
    },
    validate: value => {
      return value.length >= 6 && value.length <= 20
    }
  })
}

// function attachOneAfterRule () {
//   Validator.extend('one_after', {
//     messages: {
//       zh_CN: (field, args) => {
//         const fieldName = args[0]
//         return field + '必须在' + fieldName + '之后'
//       }
//     },
//     validate: (value, args) => {
//       console.log('v2---------------------')
//       const fieldName = args[0]
//       const fieldPath = args[1]
//       console.log(fieldName)
//       console.log(this[fieldPath])
//       console.log('---------------------')
//       return value.length === 11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/.test(value)
//     }
//   })
// }

/**
 * 配置vee-validate
 */
function initVeeValidate () {
  attachIdNumberRule
  attachMobileRule
  attachPasswordRule

  attachIdNumberRule()
  attachMobileRule()
  attachPasswordRule()
  // attachOneAfterRule

  Validator.localize('zh_CN', cn)
  const config = {
    locale: 'zh_CN'
  }
  Vue.use(VeeValidate, config)
}

export default initVeeValidate
