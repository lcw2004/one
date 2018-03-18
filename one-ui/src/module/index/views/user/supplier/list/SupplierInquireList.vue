<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box box-primary">
          <div class="box-header">
            <form class="form-horizontal">
              <SupplierQueryCondition v-model="param"></SupplierQueryCondition>
            </form>
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
                  <th>等级</th>
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
                    <SupplierStatusLabel :status="obj.status" :desc="obj.statusCn"/>
                  </td>
                  <td>
                    <SupplierLevelLabel :value="obj.level"/>
                  </td>
                  <td>
                    <a @click="addInquireRecordModal(obj)">上传调查记录</a>
                  </td>
                </tr>
              </tbody>
            </table>
            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
        </div>
      </div>
    </div>

    <AddInquireRecordModal v-if="config.show" :config="config" :supplier="supplier" @ok="query()"></AddInquireRecordModal>
  </section>
</template>

<script>
import AddInquireRecordModal from '../modal/AddInquireRecordModal.vue'
import PageMixin from '@mixins/PageMixin'
import UserInfoSimpleView from '../../common/UserInfoSimpleView'
import SupplierLevelLabel from '../common/SupplierLevelLabel.vue'
import SupplierStatusLabel from '../common/SupplierStatusLabel'

export default {
  mixins: [PageMixin],
  components: {
    UserInfoSimpleView,
    SupplierLevelLabel,
    AddInquireRecordModal,
    SupplierStatusLabel
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/supplier'}
      },
      param: {},
      config: {
        show: false,
        title: '上传调查记录'
      },
      supplier: {}
    }
  },
  methods: {
    addInquireRecordModal (obj) {
      this.config.show = true
      this.supplier = obj
    }
  }
}
</script>
