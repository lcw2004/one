import { Validator } from 'vee-validate'
import dictionary from './dictionary'

/**
 * 统一社会信用代码验证固资额
 */
export default function attachOfficeCodeRule () {
  Validator.extend('officeCode', {
    getMessage: dictionary.zh_CN.messages.officeCode,
    validate: value => {
      return (value.length === 18 || value.length === 15) && /[^_IOZSVa-z\W]{2}\d{6}[^_IOZSVa-z\W]{10}/.test(value)
    }
  })
}
