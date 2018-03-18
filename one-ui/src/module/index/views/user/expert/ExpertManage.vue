<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box box-primary">
          <div class="box-header">
            <ExpertQueryCondition v-model="param"></ExpertQueryCondition>

            <div class="row">
              <div class="col-md-12">
                <div class="pull-right">
                  <router-link to="/user/expert/add" class="btn btn-primary btn-sm">添加专家</router-link>
                </div>
              </div>
            </div>
          </div>

          <div class="box-body">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>姓名</th>
                  <th>登录名</th>
                  <th>单位</th>
                  <th>电话</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(obj, index) of page.list">
                  <td>{{ index + 1}}</td>
                  <td>
                    <router-link :to="'/user/expert/' + obj.userId + '/view'">{{ obj.userInfo.name }}</router-link>
                  </td>
                  <td>{{ obj.userInfo.account }}</td>
                  <td>某某公司</td>
                  <td>{{ obj.userInfo.userContactInfo.mobile }}</td>
                  <td>
                    <UserStatusLabel :status="obj.userInfo.status" :desc="obj.userInfo.statusCn"/>
                  </td>
                  <td>
                    <router-link :to="'/user/expert/' + obj.userId + '/edit'">修改</router-link>
                    <a @click="deleteData(obj)">删除</a>
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
import UserInfoSimpleView from '../common/UserInfoSimpleView'
import UserStatusLabel from '../common/UserStatusLabel'

export default {
  mixins: [PageMixin],
  components: {
    UserInfoSimpleView,
    UserStatusLabel
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/expert'}
      },
      param: {}
    }
  },
  methods: {
    deleteData (obj) {
      this.$confirm(`确认删除[${obj.userInfo.name}]吗？`, () => {
        this.$api.user.deleteExpert(obj.id).then((response) => {
          let result = response.data
          if (result.ok) {
            this.query()
            this.$notify.success('删除成功！')
          }
        })
      })
    }
  }
}
</script>
