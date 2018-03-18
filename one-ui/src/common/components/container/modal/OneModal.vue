/**
 * props:
      size: full\large\default\small
 */
<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="innerShow" style="display: block" @click.stop="closeOnClick($event)">
      <div :class="modalDialogClass" @click.stop="closeOnClick($event)">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
              <span aria-hidden="true">×</span>
            </button>
            <h4 class="modal-title" v-text="title"></h4>
          </div>
          <div class="modal-body">
            <slot></slot>
          </div>

          <slot name="footer"></slot>
        </div>
      </div>
    </div>
  </OneTransition>
</template>

<script>
export default {
  name: 'OneModal',
  props: {
    title: {
      type: String,
      required: true
    },
    show: {
      type: Boolean,
      required: true
    },
    size: {
      type: String,
      default: 'default'
    }
  },

  data: function () {
    return {
      isContainer: true,
      isFullScreen: this.size === 'full',

      modalSize: {
        headerHeight: 65,
        footerHeight: 0,
        htmlBodyHeight: 0,
        contentMaxHeigth: 0
      }
    }
  },

  computed: {
    fullScreenClass: function () {
      return {
        'modal': true,
        'modal-full-screen': this.isFullScreen
      }
    },

    modalSizeClass: function () {
      if (this.size === 'large') {
        return 'modal-lg'
      } else if (this.size === 'small') {
        return 'modal-sm'
      } else {
        return ''
      }
    },

    modalDialogClass: function () {
      return ['modal-dialog', this.modalSizeClass]
    },

    innerShow: {
      get: function () {
        return this.show
      },
      set: function (newValue) {
        this.$emit('update:show', newValue)

        if (newValue) {
          this.hideBodyScrollBar()
          this.$nextTick(() => {
            this.reSize()
          })
        }
      }
    }
  },

  mounted () {
    this.$nextTick(function () {
      this.reSize()
    })
    this.bindEvent()
  },

  watch: {
    'isFullScreen': {
      handler: function () {
        this.reSize()
      }
    }
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
        if (modalHeader) {
          this.modalSize.headerHeight = modalHeader.offsetHeight === 0 ? 65 : modalHeader.offsetHeight
        }
        if (modalFooter) {
          this.modalSize.footerHeight = modalFooter.offsetHeight === 0 ? 56 : modalFooter.offsetHeight
        }
        this.modalSize.htmlBodyHeight = document.body.offsetHeight
      }

      let offset = this.isFullScreen ? 10 : 60 // 偏移量
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
      this.innerShow = false
    },

    /**
     * 点击空白部分关闭模态窗
     */
    closeOnClick (event) {
      const classList = event.target.classList
      if (classList && classList.contains('modal')) {
        this.close()
      }
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
</script>
