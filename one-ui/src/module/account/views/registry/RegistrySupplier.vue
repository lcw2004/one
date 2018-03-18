<template>
  <OutContainer title="供应商注册">
    <div class="row row-margin-bottom">
      <div class="col-md-12">
        &nbsp;
      </div>
    </div>

    <form class="form-horizontal">
      <div class="row">
        <div class="col-md-6">
          <FormGroup label="供应商全称" :required="true">
            <input type="text" class="form-control" v-model.trim="user.supplierName" v-validate="'required'" name="供应商全称" maxlength="50"
                   @blur="validSupplierName">
          </FormGroup>
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

      <div class="row">
        <div class="col-md-6">
          <FormGroup label="登录帐号" :required="true">
            <input type="text" class="form-control" v-model.trim="user.account" v-validate="'required'" name="登录帐号" @blur="validAccount" maxlength="50">
          </FormGroup>
        </div>
        <div class="col-md-6">
          <p class="help-block">只能输入3-20个字母或者数字的组合字串且不包含空格</p>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <FormGroup label="密码" :required="true">
            <input type="password" class="form-control" v-model="user.password" v-validate="'required|password'" name="密码" maxlength="50">
          </FormGroup>
        </div>
        <div class="col-md-6">
          <p class="help-block">请输入6-20位字母加数字或符号!</p>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <FormGroup label="确认密码" :required="true">
            <input type="password" class="form-control" v-model="user.passwordConfirm" v-validate="'required|confirmed:密码'" name="确认密码" maxlength="50">
          </FormGroup>
        </div>
        <div class="col-md-6">
          <p class="help-block">请再次输入密码!</p>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <FormGroup label="联系人姓名" :required="true">
            <input type="text" class="form-control" v-model.trim="user.userName" v-validate="'required'" name="联系人姓名" maxlength="20">
          </FormGroup>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <FormGroup label="联系人手机号码" :required="true">
            <input type="text" class="form-control" v-model.trim="user.mobile" v-validate="'required|mobile'" name="联系人手机号码" maxlength="11">
          </FormGroup>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <FormGroup label="联系人座机号码">
            <input type="text" class="form-control" v-model.trim="user.phone" maxlength="15">
          </FormGroup>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <FormGroup label="联系人邮箱" :required="true">
            <input type="text" class="form-control" v-model.trim="user.email" v-validate="'required|email'" name="联系人邮箱" maxlength="50">
          </FormGroup>
        </div>
        <div class="col-md-6">
          <p class="help-block">请输入验证邮箱，您可通过该邮箱找回账号或者密码!</p>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <FormGroup label="验证码">
            <div class="input-group">
              <input type="text" class="form-control" v-model.trim="user.validCode" v-validate="'required'" name="验证码" maxlength="6">
              <span class="input-group-btn">
                <button type="button" class="btn btn-primary btn-flat" @click="sendVerifyCode()" :disabled="leftTime > 0">
                  发送验证码<template v-if="leftTime > 0">({{ leftTime }})</template>
                </button>
              </span>
            </div>
          </FormGroup>
        </div>
        <div class="col-md-6">
          <p class="form-control-static" v-show="validCodeMessage">{{ validCodeMessage }}</p>
        </div>
      </div>
    </form>

    <div class="row">
      <div class="col-md-12">
        <div class="col-md-3 col-md-offset-5" style="margin-top: 10px;margin-bottom: 10px;">
          <button type="button" class="btn btn-block btn-flat btn-primary" @click="registry">
            <i class="fa fa-fw fa-spinner fa-spin" v-if="loading"></i>
            注 册
          </button>
        </div>
      </div>
    </div>
  </OutContainer>
</template>

<script>
import OutContainer from '../common/OutContainer.vue'
import api from '@api'

export default {
  components: {
    OutContainer
  },
  data: function () {
    return {
      leftTime: 0,
      defaultLeftTime: 60,
      validCodeMessage: '',
      loading: false,
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
    /**
     * 发送验证码
     */
    sendVerifyCode () {
      if (this.leftTime > 0) {
        return
      }
      this.$validator.validateAll({'联系人邮箱': this.user.email}).then((result) => {
        if (result) {
          // 发送验证码
          this.send()
          // 倒计时
          this.leftTime = this.defaultLeftTime
          let interval = setInterval(() => {
            if (this.leftTime > 0) {
              this.leftTime--
            } else {
              clearInterval(interval)
            }
          }, 1000)
        }
      })
    },
    /**
     * 发送验证码
     */
    send () {
      if (this.user.email && this.user.email.trim()) {
        api.system.sendValidCode(this.user.email).then((response) => {
          if (response.data.ok) {
            this.validCodeMessage = '验证码发送成功！验证码在5分钟内有效，请尽快完成注册。如果一分钟后还未收到验证码，请重新发送。'
          } else {
            this.validCodeMessage = response.body.message
          }
        })
      }
    },
    /**
     * 验证供应商名称
     */
    validSupplierName () {
      if (!this.user.supplierName.trim()) {
        return
      }
      api.system.validSupplierName(this.user.supplierName).then((response) => {
        if (response.data.ok) {
          this.errors.remove('供应商全称', 'is_exist')
        } else {
          this.errors.add('供应商全称', '供应商已经存在', 'is_exist')
        }
      })
    },
    /**
     * 验证登录账户
     */
    validAccount () {
      if (!this.user.account.trim()) {
        return
      }
      api.system.validRegistryAccount(this.user.account).then((response) => {
        let result = response.data
        if (result.ok) {
          this.errors.remove('登录帐号', 'is_exist')
        } else {
          this.errors.add('登录帐号', '登录帐号已经存在', 'is_exist')
        }
      })
    },
    /**
     * 注册用户
     */
    registry () {
      if (this.loading) {
        return
      }
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.loading = true
          api.system.registrySupplier(this.user).then((response) => {
            this.loading = false
            let result = response.data
            if (result.ok && result.data) {
              this.$router.push('/registry/success')
            } else {
              this.handlerError(result)
            }
          })
        }
      })
    },
    handlerError (result) {
      if (result.code === '0001') {
        this.errors.add('供应商全称', result.message, 'api_valid')
      } else if (result.code === '0002') {
        this.errors.add('登录帐号', result.message, 'api_valid')
      } else if (result.code === '0003') {
        this.errors.add('验证码', result.message, 'api_valid')
      }
    }
  }
}
</script>
