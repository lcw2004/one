import { Validator } from 'vee-validate'
import dictionary from './dictionary'
import moment from 'moment'

function attachRule () {
  Validator.extend('one_time_after', {
    getMessage: function (field, args) {
      return dictionary.zh_CN.messages.oneTimeAfter(field, args)
    },
    validate: (value, args) => {
      const FORMAT_YYYY_MM_DD_HH_MM_SS = 'YYYY-MM-DD HH:mm:ss'
      const fieldPath = args[1]
      // TODO 如何根据属性链获取值
      // TODO vee-validate其实可以传入上下文的
      const filedValue = this[fieldPath]

      if (!value || value === '') {
        return false
      }
      if (!fieldPath || fieldPath === '' || !filedValue || filedValue === '') {
        return false
      }
      return moment(value, FORMAT_YYYY_MM_DD_HH_MM_SS).isAfter(moment(filedValue, FORMAT_YYYY_MM_DD_HH_MM_SS))
    }
  })
}

/**
 * 手机号码验证规则
 */
export default function attachOneTimeAfterRule (self) {
  let oneTimeAfterFunc = attachRule.bind(self)
  oneTimeAfterFunc()
}
