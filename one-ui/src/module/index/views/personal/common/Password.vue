<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-body">
            <form class="form-horizontal">
              <div class="row">
                <div class="col-md-6">
                  <FormGroup label="当前密码">
                    <input type="password" class="form-control" placeholder="请填写当前登录密码" v-validate="'required'" name="当前密码" v-model="oldPassword">
                  </FormGroup>
                  <FormGroup label="新密码">
                    <input type="password" class="form-control" placeholder="请填写新的登录密码" v-validate="'required'" name="新密码" v-model="newPassword">
                  </FormGroup>
                  <FormGroup label="确认密码">
                    <input type="password" class="form-control" placeholder="再次输入新的登录密码" v-validate="'required|confirmed:新密码'" name="确认密码"
                           v-model="newPasswordConfirm">
                  </FormGroup>
                </div>
              </div>
            </form>
          </div>
          <div class="box-footer">
            <div class="row">
              <div class="col-md-2 col-md-offset-2">
                <button type="button" class="btn btn-block btn-primary" @click="updatePassword()">
                  确认修改密码
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
  export default {
    components: {},
    data: () => {
      return {
        oldPassword: '',
        newPassword: '',
        newPasswordConfirm: ''
      }
    },
    methods: {
      updatePassword () {
        this.$validator.validateAll().then((result) => {
          if (result) {
            this.$http.put('/api/updatePassword', null, {params: this.$data}).then((response) => {
              let result = response.body
              if (result.ok) {
                this.$notify.success('修改成功')
              }
            })
          }
        })
      }
    }
  }
</script>
