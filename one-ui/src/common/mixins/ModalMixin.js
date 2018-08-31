/**
 * 模态框Mixin，用于控制显示出模态框的时候，隐藏底层的滚动条
 */
export default {
  props: {
    config: {
      type: Object,
      required: true
    }
  },
  methods: {
    close () {
      this.config.show = false
      this.$emit('close')
    }
  }
}
