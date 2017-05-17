<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <h3 class="box-title">用户信息</h3>
    </div>
    <div class="box-body">
      <form class="form-horizontal">
        <div class="form-group" v-render.r="归属公司">
          <label class="col-sm-2 control-label">归属公司</label>
          <div class="col-sm-4">
            <div class="input-group">
              <input type="text" class="form-control" v-model="obj.company.name" v-validate="'required'" name="归属公司" />
              <span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="companyTreeModalConfig.show = true">选择</button>
								</span>
            </div>
            <SelectOfficeModal :config="companyTreeModalConfig" v-model="obj.company"></SelectOfficeModal>
          </div>
        </div>
        <div class="form-group" v-render.r="归属部门">
          <label class="col-sm-2 control-label">归属部门</label>
          <div class="col-sm-4">
            <div class="input-group">
              <input type="text" class="form-control" v-model="obj.office.name" v-validate="'required'" name="归属部门" />
              <span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="officeTreeModalConfig.show = true">选择</button>
								</span>
            </div>
            <SelectOfficeModal :config="officeTreeModalConfig" v-model="obj.office"></SelectOfficeModal>
          </div>
        </div>
        <div class="form-group" v-render.r.i="工号">
          <label class="col-sm-2 control-label">工号</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.no"  v-validate="'required'" name="工号"/>
          </div>
        </div>
        <div class="form-group" v-render.r="姓名">
          <label class="col-sm-2 control-label">姓名</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.name" v-validate="'required'" name="姓名"/>
          </div>
        </div>
        <div class="form-group" v-render.r="登录名">
          <label class="col-sm-2 control-label">登录名</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.loginName" v-validate="'required'" name="登录名"/>
          </div>
        </div>
        <div class="form-group" v-render="邮箱">
          <label class="col-sm-2 control-label">邮箱</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.email" v-validate="'email'" name="邮箱"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">电话</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.phone" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">手机</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.mobile" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">角色</label>
          <div class="col-sm-8">
            <div class="checkbox">
              <label v-for="role of roles.list">
									<input type="checkbox" :value="role.id" v-model="obj.roleIdList"> {{ role.name }}
								</label>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">备注</label>
          <div class="col-sm-4">
            <textarea type="text" class="form-control" v-model="obj.remarks"></textarea>
          </div>
        </div>
      </form>
    </div>
    <div class="box-footer">
      <div class="row">
        <div class="col-md-2 col-md-offset-2">
          <a class="btn btn-block btn-primary" @click="save()">保存</a>
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
import SelectOfficeModal from '../modal/SelectOfficeModal'
import $ from 'jquery'

export default {
  components: {
    SelectOfficeModal
  },
  data: function () {
    return {
      checkboxValue: ['1', '2'],
      obj: {
        id: '',
        company: {},
        office: {},
        no: '',
        name: '',
        loginName: '',
        email: '',
        phone: '',
        mobile: '',
        remarks: '',
        roleIdList: []
      },
      roles: {},
      companyTreeModalConfig: {
        show: false,
        title: '选择所属机构'
      },
      officeTreeModalConfig: {
        show: false,
        title: '选择所属部门'
      }
    }
  },
  mounted () {
    let actions = {
      get: {method: 'get', url: '/api/sys/user{/id}'},
      getRole: {method: 'get', url: '/api/sys/role'},
      save: {method: 'post', url: '/api/sys/user'},
      update: {method: 'put', url: '/api/sys/user'}
    }
    this.resource = this.$resource(null, {}, actions)
    this.load()
    this.loadRole()

    $
    // $(':checkbox').iCheck({
    //   checkboxClass: 'icheckbox_flat-blue',
    //   radioClass: 'iradio_flat-blue',
    //   increaseArea: '20%' // optional
    // })
  },
  methods: {
    load: function () {
      let id = this.$route.params.id
      if (id) {
        this.resource.get({id: id}).then(function (response) {
          this.obj = response.body
        })
      }
    },
    loadRole: function () {
      this.resource.getRole().then(function (response) {
        this.roles = response.body
      })
    },
    save: function () {
      this.resource.save(null, JSON.stringify(this.obj)).then(function (response) {
        this.$notify.success('保存成功')
        this.$router.go(-1)
      })
    }
  }
}
</script>
