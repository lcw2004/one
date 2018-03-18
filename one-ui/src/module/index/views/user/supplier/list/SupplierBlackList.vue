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
                  <th>评分</th>
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
                  <td>{{ obj.score == null ? '--' : obj.score }}</td>
                  <td>
                    <UserStatusLabel :status="obj.status" :desc="obj.statusCn"/>
                  </td>
                  <td>
                    <a @click="moveOutFromBlackList(obj)">移除</a>
                  </td>
                </tr>
              </tbody>
            </table>
            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
        </div>
      </div>
    </div>

    <MoveSupplierOutBalckListModal v-if="config.show" :config="config" :supplier="supplier" @ok="query()"></MoveSupplierOutBalckListModal>
  </section>
</template>

<script>
import PageMixin from '@mixins/PageMixin'
import UserInfoSimpleView from '../../common/UserInfoSimpleView'
import UserStatusLabel from '../../common/UserStatusLabel'
import MoveSupplierOutBalckListModal from '../modal/MoveSupplierOutBalckListModal.vue'

export default {
  mixins: [PageMixin],
  components: {
    UserInfoSimpleView,
    UserStatusLabel,
    MoveSupplierOutBalckListModal
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/supplier/blackList'}
      },
      param: {},
      config: {
        show: false,
        title: '从黑名单中移除'
      },
      supplier: {}
    }
  },
  methods: {
    moveOutFromBlackList (obj) {
      this.supplier = obj
      this.config.show = true
    }
  }
}
</script>
