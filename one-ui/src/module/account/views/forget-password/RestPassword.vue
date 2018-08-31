<template>
  <form class="form-horizontal">
    <div class="row">
      <div class="col-md-6">
        <FormGroup label="新密码">
          <input type="password" class="form-control" v-model="param.password" maxlength="50" v-validate="'required|password'" name="新密码">
        </FormGroup>
      </div>
      <div class="col-md-5">
        <p class="help-block">请输入6-20位字母加数字或符号!</p>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <FormGroup label="确认密码">
          <input type="password" class="form-control" v-model="param.passwordConfirm" maxlength="50" v-validate="'required|confirmed:新密码'" name="确认密码">
        </FormGroup>
      </div>
      <div class="col-md-5">
        <p class="help-block"请再次输入密码!</p>
      </div>
    </div>

    <div class="row" style="margin-bottom: 20px;margin-top: 20px;">
      <div class="col-md-2 col-md-offset-5">
        <OneButton @click="resetPassword()" :loading="loading" btnClass="btn btn-primary  btn-flat btn-block">确定</OneButton>
      </div>
    </div>
  </form>
</template>

<script>
import api from '@api'

export default {
  components: {},
  data: function () {
    return {
      param: {
        password: '',
        passwordConfirm: ''
      },
      loading: false,
      leftTime: 5
    }
  },
  methods: {
    resetPassword () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.loading = true
          api.system.resetPassword(this.param).then((response) => {
            this.loading = false
            let result = response.data
            if (result.ok) {
              this.$router.push('/forget-password/success')
            } else {
              this.errors.add('新密码', result.message, 'error')
            }
          })
        }
      })
    }
  }
}
</script>

<style lang="less" type="text/less">



</style>
