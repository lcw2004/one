<template>
  <form class="form-horizontal">
    <div class="form-group">
      <label class="control-label col-md-2">验证邮箱</label>
      <div class="col-md-4">
        <input type="text" class="form-control" >
      </div>
      <div class="col-md-6">
        <p class="help-block">请输入验证邮箱</p>
      </div>
    </div>

    <div class="form-group has-error">
      <label class="control-label col-md-2">验证码</label>
      <div class="col-md-2">
        <input type="text" class="form-control" >
      </div>
      <div class="col-md-2">
        <img src="../images/validateCode.jpg" style="height: 32px"/>
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
    }
  },
  methods: {
  }
}
</script>
