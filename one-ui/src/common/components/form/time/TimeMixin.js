// import moment from 'moment'
export default {
  props: {
    disabledDate: {
      type: Function
    }
  },
  computed: {
    pickerOptions: function () {
      let option = {
        start: '08:30',
        step: '00:15',
        end: '18:30'
      }
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
    },
    currentTime: function () {
      let d = new Date()
      d.setSeconds(0)
      return d
    }
  }
}
