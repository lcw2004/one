<template>
  <div class="login-container" @keyup.enter="login">
    <!-- BACKGROUND IMAGE -->
    <!--===================================================-->
    <div class="login-bg-img login-bg-img-balloon"></div>

    <!-- HEADER -->
    <!--===================================================-->
    <div class="login-header">
      <div class="login-header-title">
        <a>
          <span>{{ systemInfo.fullName }}</span>
        </a>
      </div>
    </div>
    <!--===================================================-->

    <!-- LOGIN FORM -->
    <!--===================================================-->
    <div class="login-body">
      <div class="login-body-panel">
        <div class="panel-body">
          <p class="login-body-panel-head">欢迎登录 {{ systemInfo.shortName }}</p>

          <form class="form login-form">
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-addon login-icon"><i class="fa fa-user" style="width: 15px;"></i></div>
                    <input type="text" id="username" v-focus v-model.trim="loginInfo.username" class="form-control" style="height: 38px" placeholder="请输入登录账号">
                  </div>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-addon login-icon"><i class="fa fa-asterisk" style="width: 15px;"></i></div>
                    <input type="password" id="password" v-model="loginInfo.password" class="form-control" style="height: 38px" placeholder="请输入密码">
                  </div>
                </div>
              </div>
            </div>

            <div class="row" v-if="isVerifyCode" style="margin-bottom: 10px">
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
                <p class="form-control-static text-red login-error">{{ errorMessage }}</p>
              </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <a class="btn btn-primary btn-lg btn-block" :class="{ disabled: loginBtnDisabled }" @click="login">
                  {{ loginBtnText }}
                </a>
              </div>
            </div>

            <div class="row" style="margin-top: 20px">
              <div class="col-md-6">
                <div class="pull-right">
                  <a href="account.html#/forget-password" class="btn-link login-mar-rgt">忘记密码 ?</a>
                </div>
              </div>
              <div class="col-md-6">
                <div class="pull-left">
                  <a href="account.html#/registry/agreement" class="btn-link login-mar-lft">注册帐号</a>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!--===================================================-->

    <footer class="navbar-fixed-bottom login-footer">
      Copyright &copy; 2012-2016 <a>{{ systemInfo.fullName }}</a> - Powered By <a :href="systemInfo.powerByLink" target="_blank">{{ systemInfo.powerByName }}</a> {{ systemInfo.version }}
    </footer>
  </div>
</template>

<script>
import VerifyCodeImg from '@components/form/verify-code/VerifyCodeImg'
import { getUrlQueryValue } from '@utils/common'
import api from '@api'

let systemInfo = {
  fullName: 'One Admin System',
  shortName: 'One',
  englishName: 'One',
  version: 'V1.2.4',
  powerByName: 'lcw2004',
  powerByLink: 'https://github.com/lcw2004'
}

export default {
  components: {
    VerifyCodeImg
  },
  data: function () {
    return {
      isVerifyCode: false, // 是否显示验证码
      timestamp: '', // 验证码时间戳
      loginInfo: {
        userType: 1,
        username: '',
        password: '',
        isRememberMe: false,
        verifyCode: ''
      },
      errorMessage: '',
      result: {}, // 登录结果
      loginBtnDisabled: false,
      loginBtnText: '登录'
    }
  },
  computed: {
    systemInfo: function () {
      return systemInfo
    }
  },
  methods: {
    checkIsLogin () {
      api.system.checkIsLogin().then((response) => {
        let result = response.data
        if (result.ok) {
          let isLogin = result.data.isLogin
          if (isLogin) {
            this.handlerForward(result.data)
          }
        }
      })
    },

    loadSystemInfo () {
      api.system.systemInfo().then((response) => {
        let result = response.data
        if (result.ok) {
          this.systemInfo = result.data
        }
      })
    },

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
      api.system.login(this.loginInfo).then((response) => {
        let result = response.data
        this.result = result
        if (result.ok) {
          // 登录成功
          this.handlerForward(result.data)
          this.afterLoginSuccess()
          return
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
        } else {
          this.errorMessage = result.message
        }
        this.refreshVerifyCode()
        this.afterLoginFail()
      }).catch((error) => {
        console.log(error)
        this.errorMessage = '登录失败，请重试！'
        this.afterLoginFail()
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
      this.loginBtnText = '登录中'
      this.errorMessage = null
    },
    /**
     * 登录失败后将用户按钮置为可用
     */
    afterLoginFail () {
      this.loginBtnDisabled = false
      this.loginBtnText = '登录'
    },
    /**
     * 登录成功之后，将登录按钮改为跳转中
     */
    afterLoginSuccess () {
      this.loginBtnDisabled = true
      this.loginBtnText = '跳转中，请稍候'
    },
    handlerForward (resultBody) {
      // 如果有重定向URL，则先跳转到重定向的页面
      let redirectUri = getUrlQueryValue('redirectUri')
      let authUrl = getUrlQueryValue('authUrl')
      if (redirectUri) {
        let newUrl = authUrl + '?redirectUri=' + redirectUri + '&authCode=' + resultBody.authCode
        window.location.href = newUrl
      } else {
        window.location.href = '/'
      }
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

<style lang="less" type="text/less">
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
    font-size: 13px;
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

  .login-user-img {
    height: 80px;
    width: 80px;
    background-size: 80px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 10px;
    margin-bottom: 10px;
  }

  .login-error {
    text-align: left;
    padding-top: 0 !important;
  }
</style>
