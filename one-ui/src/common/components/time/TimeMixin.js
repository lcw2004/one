// import moment from 'moment'
export default {
  props: {
    disabledDate: {
      type: Function
    }
  },
  computed: {
    pickerOptions: function () {
      let option = {}
      if (this.disabledDate) {
        option.disabledDate = this.disabledDate
      }
      return option
    },
    time: {
      get: function () {
        return this.value
      },
      set: function (newDate) {
        this.$emit('input', newDate)
      }
    }
  }
}
