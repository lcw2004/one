<template>
<img :src="imageUrl" @click="refresh">
</template>

<script>
export default {
  name: 'VerifyCodeImg',
  props: {
    timestamp: {}
  },
  data: function () {
    return {
      t: ''
    }
  },
  methods: {
    refresh () {
      this.t = Math.random() + ''
    }
  },
  computed: {
    imageUrl: function () {
      return '/api/verifyCode' + '?t=' + this.t + this.timestamp
    }
  }
}
</script>
