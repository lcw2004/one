<template>
  <form class="form-horizontal">
    <br>
    <div class="row">
      <div class="col-md-6">
        <FormGroup label="验证邮箱">
          <p class="form-control-static">{{ email }}</p>
        </FormGroup>
      </div>
      <div class="col-md-6">
        <p class="text-muted">系统已经向您的邮箱发送了验证码，请登录邮箱查收，并在下方输入您收到的验证码。</p>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <FormGroup label="验证码">
          <input type="text" class="form-control" v-model="verifyCode" maxlength="6" v-validate="'required'" name="验证码">
        </FormGroup>
      </div>
    </div>
    <br>

    <div class="box-footer">
      <div class="row">
        <div class="col-md-2 col-md-offset-5">
          <OneButton @click="validVerifyCode()" :loading="loading" btnClass="btn btn-primary btn-block">下一步</OneButton>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
  export default {
    props: {
      email: {
        type: String
      }
    },
    data: () => {
      return {
        verifyCode: '',
        loading: false,
        actions: {
          validVerifyCode: {method: 'get', url: '/api/account/validVerifyCode'}
        }
      }
    },
    mounted () {
      this.resource = this.$resource(null, {}, this.actions)
    },
    methods: {
      validVerifyCode () {
        this.$validator.validateAll().then((result) => {
          if (result) {
            this.loading = true
            this.resource.validVerifyCode({verifyCode: this.verifyCode}).then(function (response) {
              this.loading = false
              let result = response.body
              if (result.ok) {
                this.$emit('next')
              } else {
                this.errors.add('验证码', result.message, 'error')
              }
            })
          }
        })
      }
    }
  }
</script>
