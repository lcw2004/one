import { Validator } from 'vee-validate'
import dictionary from './dictionary'
import { hasNumber, hasCase, hasSpecilChar } from '@utils/common'

/**
 * 密码验证规则
 */
export default function attachPasswordRule () {
  Validator.extend('password', {
    getMessage: dictionary.zh_CN.messages.password,
    validate: value => {
      if (value.length < 6 || value.length > 20) {
        return false
      }
      if (hasCase(value) && (hasSpecilChar(value) || hasNumber(value))) {
        return true
      }
      return false
    }
  })
}
