<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row row-margin-bottom">
            <div class="col-md-4">
              <FormGroup label="姓名">
                <input class="form-control" type="text" placeholder="姓名" v-model="param.name">
              </FormGroup>
            </div>
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
              <div class="pull-right">
                <router-link to="/system/user/add" class="btn btn-primary">添加</router-link>
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
                  <th>登录名</th>
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
                  <td class="break-word">
                    <template v-for="(roleName, index) of obj.roleNameList">
                      {{ roleName }}<template v-if="index < obj.roleNameList.length - 1">, </template>
                    </template>
                  </td>
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
  </section>
</template>

<script>
import PageMixin from '@mixins/PageMixin'

export default {
  mixins: [PageMixin],
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/manager'}
      },
      param: {
      }
    }
  },
  methods: {
    deleteData (id) {
      this.$confirm('确认删除吗？', () => {
        this.$api.user.deleteManagerUser(id).then((response) => {
          let result = response.data
          if (result.ok) {
            this.query()
            this.$notify.success('删除成功！')
          }
        })
      })
    }
  },
  watch: {
    'param': {
      handler: function () {
        this.query()
      },
      deep: true
    }
  }
}
</script>
