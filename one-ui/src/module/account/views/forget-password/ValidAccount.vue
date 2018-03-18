<template>
  <form class="form-horizontal">
    <div class="row">
      <div class="col-md-7">
        <FormGroup label="登录账号">
          <input type="text" class="form-control" v-model="param.account" maxlength="100" v-validate="'required'" name="登录账号">
        </FormGroup>
      </div>
      <div class="col-md-5">
        <p class="help-block">请输入您的登录账号</p>
      </div>
    </div>
    <div class="row">
      <div class="col-md-7">
        <FormGroup label="验证码">
          <VerifyCode v-model="param.captcha" :timestamp="timestamp" v-validate="'required'" data-vv-value-path="innerValue" data-vv-name="验证码"></VerifyCode>
        </FormGroup>
      </div>
      <div class="col-md-5">
        <p class="help-block">请输入验证码</p>
      </div>
    </div>

    <div class="row" style="margin-bottom: 20px;margin-top: 20px;">
      <div class="col-md-2 col-md-offset-5">
        <OneButton @click="validAccount()" :loading="loading" btnClass="btn btn-primary btn-flat btn-block">下一步</OneButton>
      </div>
    </div>
  </form>
</template>

<script>
import VerifyCode from '@components/form/verify-code/VerifyCode'
import api from '@api'

export default {
  components: {
    VerifyCode
  },
  data: function () {
    return {
      param: {
        account: '',
        captcha: ''
      },
      timestamp: '',
      loading: false
    }
  },
  methods: {
    validAccount () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.loading = true
          api.system.validAccount(this.param).then((response) => {
            this.loading = false
            let result = response.data
            if (result.ok) {
              this.$emit('email', result.data)
              this.$emit('next')
            } else {
              this.refreshVerifyCode()
              if (result.code === '0001') {
                this.errors.add('验证码', result.message, 'error')
                this.focusOnVerifyCode()
              } else if (result.code === '0002') {
                this.errors.add('登录账号', result.message, 'error')
              }
            }
          })
        }
      })
    },
    /**
     * 刷新验证码
     */
    refreshVerifyCode () {
      this.timestamp = Math.random() + ''
    },
    /**
     * 将焦点放在验证码输入框上面
     */
    focusOnVerifyCode () {
      let verifyCodeElement = document.getElementById('verifyCode')
      if (verifyCodeElement) {
        verifyCodeElement.focus()
      }
    }
  }
}
</script>
