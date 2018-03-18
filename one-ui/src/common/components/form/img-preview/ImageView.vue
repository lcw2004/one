<template>
  <span>
    <a @click="view()" v-show="!isShowImage">{{ text }}</a>
    <img :src="src" height="100" @click="view()" v-show="isShowImage">
  </span>
</template>

<script>
export default {
  name: 'ImageView',
  props: {
    showImg: {
      type: String,
      default: 'false'
    },
    src: {
      type: String,
      required: true
    },
    text: {
      type: String,
      default: '查看'
    }
  },
  methods: {
    view () {
      this.$preview.openImg(this.src)
    }
  },
  computed: {
    isShowImage: function () {
      return this.showImg === 'true'
    }
  }
}
</script>
