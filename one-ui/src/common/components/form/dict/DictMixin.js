export default {
  props: {
    showEmptyValue: {
      type: Boolean,
      default: false
    },
    type: {
      type: String,
      required: true
    },
    disabled: {
      type: Boolean,
      default: false
    },
    value: {}
  },
  computed: {
    dictValue: {
      get: function () {
        return this.value === null || this.value === undefined ? '' : this.value + ''
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
  },
  methods: {
    onChange () {
      this.$emit('change')
    },
    onClick () {
      this.$emit('click')
    }
  }
}
