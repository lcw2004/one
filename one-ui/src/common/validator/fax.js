import { Validator } from 'vee-validate'
import dictionary from './dictionary'

/**
 * 传真号码验证规则
 */
export default function attachFaxRule () {
  Validator.extend('fax', {
    getMessage: dictionary.zh_CN.messages.fax,
    validate: value => {
      return /^(\d{3,4}-)?\d{7,8}$/.test(value)
    }
  })
}
