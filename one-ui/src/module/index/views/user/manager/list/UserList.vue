<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row margin-bottom-10">
            <div class="col-md-4">
              <FormGroup label="查询条件">
                <input class="form-control" type="text" placeholder="登录账号/姓名/电话/手机" v-model.trim="param.userLikeName">
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="角色">
                <SelectRoleId v-model="param.roleIdList"></SelectRoleId>
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="所属机构">
                <div class="input-group">
                  <input type="text" class="form-control" v-model="selectOffice.name" readonly @click="sysOfficeConfig.show = true">
                  <span class="input-group-btn">
                    <button type="button" class="btn btn-primary" v-if="!selectOffice.name" @click="sysOfficeConfig.show = true">选择</button>
                    <button type="button" class="btn btn-primary" v-if="selectOffice.name" @click="clearSysOffice()">清空</button>
                  </span>
                </div>
              </FormGroup>
            </div>
            <div class="col-md-12">
              <div class="pull-right">
                <router-link to="/system/user/add" class="btn btn-primary">添加</router-link>
                <button type="button" class="btn btn-primary" @click="config.show = true">导入</button>
              </div>
            </div>
          </div>
        </form>
        <!--Query End-->

        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>归属公司</th>
                  <th>登录账号</th>
                  <th>姓名</th>
                  <th>电话</th>
                  <th>手机</th>
                  <th>角色</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="obj of page.list">
                  <td>{{ obj.sysOffice.name }}</td>
                  <td>{{ obj.userInfo.account }}</td>
                  <td>{{ obj.userInfo.name }}</td>
                  <td>{{ obj.userInfo.userContactInfo.phone }}</td>
                  <td>{{ obj.userInfo.userContactInfo.mobile }}</td>
                  <td class="break-word">{{ roleName(obj) }}</td>
                  <td>
                    <router-link :to='"/system/user/" + obj.userId + "/form"'>修改</router-link>
                    <a @click="deleteData(obj.userId)">删除</a>
                  </td>
                </tr>
              </tbody>
            </table>
            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
        </div>
      </div>
    </div>

    <UserManagerImportModal v-if="config.show" :config="config" @ok="query"></UserManagerImportModal>
    <SelectOfficeModal :config="sysOfficeConfig" v-model="selectOffice"></SelectOfficeModal>

  </section>
</template>

<script>
import PageMixin from '@mixins/PageMixin'
import SelectRoleId from '../../../system/modal/SelectRoleId'
import UserManagerImportModal from '../import/UserManagerImportModal.vue'
import SelectOfficeModal from '../../../system/modal/SelectOfficeModal.vue'

export default {
  mixins: [PageMixin],
  components: {
    SelectRoleId,
    UserManagerImportModal,
    SelectOfficeModal
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/manager'}
      },
      param: {
        userLikeName: '',
        roleIdList: '',
        officeId: ''
      },
      config: {
        show: false,
        title: '导入用户'
      },
      sysOfficeConfig: {
        show: false,
        title: '选择机构'
      },
      selectOffice: {}
    }
  },
  created () {
    this.obj = this.page.list
    this.param.officeId = this.obj.sysOffice.id
    this.selectOffice = this.obj.sysOffice
  },
  methods: {
    deleteData (id) {
      this.$confirm('确认删除用户吗？', () => {
        this.$api.user.deleteManagerUser(id).then((response) => {
          let result = response.data
          if (result.ok) {
            this.query()
            this.$notify.success('删除成功！')
          }
        })
      })
    },
    roleName (obj) {
      let roleNameStr = ''
      for (let i = 0; i < obj.roleNameList.length; i++) {
        roleNameStr += obj.roleNameList[i]
        if (roleNameStr.length > 20) {
          roleNameStr += ' ...'
          break
        }
        roleNameStr += i < obj.roleNameList.length - 1 ? ', ' : ''
      }
      return roleNameStr
    },
    clearSysOffice () {
      this.param.officeId = ''
      this.selectOffice = {}
    }
  },
  watch: {
    'selectOffice': {
      handler: function () {
        if (this.selectOffice) {
          this.param.officeId = this.selectOffice.id
        } else {
          this.clearSysOffice()
        }
      },
      deep: true
    }
  }
}
</script>
