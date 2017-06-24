<template>
  <div>
    <OneTransition>
      <div class="modal" v-if="isShow" style="display: block" :class="modalStyle">
        <div class="modal-dialog" :class="modelSize">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
                <span aria-hidden="true">×</span>
              </button>
              <h4 class="modal-title">{{ title }}</h4>
            </div>
            <div class="modal-body">
              {{ message }}
            </div>
            <div class="modal-footer">
              <button type="button" class="btn" :class="cancelBtnStyle" data-dismiss="modal" @click="cancel()">{{ cancelBtnText }}</button>
              <button type="button" class="btn" :class="okBtnStyle" @click="ok()">{{ okBtnText }}</button>
            </div>
          </div>
        </div>
      </div>
    </OneTransition>
  </div>
</template>

<script>
  export default {
    name: 'Confirm',
    props: {
      title: {
        type: String,
        required: false
      },
      message: {
        type: String,
        required: false
      },
      onOk: {
        type: Function,
        required: true
      },
      /**
       * 类型：取值范围：default\primary\warning\success\danger
       */
      type: {
        type: String,
        default: 'default'
      },
      /**
       * 大小：取值范围：sm\md\lg
       */
      size: {
        type: String,
        default: 'md'
      },
      okBtnText: {
        type: String,
        default: '确定'
      },
      cancelBtnText: {
        type: String,
        default: '取消'
      }
    },
    data: () => {
      return {
        isShow: true
      }
    },
    beforeMount () {
      this.init()
    },
    methods: {
      init () {
        let parent = document.querySelector(`#confirm`)
        if (!parent) {
          parent = document.createElement('div')
          parent.classList.add('#confirm')
          document.body.appendChild(parent)
          parent.appendChild(this.$el)
        }
      },
      close () {
        this.isShow = false
        this.$destroy()
        this.$el.remove()
      },
      ok () {
        if (this.onOk) {
          this.onOk()
        }
        this.close()
      },
      cancel () {
        if (this.onCancel) {
          this.onCancel()
        }
        this.close()
      }
    },
    computed: {
      modalStyle: function () {
        return 'modal-' + this.type
      },
      modelSize: function () {
        return 'modal-' + this.size
      },
      okBtnStyle: function () {
        if (this.type === 'default') {
          return 'btn-primary'
        }
        return 'btn-' + this.type
      },
      cancelBtnStyle: function () {
        return 'btn-' + this.type
      }
    }
  }
</script>

<style>
  .bounce-enter-active {
    animation: bounce-in .5s;
  }

  .bounce-leave-active {
    animation: bounce-out .5s;
  }

  @keyframes bounce-in {
    0% {
      transform: scale(0);
    }
    50% {
      transform: scale(1.5);
    }
    100% {
      transform: scale(1);
    }
  }

  @keyframes bounce-out {
    0% {
      transform: scale(1);
    }
    50% {
      transform: scale(1.5);
    }
    100% {
      transform: scale(0);
    }
  }
</style>
