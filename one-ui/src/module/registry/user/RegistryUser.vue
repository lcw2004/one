<template>
  <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
      <div class="box box-solid">
        <div class="box-header">
          <div class="text-center registry-title">
            <h1 class="box-title">供应商注册</h1>
          </div>
        </div>

        <div class="box-body">
          <div class="row">
            <div class="col-md-12">
              <form class="form-horizontal">
                <div class="form-group" v-render.r="'供应商全称'">
                  <label class="control-label col-md-2">供应商全称</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" v-model.trim="user.supplierName" v-validate="'required'" name="供应商全称" maxlength="50"
                           @blur="validSupplierName">
                  </div>
                  <div class="col-md-6">
                    <p class="help-block">供应商全称必须与公司公章相符，不得含其它字符!</p>
                    <p class="help-block" v-if="errors.has('供应商全称:is_exist')">
                      该机构已经注册过了，点此 <a href="login.html">登录系统</a>。</br>
                      如果您不记得账号，点此<router-link to="/forget-account">找回账户</router-link>。</br>
                      如果您不记得密码，点此<router-link to="/forget-password">重置密码</router-link>。
                    </p>
                  </div>
                </div>

                <div class="form-group" v-render.r="'登录帐号'">
                  <label class="control-label col-md-2">登录帐号</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" v-model.trim="user.account" v-validate="'required'" name="登录帐号" @blur="validAccount" maxlength="50">
                  </div>
                  <div class="col-md-6">
                    <p class="help-block">只能输入3-20个字母或者数字的组合字串且不包含空格</p>
                  </div>
                </div>

                <div class="form-group" v-render.r="'密码'">
                  <label class="control-label col-md-2">密码</label>
                  <div class="col-md-4">
                    <input type="password" class="form-control" v-model="user.password" v-validate="'required'" name="密码" maxlength="50">
                  </div>
                  <div class="col-md-6">
                    <p class="help-block">请输入6-20位字母加数字或符号!</p>
                  </div>
                </div>

                <div class="form-group" v-render.r="'确认密码'">
                  <label class="control-label col-md-2">确认密码</label>
                  <div class="col-md-4">
                    <input type="password" class="form-control" v-model="user.passwordConfirm" v-validate="'required|confirmed:密码'" name="确认密码" maxlength="50">
                  </div>
                  <div class="col-md-6">
                    <p class="help-block">请再次输入密码!</p>
                  </div>
                </div>

                <div class="form-group" v-render.r="'联系人姓名'">
                  <label class="control-label col-md-2">联系人姓名</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" v-model.trim="user.userName" v-validate="'required'" name="联系人姓名" maxlength="20">
                  </div>
                  <div class="col-md-6">
                    <p class="help-block"></p>
                  </div>
                </div>

                <div class="form-group" v-render.r="'手机号码'">
                  <label class="control-label col-md-2">手机号码</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" v-model.trim="user.mobile" v-validate="'required'" name="手机号码" maxlength="11">
                  </div>
                  <div class="col-md-6">
                    <p class="help-block"></p>
                  </div>
                </div>

                <div class="form-group">
                  <label class="control-label col-md-2">电话</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" v-model.trim="user.phone" maxlength="12">
                  </div>
                  <div class="col-md-6">
                    <p class="help-block"></p>
                  </div>
                </div>

                <div class="form-group" v-render.r="'邮箱'">
                  <label class="control-label col-md-2">邮箱</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" v-model.trim="user.email" v-validate="'required|email'" name="邮箱" maxlength="50">
                  </div>
                  <div class="col-md-6">
                    <p class="help-block">请输入验证邮箱，您可通过该邮箱找回账号或者密码!</p>
                  </div>
                </div>

                <div class="form-group" v-render="'验证码'">
                  <label class="control-label col-md-2">验证码</label>
                  <div class="col-md-2">
                    <input type="text" class="form-control" v-model.trim="user.validCode" v-validate="'required'" name="验证码" maxlength="6">
                  </div>
                  <div class="col-md-2">
                    <button type="button" class="btn btn-primary" @click="sendVerifyCode" :class="{ disabled : leftTime > 0}">
                      发送验证码
                      <template v-if="leftTime > 0">({{ leftTime }})</template>
                    </button>
                  </div>
                  <div class="col-md-6">
                    <p class="help-block" v-if="validCodeMessage">{{ validCodeMessage }}</p>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>

        <div class="box-footer">
          <div class="row">
            <div class="col-md-12">
              <div class="col-md-5"></div>
              <div class="col-md-2">
                <button type="button" class="btn btn-block btn-primary" @click="registry">
                  注 册
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
  import Step from '../step/Step'
  import StepItem from '../step/StepItem'
  export default {
    components: {
      Step,
      StepItem
    },
    data: function () {
      return {
        leftTime: 0,
        defaultLeftTime: 10,
        validCodeMessage: '',

        user: {
          supplierName: '',
          account: '',
          password: '',
          passwordConfirm: '',
          userName: '',
          mobile: '',
          phone: '',
          email: '',
          validCode: ''
        }
      }
    },
    methods: {
      sendVerifyCode () {
        if (this.leftTime > 0) {
          return
        }

        // 发送验证码
        this.send()
        this.validSupplierName()

        // 倒计时
        this.leftTime = this.defaultLeftTime
        let interval = setInterval(() => {
          if (this.leftTime > 0) {
            this.leftTime--
          } else {
            clearInterval(interval)
          }
        }, 1000)
      },
      send () {
        let email = this.user.email
        this.$http.get('/api/user/supplierRegistry/sendValidCode?email=' + email).then(function (response) {
          console.log(response.body)
          if (response.body.ok) {
            this.validCodeMessage = '验证码发送成功！'
          } else {
            this.validCodeMessage = response.body.message
          }
        })
      },
      validSupplierName () {
        if (!this.user.supplierName.trim()) {
          return
        }
        this.$http.get('/api/user/supplierRegistry/validName?name=' + this.user.supplierName).then(function (response) {
          let result = response.body
          if (result.ok) {
            this.errors.remove('供应商全称', 'is_exist')
          } else {
            this.errors.add('供应商全称', '供应商已经存在', 'is_exist')
          }
        })
      },
      validAccount () {
        if (!this.user.account.trim()) {
          return
        }
        this.$http.get('/api/user/supplierRegistry/validAccount?account=' + this.user.account).then(function (response) {
          let result = response.body
          if (result.ok) {
            this.errors.remove('', 'is_exist')
          } else {
            this.errors.add('注册帐号', '账户已经存在', 'is_exist')
          }
        })
      },
      registry () {
        this.$validator.validateAll().then(() => {
          this.$http.post('/api/user/supplierRegistry/registryUserInfo', JSON.stringify(this.user)).then(function (response) {
            let result = response.body
            if (result.ok && result.data) {
              this.$router.push('/registry/success')
            } else {
              this.handlerError(result)
            }
          })
        }).catch(() => {
        })
      },
      handlerError (result) {
        if (result.code === '0001') {
          this.errors.add('供应商全称', result.message, 'api_valid')
        } else if (result.code === '0002') {
          this.errors.add('注册帐号', result.message, 'api_valid')
        } else if (result.code === '0003') {
          this.errors.add('验证码', result.message, 'api_valid')
        }
      }
    }
  }
</script>
