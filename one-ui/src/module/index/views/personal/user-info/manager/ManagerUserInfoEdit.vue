<template>
  <div class="box box-primary">
    <div class="box-body">
      <section>
        <h4 class="page-header">基本信息</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="姓名" :required="true" inputWidth="6">
                <input type="text" class="form-control" v-model="user.userInfo.name" v-validate="'required'" name="姓名" maxlength="50">
              </FormGroup>
            </div>
            <div class="col-md-6">
              <FormGroup label="登录账号" inputWidth="6">
                <p class="form-control-static">{{ user.userInfo.account }}</p>
              </FormGroup>
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
              <FormGroup label="手机号码" :required="true" inputWidth="6">
                <input type="text" class="form-control" v-model="user.userInfo.userContactInfo.mobile" v-validate="'required|mobile'" name="手机号码" maxlength="11">
              </FormGroup>
            </div>
            <div class="col-md-6">
              <FormGroup label="座机号码" inputWidth="6">
                <input type="text" class="form-control" v-model="user.userInfo.userContactInfo.phone" maxlength="20">
              </FormGroup>
            </div>
            <div class="col-md-6">
              <FormGroup label="电子邮箱" inputWidth="6">
                <input type="text" class="form-control" v-model="user.userInfo.userContactInfo.email" v-validate="'email'" name="电子邮箱" maxlength="50"/>
              </FormGroup>
            </div>
          </div>
        </form>
      </section>
    </div>
    <div class="box-footer">
      <div class="row">
        <div class="col-md-2 col-md-offset-2">
          <button type="button" class="btn btn-block btn-primary" @click="save()">
            保存
          </button>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-block btn-default" @click="editEnd()">
            返回
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
    save: function () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.$api.user.updateCurrentManagerUser(this.user).then((response) => {
            let result = response.data
            if (result.ok) {
              this.$store.dispatch('initUserInfo', result.data.userInfo)
              this.$notify.success('保存成功')
              this.editEnd()
            }
          })
        } else {
          this.scrollToError()
        }
      })
    },
    editEnd () {
      this.$emit('edit-end')
    }
  }
}
</script>
