import { Validator } from 'vee-validate'
import dictionary from './dictionary'

/**
 * 传真号码验证规则
 */
export default function attachZipCodeRule () {
  Validator.extend('zipCode', {
    getMessage: dictionary.zh_CN.messages.zipCode,
    validate: value => {
      return /^[1-9][0-9]{5}$/.test(value)
    }
  })
}
