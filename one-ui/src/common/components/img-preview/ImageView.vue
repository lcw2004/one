<template>
  <div>
    <a @click="view()" v-show="!isShowImage">查看</a>
    <img :src="src" height="100" @click="view()" v-show="isShowImage">
  </div>
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
