import { Validator } from 'vee-validate'
import dictionary from './dictionary'

/**
 * 手机号码验证规则
 */
export default function attachMobileRule () {
  Validator.extend('mobile', {
    getMessage: dictionary.zh_CN.messages.mobile,
    validate: value => {
      return value.length === 11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/.test(value)
    }
  })
}
