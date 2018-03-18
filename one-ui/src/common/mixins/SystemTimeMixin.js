import * as dateUtil from '@utils/date-util'

/**
 * 服务器时间Mixin
 */
export default {
  computed: {
    systemTimeStr: function () {
      if (this.$store) {
        return this.$store.state.system.systemTime
      } else {
        return null
      }
    },
    systemTime: function () {
      return this.systemTimeStr ? dateUtil.parseDate(this.systemTimeStr) : ''
    }
  }
}
