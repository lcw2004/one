<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box box-primary">
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
                    <span v-if="obj.status == -1">{{ obj.statusCn }}</span>
                    <span v-if="obj.status == 1" class="label label-primary">{{ obj.statusCn }}</span>
                    <span v-if="obj.status == 2" class="label label-success">{{ obj.statusCn }}</span>
                    <span v-if="obj.status == 3" class="label label-warning">{{ obj.statusCn }}</span>
                    <span v-if="obj.status == 4" class="label label-default">{{ obj.statusCn }}</span>
                    <span v-if="obj.status == 5">{{ obj.statusCn }}</span>
                  </td>
                  <td>
                    <router-link :to='"/user/supplier/purchase-audit/" + obj.supplierId'>审核</router-link>
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
import UserInfoSimpleView from '../../common/UserInfoSimpleView'

export default {
  mixins: [PageMixin],
  components: {
    UserInfoSimpleView
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/supplier/audit'}
      },
      param: {},
      dictTypeList: []
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
