<template>
  <div class="box">
    <div class="box-body">
      <section>
        <h4 class="page-header">基本信息</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="姓名" inputWidth="6">
                <input type="text" class="form-control" v-model="user.name" v-validate="'required'" name="姓名" maxlength="50">
              </FormGroup>
            </div>
            <div class="col-md-6">
              <FormGroup label="登录账号" inputWidth="6">
                <p class="form-control-static">{{ user.account }}</p>
              </FormGroup>
            </div>
            <div class="col-md-6">
              <FormGroup label="身份证号码" inputWidth="6">
                <input type="text" class="form-control" v-model="user.identityNumber" v-validate="'required|idNumber'" name="身份证号码" maxlength="18">
              </FormGroup>
            </div>
          </div>
        </form>
      </section>

      <section>
        <h4 class="page-header">联系方式</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="手机号码" inputWidth="6">
                <input type="text" class="form-control" v-model="user.userContactInfo.mobile" v-validate="'required|mobile'" name="手机号码" maxlength="11">
              </FormGroup>
            </div>
            <div class="col-md-6">
              <FormGroup label="座机号码" inputWidth="6">
                <input type="text" class="form-control" v-model="user.userContactInfo.phone" maxlength="12">
              </FormGroup>
            </div>

            <div class="col-md-6">
              <FormGroup label="电子邮箱" inputWidth="6">
                <p class="form-control-static">{{ user.userContactInfo.email }}</p>
              </FormGroup>
            </div>
            <div class="col-md-6">
              <FormGroup label="用户地址" inputWidth="6">
                <input type="text" class="form-control" v-model="user.userContactInfo.address" maxlength="100">
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
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data: () => {
      return {
        actions: {
          updateUserInfo: {method: 'put', url: '/api/updateUserInfo'}
        }
      }
    },
    computed: {
      user: function () {
        return this.$store.state.system.userInfo
      }
    },
    mounted: function () {
      this.resource = this.$resource(null, {}, this.actions)
    },
    methods: {
      save: function () {
        this.$validator.validateAll().then((result) => {
          if (result) {
            this.resource.updateUserInfo(null, JSON.stringify(this.user)).then((response) => {
              let result = response.body
              if (result.ok) {
                this.$store.dispatch('initUserInfo', result.data)
                this.$notify.success('保存成功')
                this.editEnd()
              }
            })
          }
        })
      },
      editEnd () {
        this.$emit('edit-end')
      }
    }
  }
</script>
