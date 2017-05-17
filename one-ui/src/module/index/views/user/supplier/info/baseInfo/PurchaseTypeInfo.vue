<template>
  <form class="form-horizontal">
    <div class="row">
      <div class="col-md-12">
        <table class="table table-bordered table-hover">
          <tbody>
            <tr>
              <th style="width: 10px">#</th>
              <th>投标类别</th>
              <th>审核人</th>
              <th>审核状态</th>
              <th>驳回理由</th>
            </tr>
            <tr v-for="(purchaseType, index) of obj.purchaseTypeList">
              <td>{{ index + 1}}</td>
              <td>{{ purchaseType.basePurchaseType.name }}</td>
              <td>{{ purchaseType.userInfoEO == null ? '' : purchaseType.userInfoEO.name }}</td>
              <td>
                <span v-if="purchaseType.status == 1" class="label label-primary">{{ purchaseType.statusCN }}</span>
                <span v-if="purchaseType.status == 2" class="label label-success">{{ purchaseType.statusCN }}</span>
                <span v-if="purchaseType.status == 3" class="label label-warning">{{ purchaseType.statusCN }}</span>
              </td>
              <td>{{ purchaseType.remark }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </form>
</template>

<script>
export default {
  props: {
    obj: {
      type: Object,
      required: true
    }
  }
}
</script>
