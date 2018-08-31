import { Validator } from 'vee-validate'
import dictionary from './dictionary'

/**
 * 传真号码验证规则
 */
export default function attachWebSiteRule () {
  Validator.extend('webSite', {
    getMessage: dictionary.zh_CN.messages.webSite,
    validate: value => {
      return /[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/.test(value)
    }
  })
}
