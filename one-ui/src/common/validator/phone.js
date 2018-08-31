import { Validator } from 'vee-validate'
import dictionary from './dictionary'

/**
 * 电话/手机号码验证规则
 */
export default function attachPhoneRule () {
  Validator.extend('phone', {
    getMessage: dictionary.zh_CN.messages.phone,
    validate: value => {
      return /^((0\d{2,3}-\d{7,8})|(1[3456789]\d{9}))$/.test(value)
    }
  })
}
