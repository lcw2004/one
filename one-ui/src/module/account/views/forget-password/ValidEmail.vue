<template>
  <form class="form-horizontal">
    <div class="row">
      <div class="col-md-12">
        <div class="callout callout-default">
          <p class="text-muted">系统已经向您的邮箱发送了验证码，请登录邮箱查收，并在下方输入您收到的验证码。</p>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-7">
        <FormGroup label="验证邮箱">
          <p class="form-control-static">{{ email }}</p>
        </FormGroup>
      </div>
    </div>
    <div class="row">
      <div class="col-md-7">
        <FormGroup label="验证码">
          <input type="text" class="form-control" v-model="verifyCode" maxlength="6" v-validate="'required'" name="验证码">
        </FormGroup>
      </div>
    </div>

    <div class="row" style="margin-bottom: 20px;margin-top: 20px;">
      <div class="col-md-2 col-md-offset-5">
        <OneButton @click="validVerifyCode()" :loading="loading" btnClass="btn btn-primary btn-flat btn-block">下一步</OneButton>
      </div>
    </div>
  </form>
</template>

<script>
import api from '@api'

export default {
  props: {
    email: {
      type: String
    }
  },
  data: function () {
    return {
      verifyCode: '',
      loading: false
    }
  },
  methods: {
    validVerifyCode () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.loading = true
          api.system.validVerifyCode({verifyCode: this.verifyCode}).then((response) => {
            this.loading = false
            let result = response.data
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
