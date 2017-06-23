<template>
  <div class="login-container" @keyup.enter="login">
    <!--[if lte IE 8]>
    <div>
      <h4>温馨提示：</h4>
      <p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p>
    </div>
    <![endif]-->

    <!-- BACKGROUND IMAGE -->
    <!--===================================================-->
    <div class="login-bg-img login-bg-img-balloon"></div>

    <!-- HEADER -->
    <!--===================================================-->
    <div class="login-header">
      <div class="login-header-title">
        <a>
          <span>One Base System</span>
        </a>
      </div>
    </div>
    <!--===================================================-->

    <!-- LOGIN FORM -->
    <!--===================================================-->
    <div class="login-body">
      <div class="login-body-panel">
        <div class="panel-body">
          <p class="login-body-panel-head">欢迎登录 One Base System</p>
          <form class="form login-form">
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-addon login-icon"><i class="fa fa-user" style="width: 15px;"></i></div>
                    <input type="text" id="username" v-focus v-model="loginInfo.username" class="form-control" style="height: 38px" placeholder="登录名">
                  </div>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-addon login-icon"><i class="fa fa-asterisk" style="width: 15px;"></i></div>
                    <input type="password" id="password" v-model="loginInfo.password" class="form-control" style="height: 38px" placeholder="密码">
                  </div>
                </div>
              </div>
            </div>

            <div class="row" v-if="isVerifyCode">
              <div class="form-group">
                <div class="col-md-9 col-xs-9">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="验证码" id="verifyCode" v-focus v-model="loginInfo.verifyCode" maxlength="4">
                    <span class="input-group-addon" style="padding: 0">
                    <VerifyCodeImg :timestamp="timestamp"></VerifyCodeImg>
                  </span>
                  </div>
                </div>
                <div class="col-md-3 col-xs-3">
                  <a @click="refreshVerifyCode" style="cursor: pointer"><p class="form-control-static">换一张</p></a>
                </div>
              </div>
            </div>

            <div class="row" v-if="errorMessage">
              <div class="col-md-12">
                <div class="form-group">
                  <div class="input-group">
                    <p class="form-control-static text-red">{{ errorMessage }}</p>
                  </div>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <a class="btn btn-primary btn-lg btn-block" :class="{ disabled: loginBtnDisabled }" @click="login">
                  <template v-if="loginBtnDisabled">登录中</template>
                  <template v-if="!loginBtnDisabled">登录</template>
                </a>
              </div>
            </div>

            <div class="row" style="margin-top: 20px">
              <div class="col-md-6">
                <div class="pull-right">
                  <a href="resetpass.html" class="btn-link login-mar-rgt">忘记密码 ?</a>
                </div>
              </div>
              <div class="col-md-6">
                <div class="pull-left">
                  <a href="registry.html" class="btn-link login-mar-lft">注册帐号</a>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!--===================================================-->

    <footer class="navbar-fixed-bottom login-footer">
      Copyright &copy; 2012-2016 <a>One Base System</a> - Powered By <a href="https://github.com/thinkgem/jeesite" target="_blank">JeeSite</a> V1.1.0
    </footer>
  </div>
</template>

