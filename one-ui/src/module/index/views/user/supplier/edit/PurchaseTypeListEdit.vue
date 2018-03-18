<template>
  <div class="row">
    <div class="col-md-12">
      <div class="pull-right" style="margin-bottom: 10px;">
        <button type="button" class="btn btn-primary" @click="purchaseTypeConfig.show = true">
          添加投标类别
        </button>
      </div>
    </div>

    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th style="width: 10px">#</th>
            <th>投标类别</th>
            <th>备注</th>
            <th>审核人</th>
            <th>审核状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(purchaseType, index) of purchaseTypeList">
            <td>{{ index + 1}}</td>
            <td>{{ purchaseType.basePurchaseType.name }}</td>
            <td>
              <LongText :text="purchaseType.remark"></LongText>
            </td>
            <td>{{ purchaseType.userInfoEO == null ? '' : purchaseType.userInfoEO.name }}</td>
            <td>
              <template v-if="purchaseType.status != 3">
                <span v-if="purchaseType.status == 1" class="label label-primary">{{ purchaseType.statusCN }}</span>
                <span v-if="purchaseType.status == 2" class="label label-success">{{ purchaseType.statusCN }}</span>
              </template>
              <template v-if="purchaseType.status == 3">
                <PopoverContainer title="驳回原因">
                  <span slot="outer" class="label label-warning">{{ purchaseType.statusCN }}</span>
                  <div class="row">
                    <div class="col-md-12">
                      <p class="text-red">{{ purchaseType.auditRemark }}</p>
                    </div>
                  </div>
                </PopoverContainer>
              </template>
            </td>
            <td>
              <a @click="deletePurchaseType(index)" v-if="purchaseType.status != 2">删除</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <AddPurchaseTypeModal :config="purchaseTypeConfig" @ok="handlerOk(arguments[0])" :value="purchaseType"></AddPurchaseTypeModal>
  </div>
</template>

<script>
import PurchaseTypeListEditMixin from './PurchaseTypeListEditMixin'

export default {
  mixins: [PurchaseTypeListEditMixin]
}
</script>
