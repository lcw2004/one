<template>
  <form class="form-horizontal">
    <div class="form-group">
      <label class="control-label col-md-2">新密码</label>
      <div class="col-md-4">
        <input type="password" class="form-control" >
      </div>

    </div>

    <div class="form-group has-error">
      <label class="control-label col-md-2">确认密码</label>
      <div class="col-md-4">
        <input type="password" class="form-control" >
      </div>
      <div class="col-md-6">
        <p class="help-block">两次密码不一致</p>
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
