<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box box-primary">
          <div class="box-body">
            <form class="form-horizontal">
              <div class="row">
                <div class="col-md-6">
                  <FormGroup label="当前密码" required="true">
                    <input type="password" class="form-control" placeholder="请填写当前登录密码" v-validate="'required'" name="当前密码" v-model="oldPassword">
                  </FormGroup>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <FormGroup label="新密码" required="true">
                    <input type="password" class="form-control" placeholder="请填写新的登录密码" v-validate="'required|password'" name="新密码" v-model="newPassword">
                    <div class="row">
                      <div class="col-md-12">
                        <PasswordStrength :password="newPassword"></PasswordStrength>
                      </div>
                    </div>
                  </FormGroup>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <FormGroup label="确认密码" required="true">
                    <input type="password" class="form-control" placeholder="再次输入新的登录密码" v-validate="'required|confirmed:新密码'" name="确认密码" v-model="newPasswordConfirm">
                  </FormGroup>
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

    <el-dialog
      title="提示"
      :visible.sync="showLogoutDialog"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false"
      width="30%"
      center>
      <span>密码修改成功，请退出重新登录！</span>
      <span slot="footer" class="dialog-footer">
        <button type="button" class="btn btn-primary" @click="logout()">
          重新登录
        </button>
      </span>
    </el-dialog>
  </section>
</template>

<script>
import PasswordStrength from '../../user/common/PasswordStrength.vue'

export default {
  components: {
    PasswordStrength
  },
  data: function () {
    return {
      oldPassword: '',
      newPassword: '',
      newPasswordConfirm: '',
      showLogoutDialog: false
    }
  },
  methods: {
    updatePassword () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.$api.system.updateUserPassword(this.oldPassword, this.newPassword).then((response) => {
            let result = response.data
            if (result.ok) {
              this.showLogoutDialog = true
            }
          })
        } else {
          this.scrollToError()
        }
      })
    },
    logout () {
      this.$store.dispatch('logout')
    }
  }
}
</script>
