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
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(purchaseType, index) of purchaseTypeList">
            <td>{{ index + 1 }}</td>
            <td>{{ purchaseType.basePurchaseType.name }}</td>
            <td>
              <LongText :text="purchaseType.remark"></LongText>
            </td>
            <td>
              <a @click="deletePurchaseType(index)">删除</a>
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
