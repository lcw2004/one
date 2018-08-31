import { Validator } from 'vee-validate'
import dictionary from './dictionary'

/**
 * 银行卡验证规则
 */
export default function attachBankCardRule () {
  Validator.extend('bankCard', {
    getMessage: dictionary.zh_CN.messages.bankCard,
    validate: value => {
      if (value.length < 16 || value.length > 19) {
        return false
      }
      var strBin = '10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99'
      if (strBin.indexOf(value.substring(0, 2)) === -1) {
        return false
      }
      return true
    }
  })
}
