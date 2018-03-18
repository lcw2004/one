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

  watch: {
    'config.show': {
      handler: function () {
        if (this.config.show) {
          this.hideBodyScrollBar()
          this.$nextTick(() => {
            this.reSize()
          })
        }
      },
      deep: true,
      immediate: true
    },
    'isFullScreen': {
      handler: function () {
        this.reSize()
      }
    }
  },

  computed: {
    fullScreenClass: function () {
      return {
        'modal': true,
        'modal-full-screen': this.isFullScreen
      }
    }
  },

  data: function () {
    return {
      isFullScreen: false,

      modalSize: {
        headerHeight: 65,
        footerHeight: 65,
        htmlBodyHeight: 0,
        contentMaxHeigth: 0
      }
    }
  },

  mounted () {
    this.$nextTick(function () {
      this.reSize()
    })
    this.bindEvent()
  },

  methods: {
    /**
     * 自适应设置modal-body的高度，超出高度的时候显示滚动条
     */
    reSize () {
      const modalBody = this.$el.querySelector('.modal-body')
      if (!modalBody.style.maxHeight) {
        const modalHeader = this.$el.querySelector('.modal-header')
        const modalFooter = this.$el.querySelector('.modal-footer')
        this.modalSize.headerHeight = modalHeader.offsetHeight === 0 ? 65 : modalHeader.offsetHeight
        this.modalSize.footerHeight = modalFooter.offsetHeight === 0 ? 56 : modalFooter.offsetHeight
        this.modalSize.htmlBodyHeight = document.body.offsetHeight
      }

      let offset = this.isFullScreen ? 0 : 60 // 偏移量
      this.modalSize.contentMaxHeigth = this.modalSize.htmlBodyHeight - this.modalSize.headerHeight - this.modalSize.footerHeight - offset
      modalBody.style.maxHeight = this.modalSize.contentMaxHeigth + 'px'
      modalBody.style.overflowY = 'auto'
    },

    fullScreen () {
      this.isFullScreen = !this.isFullScreen
    },

    /**
     * 显示页面body的滚动条
     */
    showBodyScrollBar () {
      document.body.style.overflow = 'auto'
    },

    /**
     * 隐藏页面body的滚动条
     */
    hideBodyScrollBar () {
      document.body.style.overflow = 'hidden'
    },

    /**
     * 关闭模态窗
     */
    close () {
      this.showBodyScrollBar()
      this.config.show = false
    },

    /**
     * 绑定双击全屏事件
     */
    bindEvent () {
      const modalHeader = this.$el.querySelector('.modal-header')
      modalHeader.addEventListener('dblclick', this.fullScreen)
    }
  }
}
