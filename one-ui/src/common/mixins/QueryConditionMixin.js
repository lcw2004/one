export default {
  props: {
    value: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      isShow: false
    }
  },
  created () {
    // 用初始参数值覆盖默认参数值
    for (let key in this.value) {
      this.$set(this.param, key, this.value[key])
    }
  },
  watch: {
    'param': {
      handler: function () {
        this.$emit('input', this.param)
      },
      deep: true
    }
  }
}
