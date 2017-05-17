export default {
  props: {
    type: {
      type: String,
      required: true
    },
    value: {}
  },
  computed: {
    dictValue: {
      get: function () {
        return this.value
      },
      set: function (newValue) {
        this.$emit('input', newValue)
      }
    },
    dictMap: function () {
      return this.$store.state.system.dictMap
    },
    dictList: function () {
      return this.dictMap[this.type]
    }
  }
}
