
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
    }
  }
}
