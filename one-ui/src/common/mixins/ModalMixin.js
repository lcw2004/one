/*
模态框Mixin，用于控制显示模态框的时候，隐藏到底层的滚动条
*/
export default {
  props: {
    config: {
      type: Object,
      required: true
    }
  },
  watch: {
    'config.show': {
      handler: function () {
        if (this.config.show) {
          this.hideBodyScrollBar()
        }
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    showBodyScrollBar () {
      document.body.style.overflow = 'auto'
    },
    hideBodyScrollBar () {
      document.body.style.overflow = 'hidden'
    },
    close () {
      this.showBodyScrollBar()
      this.config.show = false
    }
  }
}
