<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <h3 class="box-title">用户信息</h3>
    </div>
    <div class="box-body">
      <form class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="归属公司">
              <div class="input-group">
                <input type="text" class="form-control" v-model="obj.sysOffice.name" v-validate="'required'" name="归属公司" />
                <span class="input-group-btn">
                    <button class="btn btn-info" type="button" @click="companyTreeModalConfig.show = true">选择</button>
                  </span>
              </div>
              <SelectOfficeModal :config="companyTreeModalConfig" v-model="obj.sysOffice"></SelectOfficeModal>
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="工号">
              <input type="text" class="form-control" v-model="obj.workNumber" readonly />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="姓名">
              <input type="text" class="form-control" v-model="obj.userInfo.name" v-validate="'required'" name="姓名" maxlength="50"/>
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="登录账号">
              <input type="text" class="form-control" v-model="obj.userInfo.account" readonly />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="登录密码">
              <input type="password" class="form-control" v-model="obj.password" v-validate="'password'" name="登录密码" maxlength="20"/>
            </FormGroup>
          </div>
          <div class="col-md-6" v-if="obj.userId">
            <span class="help-block">如果不需要修改密码，请将密码置空</span>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="邮箱">
              <input type="text" class="form-control" v-model="obj.userInfo.userContactInfo.email" v-validate="'email'" name="邮箱" />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="电话">
              <input type="text" class="form-control" v-model="obj.userInfo.userContactInfo.phone"/>
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="手机">
              <input type="text" class="form-control" v-model="obj.userInfo.userContactInfo.mobile"  v-validate="'required|mobile'" name="手机"/>
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <FormGroup label="角色" width="2">
              <div>
                <div class="checkbox-inline" v-for="role of rolelist">
                  <label>
                    <input type="checkbox" :value="role.id" v-model="obj.roleIdList"> {{ role.name }}
                  </label>
                </div>
              </div>
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="备注">
              <textarea type="text" class="form-control" v-model="obj.remark" maxlength="200"></textarea>
            </FormGroup>
          </div>
        </div>
      </form>
    </div>
    <div class="box-footer">
      <div class="row">
        <div class="col-md-2 col-md-offset-2">
          <a class="btn btn-block btn-primary" @click="validAndSave()">保存</a>
        </div>
        <div class="col-md-2">
          <a class="btn btn-block btn-default" @click="$router.go(-1)">返回</a>
        </div>
      </div>
    </div>
  </div>
</section>
</template>

<script>
  import UserMixin from './UserMixin.js'

  export default {
    mixins: [UserMixin]
  }
</script>
