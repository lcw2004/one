<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
              <span aria-hidden="true">×</span>
            </button>
            <h4 class="modal-title">{{ config.title }}</h4>
          </div>
          <div class="modal-body">
            <div class="row row-margin-bottom">
              <div class="col-md-12">
                <SupplierQueryCondition v-model="param" :purchaseType="purchaseType"></SupplierQueryCondition>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <table class="table table-bordered table-hover">
                  <thead>
                    <tr>
                      <th style="width: 50px">#</th>
                      <th>供应商名称</th>
                      <th>注册时间</th>
                      <th>联系人</th>
                      <th>手机号码</th>
                      <th>状态</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(obj, index) of page.list" @click="selectedSupplier = obj">
                      <td>
                        <input type="radio" :value="obj" :key="obj.supplierId" v-model="selectedSupplier">
                      </td>
                      <td>{{ obj.name }}</td>
                      <td>{{ obj.createTime }}</td>
                      <td>
                        <UserInfoSimpleView :user="obj.principalUser"></UserInfoSimpleView>
                      </td>
                      <td>{{ obj.principalUser.userContactInfo.mobile }}</td>
                      <td>
                        <UserStatusLabel :status="obj.status" :desc="obj.statusCn"></UserStatusLabel>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <div class="pull-left">
              选中 <span class="label label-info">{{ selectedSupplier.name }}</span>&nbsp;
            </div>
            <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">取消</button>
            <button type="button" class="btn btn-primary" @click="ok()">确认</button>
          </div>
        </div>
      </div>
    </div>
  </OneTransition>
</template>

<script>
import PageMixin from '@mixins/PageMixin'
import ModalMixin from '@mixins/ModalMixin'
import UserInfoSimpleView from '../common/UserInfoSimpleView'
import UserStatusLabel from '../common/UserStatusLabel'

export default {
  mixins: [PageMixin, ModalMixin],
  components: {
    UserInfoSimpleView,
    UserStatusLabel
  },
  props: {
    config: {
      type: Object,
      required: true
    },
    purchaseType: {
      type: Object,
      required: true
    },
    value: {}
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/supplier'}
      },
      param: {
        supplierStatus: 2,
        purchaseTypeId: ''
      },
      selectedSupplier: {}
    }
  },
  methods: {
    valid () {
      if (!this.selectedSupplier.supplierId) {
        this.$notify.warn('请先选择供应商')
        return false
      }
      return true
    },
    ok () {
      if (!this.selectedSupplier.supplierId) {
        this.$notify.warn('请先选择供应商')
        return false
      }
      this.$emit('input', this.selectedSupplier)
      this.close()
    }
  }
}
</script>
