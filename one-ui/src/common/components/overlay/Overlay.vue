<template>
  <div class="overlay-wrapper">
    <div class="overlay" :style="overlayStyle">
      <div class="overlay-item">
        <BounceLoader color="#3c8dbc"></BounceLoader>
        <div class="overlay-text" v-if="text">{{ text }}</div>
      </div>
    </div>
  </div>
</template>

<script>
  let RingLoader = require('vue-spinner/src/RingLoader.vue')
  let DotLoader = require('vue-spinner/src/DotLoader.vue')
  let BounceLoader = require('vue-spinner/src/BounceLoader.vue')

  export default {
    name: 'Overlay',
    components: {
      RingLoader,
      DotLoader,
      BounceLoader
    },
    props: {
      text: {
        type: String
      }
    },
    data: function () {
      return {
        scrollY: 0
      }
    },
    computed: {
      overlayStyle: function () {
        return {
          top: this.scrollY + 'px'
        }
      }
    },
    beforeMount () {
      document.body.insertBefore(this.$el, document.body.firstElementChild)

      // 隐藏滚动条
      this.scrollY = window.pageYOffset
      document.body.style.overflow = 'hidden'
    },
    methods: {
      close () {
        // 显示滚动条
        document.body.style.overflow = 'auto'
        this.$destroy()
        this.$el.remove()
      },
      done () {
        // 清除定时器
        clearTimeout(this.timer)

        this.close()
      }
    }
  }
</script>

<style>
  .overlay-item {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-left: -15px;
    margin-top: -15px;
    color: #000;
    font-size: 30px;
    text-align: center;
  }
  .overlay-text {
    font-size: 14px;
    color: #337ab7;
  }
</style>
