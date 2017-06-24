<template>
  <div class="box box-solid">
    <div class="box-header">
      <h3 class="box-title">个人信息</h3>
    </div>
    <div class="box-body">
      <section>
        <h4 class="page-header">基本信息</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group" v-render.r="'姓名'">
                <label class="col-md-4 control-label">姓名</label>
                <div class="col-md-8">
                  <div class="input-group">
                    <input type="text" class="form-control" v-model="user.name" v-validate="'required'" name="姓名" maxlength="50">
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6" v-render.r="'身份证号码'">
              <div class="form-group">
                <label class="col-md-4 control-label">身份证号码</label>
                <div class="col-md-8">
                  <div class="input-group">
                    <input type="text" class="form-control" v-model="user.identityNumber" v-validate="'required'" name="身份证号码" maxlength="18">
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">登录账号</label>
                <div class="col-md-8">
                  <p class="form-control-static">{{ user.account }}</p>
                </div>
              </div>
            </div>
          </div>
        </form>
      </section>

      <section>
        <h4 class="page-header">联系方式</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group" v-render.r="'手机号码'">
                <label class="col-md-4 control-label">手机号码</label>
                <div class="col-md-8">
                  <div class="input-group">
                    <input type="text" class="form-control" v-model="user.userContactInfo.mobile" v-validate="'required'" name="手机号码" maxlength="11">
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">座机号码</label>
                <div class="col-md-8">
                  <div class="input-group">
                    <input type="text" class="form-control" v-model="user.userContactInfo.phone" maxlength="12">
                  </div>
                </div>
              </div>
            </div>

            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">电子邮箱</label>
                <div class="col-md-8">
                  <div class="input-group">
                    <p class="form-control-static">{{ user.userContactInfo.email }}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="col-md-4 control-label">用户地址</label>
                <div class="col-md-8">
                  <div class="input-group">
                    <input type="text" class="form-control" v-model="user.userContactInfo.address" maxlength="100">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </form>
      </section>
    </div>
    <div class="box-footer">
      <div class="row">
        <div class="col-md-2 col-md-offset-2">
          <button type="button" class="btn btn-block btn-primary" @click="save()">
            保存
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data: () => {
      return {
        actions: {
          updateUserInfo: {method: 'put', url: '/api/updateUserInfo'}
        }
      }
    },
    computed: {
      user: function () {
        return this.$store.state.system.userInfo
      }
    },
    mounted: function () {
      this.resource = this.$resource(null, {}, this.actions)
    },
    methods: {
      save: function () {
        this.$validator.validateAll().then(() => {
          this.resource.updateUserInfo(null, JSON.stringify(this.user)).then((response) => {
            let result = response.body
            if (result.ok) {
              this.$store.dispatch('initUserInfo', result.data)
              this.$notify.success('修改成功')
              this.editEnd()
            }
          })
        }).catch(() => {
        })
      },
      editEnd () {
        this.$emit('edit-end')
      }
    }
  }
</script>