<script>
  import VerifyCodeImg from '../../common/components/base/VerifyCodeImg'

  export default {
    components: {
      VerifyCodeImg
    },
    data: () => {
      return {
        isVerifyCode: false, // 是否显示验证码
        timestamp: '', // 验证码时间戳
        loginInfo: {
          username: '',
          password: '',
          isRememberMe: false,
          verifyCode: ''
        },
        errorMessage: '',
        result: {}, // 登录结果
        loginBtnDisabled: false
      }
    },
    mounted () {
      let actions = {
        login: {method: 'get', url: '/api/login'}
      }
      this.resource = this.$resource(null, {}, actions, {emulateJSON: true})
    },
    methods: {
      /**
       * 登录接口
       */
      login () {
        if (!this.valid()) {
          return
        }
        if (this.loginBtnDisabled) {
          return
        }

        this.beforeLogin()
        this.resource.login(this.loginInfo).then((response) => {
          this.afterLogin()
          let result = response.body
          this.result = result
          if (result.ok) {
            // 登录成功
            window.location.href = '/'
          } else if (result.code === '0001') {
            // 账号错误
            this.errorMessage = result.message
            if (result.data != null && result.data) {
              // 根据后台返回的错误次数启用验证码
              this.isVerifyCode = true
              this.focusOnVerifyCode()
            }
          } else if (result.code === '0002') {
            // 验证码错误
            this.errorMessage = result.message
            this.isVerifyCode = true
            this.focusOnVerifyCode()
          }
          this.refreshVerifyCode()
        })
      },

      /**
       * 验证，验证通过返回true，验证失败返回false
       */
      valid: function () {
        if (this.loginInfo.username.length <= 0) {
          document.getElementById('username').focus()
          this.errorMessage = '请您输入登录账户'
          return false
        }

        if (this.loginInfo.password.length <= 0) {
          document.getElementById('password').focus()
          this.errorMessage = '请您输入登录密码'
          return false
        }

        if (this.isVerifyCode && this.loginInfo.verifyCode.length <= 0) {
          this.focusOnVerifyCode()
          this.errorMessage = '请您输入验证码'
          return false
        }
        return true
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
      },

      /**
       * 点击登录后将用户按钮置为不可用
       */
      beforeLogin () {
        this.loginBtnDisabled = true
      },

      /**
       * 登录接口响应后将用户按钮置为可用
       */
      afterLogin () {
        this.loginBtnDisabled = false
      }
    },
    directives: {
      focus: {
        inserted: function (el) {
          el.focus()
        }
      }
    }
  }
</script>

<style>
  /*登录 Start */
  .login-container {
    background-color: #212124;
    color: #515151;
    text-align: center;
    font-size: 12px;
    padding: 0;
    vertical-align: middle;
    width: auto;
    min-height: 100vh;
  }

  .login-bg-img {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    opacity: .55;
    background: no-repeat fixed center center;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover
  }

  /*背景图片*/
  .login-bg-img-balloon {
    background-image: url("./images/bg-img-2.jpg");
  }

  .login-header {
    position: relative;
    background-color: rgba(0, 0, 0, 0.1);
    font-weight: 600;
    font-size: 30px;;
  }

  .login-header .login-header-title .brand-icon {
    width: 60px;
    height: 60px
  }

  .cls-container .login-header .login-header .login-header-title .brand-title {
    font-size: 27px
  }

  .login-header-title {
    display: inline-block;
    padding: 20px 0;
    line-height: 60px;
  }

  .login-header-title a {
    color: #bebebe
  }

  .login-header-title a:hover {
    color: #ffffff;
  }

  .login-body {
    padding: 10vh 15px 15px;
    position: relative;
    margin-top: 50px;
    margin-left: 5px;
    margin-right: 5px;
  }

  .login-body-panel {
    width: 70%;
    min-width: 250px;
    max-width: 380px;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 20px;
    background-color: #fff;
    border: 1px solid transparent;
    border-radius: 4px;
    -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
    box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
  }

  .login-body-panel-head {
    margin-top: 10px;
    margin-bottom: 20px;
    font-size: 15px;
  }

  .login-footer {
    font-size: 15px;
    color: #e0e0e0;
  }

  .login-footer a {
    color: #e0e0e0;
  }

  .login-footer a:hover {
    color: #FFFFFF;
  }

  .login-mar-rgt {
    margin-right: 15px;
  }

  .login-mar-lft {
    margin-left: 15px;
  }

  .login-icon {
    padding-left: 18px;
    padding-right: 18px;
  }

  .verify-code {
    margin-bottom: 10px
  }

  /*登录 End */
</style>
