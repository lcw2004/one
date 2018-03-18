<template>
  <div class="overlay-wrapper">
    <div class="overlay" :style="overlayStyle">
      <div class="overlay-item">
        <FadeLoader color="#3c8dbc"></FadeLoader>
        <div class="overlay-text" v-if="text">{{ text }}</div>
      </div>
    </div>
  </div>
</template>

<script>
let FadeLoader = require('vue-spinner/src/FadeLoader.vue')
import $ from 'jquery'

export default {
  name: 'Overlay',
  components: {
    FadeLoader
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
      $(this.$el).remove()
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
    width: 60px;
    height: 60px;
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
    position: absolute;
    display: inline-block;
    left: -15px;
    top: 40px;
    font-size: 14px;
    color: #337ab7;
  }
</style>
