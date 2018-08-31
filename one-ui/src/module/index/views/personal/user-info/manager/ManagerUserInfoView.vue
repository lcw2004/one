<template>
  <div class="box box-primary">
    <div class="box-body">
      <section>
        <h4 class="page-header">基本信息</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="姓名">{{ user.userInfo.name }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="登录账号">{{ user.userInfo.account }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="工号">{{ user.workNumber }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="所属部门">{{ user.sysOffice ? user.sysOffice.name : '' }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="主管">{{ user.leader ?user.leader.name : '' }}</FormGroupStatic>
            </div>
          </div>
        </form>
      </section>

      <section>
        <h4 class="page-header">联系方式</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="手机号码">{{ user.userInfo.userContactInfo.mobile }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="座机号码">{{ user.userInfo.userContactInfo.phone }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="电子邮箱">{{ user.userInfo.userContactInfo.email }}</FormGroupStatic>
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
  props: {
    user: {
      type: Object
    }
  },
  methods: {
    editStart () {
      this.$emit('edit-start')
    }
  }
}
</script>
