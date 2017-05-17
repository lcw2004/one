<template>
  <form class="form-horizontal">
    <div class="form-group">
      <label class="control-label col-md-2">验证邮箱</label>
      <div class="col-md-4">
        <p class="form-control-static">123****22@qq.com</p>
      </div>
    </div>

    <div class="form-group has-error">
      <label class="control-label col-md-2">邮箱验证码</label>
      <div class="col-md-2">
        <input type="text" class="form-control" >
      </div>
      <div class="col-md-2">
        <button type="button" class="btn btn-primary" @click="sendVarifyCode" :class="{ disabled : leftTime > 0}">
          发送验证码<template v-if="leftTime > 0">({{ leftTime }})</template>
        </button>
      </div>
      <div class="col-md-6">
        <p class="help-block">请输入验证码</p>
      </div>
    </div>
  </form>
</template>

<script>
export default {
  components: {
  },
  data: function () {
    return {
      leftTime: 0,
      defaultLeftTime: 10
    }
  },
  methods: {
    sendVarifyCode () {
      let self = this

      if (self.leftTime > 0) {
        return
      }

      self.leftTime = self.defaultLeftTime
      let interval = setInterval(function () {
        if (self.leftTime > 0) {
          self.leftTime--
        } else {
          clearInterval(interval)
        }
      }, 1000)
    }
  }
}
</script>
