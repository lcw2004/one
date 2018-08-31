/**
* props:
size: full\large\default\small
*/
<template>
  <OneTransition>
    <div :class="['modal', { 'modal-full-screen': isFullScreen }]" v-show="innerShow" style="display: block" @click.stop="closeOnClick($event)">
      <div :class="['modal-dialog', modalSizeClass]" :style="modalDialogStyle" @click.stop="closeOnClick($event)">
        <div class="modal-content">
          <div class="modal-header" @dblclick="fullScreen">
            <button type="button" class="close" aria-label="Close" @click="close()">
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
      },
      width: {
        type: [Number, String]
      }
    },

    data: function () {
      return {
        isContainer: true,
        isFullScreen: this.size === 'full',

        modalSize: {
          headerHeight: 65,
          footerHeight: 56,
          htmlBodyHeight: 0,
          modalBodyHeight: 0,
          modalContentHeight: 0
        }
      }
    },

    computed: {
      innerShow: {
        get: function () {
          return this.show
        },
        set: function (newValue) {
          this.$emit('update:show', newValue)

          if (newValue) {
            // 隐藏页面body的滚动条
            document.body.style.overflow = 'hidden'

            this.$nextTick(() => {
              this.reSize()
            })
          }
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

      modalDialogStyle: function () {
        let style = {}
        if (this.width) {
          style.width = this.width + 'px'
        }
        return style
      }
    },

    mounted () {
      this.fetchModalSize()
      this.bindEvent()
      this.$nextTick(() => {
        this.reSize()
      })
    },

    methods: {
      /**
       * 关闭模态窗
       */
      close () {
        // 显示页面body的滚动条
        document.body.style.overflow = 'auto'
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
       * 模态框全屏
       */
      fullScreen () {
        this.isFullScreen = !this.isFullScreen
      },

      /**
       * 根据是否全屏，自适应设置modal-body的高度，超出高度的时候显示滚动条
       */
      reSize () {
        const modalBody = this.$el.querySelector('.modal-body')
        const modalContent = this.$el.querySelector('.modal-content')

        const offset = this.isFullScreen ? 5 : 60 // 偏移量
        const modalSize = this.modalSize
        let modalBodyHeight = modalSize.htmlBodyHeight - modalSize.headerHeight - modalSize.footerHeight - offset
        let modalContentHeight = modalSize.htmlBodyHeight - offset

        if (this.isFullScreen) {
          modalBody.style.removeProperty('max-height')
          modalBody.style.setProperty('height', modalBodyHeight + 'px', 'important')
          modalContent.style.setProperty('height', modalContentHeight + 'px', 'important')
        } else {
          modalBody.style.setProperty('max-height', modalBodyHeight + 'px', 'important')
          modalBody.style.removeProperty('height')
          modalContent.style.removeProperty('height')
        }
        modalBody.style.overflowY = 'auto'
        modalBody.style.overflowX = 'auto'
      },

      /**
       * 获取模态框初始尺寸
       */
      fetchModalSize () {
        const modalBody = this.$el.querySelector('.modal-body')
        const modalContent = this.$el.querySelector('.modal-content')
        const htmlBodyHeight = document.body.offsetHeight
        let headerHeight = 65
        let footerHeight = 56
        if (modalBody.style.offsetHeight) {
          const modalHeader = this.$el.querySelector('.modal-header')
          const modalFooter = this.$el.querySelector('.modal-footer')
          headerHeight = modalHeader && modalHeader.offsetHeight ? modalHeader.offsetHeight : headerHeight
          footerHeight = modalFooter && modalFooter.offsetHeight ? modalFooter.offsetHeight : footerHeight
        }
        const modalBodyHeight = modalBody.offsetHeight
        const modalContentHeight = modalContent.offsetHeight
        this.modalSize = {
          headerHeight,
          footerHeight,
          htmlBodyHeight,
          modalBodyHeight,
          modalContentHeight
        }
      },

      /**
       * 绑定事件
       */
      bindEvent () {
      }
    },

    watch: {
      'isFullScreen': function () {
        this.reSize()
      }
    }
  }
</script>

<style lang="less">
  .modal-content {
    box-shadow: 0 3px 50px rgba(0, 0, 0, 0.4)
  }
</style>
