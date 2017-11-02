<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <SupplierQueryCondition v-model="param"></SupplierQueryCondition>
          </div>

          <div class="box-body">
            <table class="table table-bordered table-hover">
              <thead>
              <tr>
                <th style="width: 10px">#</th>
                <th>供应商名称</th>
                <th>注册时间</th>
                <th>联系人</th>
                <th>手机号码</th>
                <th>状态</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(obj, index) of page.list">
                <td>{{ index + 1}}</td>
                <td>
                  <router-link :to='"/user/supplier/" + obj.supplierId + "/view"'>{{ obj.name }}</router-link>
                </td>
                <td>{{ obj.createTime }}</td>
                <td>
                  <UserInfoSimpleView :user="obj.principalUser"/>
                </td>
                <td>{{ obj.principalUser.userContactInfo.mobile }}</td>
                <td>
                  <UserStatusLabel :status="obj.status" :desc="obj.statusCn"/>
                </td>
                <td>
                  <router-link :to='"/user/supplier/" + obj.supplierId + "/edit"'>修改</router-link>
                  <a @click="deleteData(obj)">删除</a>
                  <a v-if="obj.status == -2" @click="start(obj)">启用</a>
                  <a v-if="obj.status == 2" @click="stop(obj)">停用</a>
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
  import PageMixin from 'mixins/PageMixin.js'
  import UserInfoSimpleView from '../common/UserInfoSimpleView'
  import UserStatusLabel from '../common/UserStatusLabel'

  export default {
    mixins: [PageMixin],
    components: {
      UserInfoSimpleView,
      UserStatusLabel
    },
    data: () => {
      return {
        actions: {
          list: {method: 'get', url: '/api/user/supplier'},
          delete: {method: 'delete', url: '/api/user/supplier{/id}'},
          stop: {method: 'put', url: '/api/user/supplier{/id}/stop'},
          start: {method: 'put', url: '/api/user/supplier{/id}/start'}
        },
        param: {},
        dictTypeList: []
      }
    },
    methods: {
      deleteData (obj) {
        this.$confirm('确认删除供应商吗？', () => {
          this.resource.deleteData({id: obj.supplierId}).then((response) => {
            if (response.body.ok) {
              this.query()
              this.$notify.success('删除成功！')
            }
          })
        })
      },
      stop (obj) {
        this.$confirm('确认停用供应商吗？', () => {
          this.resource.stop({id: obj.supplierId}, null).then((response) => {
            if (response.body.ok) {
              this.query()
              this.$notify.success('停用成功！')
            }
          })
        })
      },
      start (obj) {
        this.$confirm('确认启用供应商吗？', () => {
          this.resource.start({id: obj.supplierId}, null).then((response) => {
            if (response.body.ok) {
              this.query()
              this.$notify.success('启用成功！')
            }
          })
        })
      }
    }
  }
</script>
