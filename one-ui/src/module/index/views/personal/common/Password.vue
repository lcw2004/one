<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box box-solid">
          <div class="box-header">
            <h3 class="box-title">修改密码</h3>
          </div>
          <div class="box-body">
            <form class="form-horizontal">
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group" v-render.r="'当前密码'">
                    <label class="col-md-2 control-label">当前密码</label>
                    <div class="col-md-4">
                      <input type="password" class="form-control" placeholder="请填写当前登录密码" v-validate="'required'" name="当前密码" v-model="oldPassword">
                      <span v-show="errors.has('oldPassword')" class="help is-danger">{{ errors.first('oldPassword') }}</span>
                    </div>
                  </div>
                  <div class="form-group" v-render.r="'新密码'">
                    <label class="col-md-2 control-label">新密码</label>
                    <div class="col-md-4">
                      <input type="password" class="form-control" placeholder="请填写新的登录密码" v-validate="'required'" name="新密码" v-model="newPassword">
                      <span v-show="errors.has('newPassword')" class="help is-danger">{{ errors.first('newPassword') }}</span>
                    </div>
                  </div>
                  <div class="form-group" v-render.r="'确认密码'">
                    <label class="col-md-2 control-label">确认密码</label>
                    <div class="col-md-4">
                      <input type="password" class="form-control" placeholder="再次输入新的登录密码" v-validate="'required|confirmed:新密码'" name="确认密码"
                             v-model="newPasswordConfirm">
                      <span v-show="errors.has('newPasswordConfirm')" class="help is-danger">{{ errors.first('newPasswordConfirm') }}</span>
                    </div>
                  </div>
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
        this.$validator.validateAll().then(() => {
          this.$http.put('/api/updatePassword', null, {params: this.$data}).then((response) => {
            let result = response.body
            if (result.ok) {
              this.$notify.info('修改成功')
            }
          })
        })
      }
    }
  }
</script>
