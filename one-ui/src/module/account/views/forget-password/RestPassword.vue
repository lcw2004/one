<template>
  <form class="form-horizontal">
    <br>
    <template v-if="!success">
      <div class="row">
        <div class="col-md-6">
          <FormGroup label="新密码">
            <input type="password" class="form-control" v-model="param.password" maxlength="50" v-validate="'required'" name="新密码">
          </FormGroup>
        </div>
        <div class="col-md-6">
          <p class="help-block">请输入6-20位字母加数字或符号!</p>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <FormGroup label="确认密码">
            <input type="password" class="form-control" v-model="param.passwordConfirm" maxlength="50" v-validate="'required|confirmed:新密码'" name="确认密码">
          </FormGroup>
        </div>
        <div class="col-md-6">
          <p class="help-block"请再次输入密码!</p>
        </div>
      </div>
    </template>

    <template v-if="success">
      <div class="row">
        <div class="col-md-12">
          <div class="success-label">
            <div class="success-label-icon"><img src="../../assets/images/success.png"></div>
            <div>
              <h4 class="success-label-title">操作成功</h4>
              <div>重置密码成功，请进入 <a href="login.html">登录页面</a> 登录系统。{{ leftTime }}秒后自动跳转。</div>
            </div>
          </div>
        </div>
      </div>
    </template>
    <br>

    <div class="box-footer">
      <div class="row">
        <div class="col-md-2 col-md-offset-5">
          <template v-if="!success">
            <OneButton @click="resetPassword()" :loading="loading" btnClass="btn btn-primary btn-block">确定</OneButton>
          </template>
          <template v-if="success">
            <a href="login.html" class="btn btn-primary btn-block">登 录</a>
          </template>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
  export default {
    components: {},
    data: () => {
      return {
        param: {
          password: '',
          passwordConfirm: ''
        },
        success: false,
        loading: false,
        leftTime: 5,
        actions: {
          resetPassword: {method: 'get', url: '/api/account/resetPassword'}
        }
      }
    },
    mounted () {
      this.resource = this.$resource(null, {}, this.actions)
    },
    methods: {
      resetPassword () {
        this.$validator.validateAll().then((result) => {
          if (result) {
            this.loading = true
            this.resource.resetPassword(this.param).then(function (response) {
              this.loading = false
              let result = response.body
              if (result.ok) {
                this.startTimer()
                this.success = true
              } else {
                this.errors.add('新密码', result.message, 'error')
              }
            })
          }
        })
      },
      startTimer () {
        let interval = setInterval(() => {
          if (this.leftTime > 0) {
            this.leftTime--
          } else {
            clearInterval(interval)
          }

          if (this.leftTime === 0) {
            window.location.href = 'login.html'
          }
        }, 1000)
      }
    }
  }
</script>

<style lang="less">
.success-label {
  margin: 20px 20px 20px 50px;
  min-height: 80px;

  div {
    float: left;
    display: inline-block;

    .success-label-title {
      margin: 5px 0 10px 0;
      font-size: 22px;
    }
  }

  .success-label-icon {
    margin: 0 30px;
  }
}


</style>
