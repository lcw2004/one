<template>
  <div class="row">
    <div class="col-md-9">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="验证码" id="verifyCode" v-focus v-model="innerValue" maxlength="4">
        <span class="input-group-addon" style="padding: 0">
        <img :src="imageUrl" @click="refresh">
      </span>
      </div>
    </div>
    <div class="col-md-3">
      <a @click="refresh" style="cursor: pointer"><p class="form-control-static">换一张</p></a>
    </div>
  </div>
</template>

<script>
import VerifyCodeImg from './VerifyCodeImg.vue'

export default {
  name: 'VerifyCode',
  components: {
    VerifyCodeImg
  },
  props: {
    timestamp: {
      type: String,
      required: false
    }
  },
  data: function () {
    return {
      innerTime: '',
      innerValue: ''
    }
  },
  computed: {
    imageUrl: function () {
      return '/api/verifyCode' + '?t=' + this.innerTime + this.timestamp
    }
  },
  methods: {
    refresh () {
      this.innerTime = Math.random() + ''
    }
  },
  watch: {
    'innerValue': function () {
      this.$emit('input', this.innerValue)
    }
  }
}
</script>
