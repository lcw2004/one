<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
              <span aria-hidden="true">×</span>
            </button>
            <h4 class="modal-title"><span class="label label-primary">{{ purchaseType.name }}</span> 供应商列表</h4>
          </div>
          <div class="modal-body">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>供应商名称</th>
                  <th>联系人</th>
                  <th>手机号码</th>
                  <th>状态</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(obj, index) of page.list">
                  <td>{{ index + 1 }}</td>
                  <td>{{ obj.userSupplier.name }}</td>
                  <td>
                    <UserInfoSimpleView :user="obj.userSupplier.principalUser"></UserInfoSimpleView>
                  </td>
                  <td>{{ obj.userSupplier.principalUser.userContactInfo.mobile }}</td>
                  <td>
                    <template v-if="obj.status != 3">
                      <span v-if="obj.status == 1" class="label label-primary">审核中</span>
                      <span v-if="obj.status == 2" class="label label-success">审核通过</span>
                    </template>
                    <template v-if="obj.status == 3">
                      <PopoverContainer title="驳回原因">
                        <span slot="outer" class="label label-warning">审核驳回</span>
                        <div class="row">
                          <div class="col-md-12">
                            <p class="text-red">{{ obj.auditRemark }}</p>
                          </div>
                        </div>
                      </PopoverContainer>
                    </template>
                  </td>
                </tr>
              </tbody>
            </table>
            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
          <div class="modal-footer">
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
import UserInfoSimpleView from '../../user/common/UserInfoSimpleView'
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [PageMixin, ModalMixin],
  components: {
    UserInfoSimpleView
  },
  props: {
    config: {
      type: Object,
      required: true
    },
    purchaseType: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/supplier/byPurchaseType'}
      },
      param: {
        purchaseTypeId: ''
      }
    }
  },
  created () {
    this.param.purchaseTypeId = this.purchaseType.id
  },
  methods: {
    ok () {
      this.close()
      this.$notify.success('ok')
    }
  }
}
</script>
