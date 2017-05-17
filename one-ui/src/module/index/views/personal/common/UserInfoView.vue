<template>
  <div class="box box-solid">
    <div class="box-header">
      <h3 class="box-title">个人信息</h3>
    </div>
    <div class="box-body">
      <section>
        <h4 class="page-header">基本信息</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">姓名</label>
                <div class="col-md-8">
                  <p class="form-control-static">{{ user.name }}</p>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">身份证号码</label>
                <div class="col-md-8">
                  <p class="form-control-static">{{ user.identityNumber }}</p>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">登录账号</label>
                <div class="col-md-8">
                  <p class="form-control-static">{{ user.account }}</p>
                </div>
              </div>
            </div>
          </div>
        </form>
      </section>

      <section>
        <h4 class="page-header">联系方式</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">手机号码</label>
                <div class="col-md-8">
                  <p class="form-control-static">{{ user.userContactInfo.mobile }}</p>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">座机号码</label>
                <div class="col-md-8">
                  <p class="form-control-static">{{ user.userContactInfo.phone }}</p>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">电子邮箱</label>
                <div class="col-md-8">
                  <p class="form-control-static">{{ user.userContactInfo.email }}</p>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">用户地址</label>
                <div class="col-md-8">
                  <p class="form-control-static">{{ user.userContactInfo.address }}</p>
                </div>
              </div>
            </div>
          </div>
        </form>
      </section>
    </div>
    <div class="box-footer">
      <div class="row">
        <div class="col-md-2 col-md-offset-2">
          <button type="button" class="btn btn-block btn-primary" @click="editStart()">
            修改
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: function () {
    return {}
  },
  computed: {
    user: function () {
      return this.$store.state.system.userInfo
    }
  },
  methods: {
    editStart () {
      this.$emit('edit-start')
    }
  }
}
</script>
