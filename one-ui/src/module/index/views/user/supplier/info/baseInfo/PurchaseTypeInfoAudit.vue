<template>
  <form class="form-horizontal">
    <div class="row">
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
                <template v-if="purchaseType.status == 1 && purchaseType.canAudit">
                  <a @click="pass(purchaseType)">通过</a>
                  <a @click="reject(purchaseType)">驳回</a>
                </template>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <AuditReject :config="rejectConfig" :callback="rejectCallback"/>
  </form>
</template>

<script>
import AuditReject from '../../../../personal/task/AuditReject'

export default {
  components: {
    AuditReject
  },
  props: {
    obj: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      purchaseTypeList: [],
      rejectConfig: {
        title: '供应商审核',
        show: false
      },
      rejectCallback: Function
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.user.listSupplierPurchaseType(this.obj.supplierId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.purchaseTypeList = result.data
        }
      })
    },
    pass (purchaseType) {
      this.$confirm('确认审核通过吗？', () => {
        this.$api.user.auditSupplierPurchaseType(this.obj.supplierId, purchaseType.purchaseTypeId, true, null).then((response) => {
          this.$notify.success('审核成功！')
          this.loadData()
        })
      })
    },
    reject (purchaseType) {
      this.rejectConfig.show = true
      this.rejectCallback = (remark) => {
        this.$api.user.auditSupplierPurchaseType(this.obj.supplierId, purchaseType.purchaseTypeId, false, remark).then((response) => {
          this.$notify.success('驳回成功')
          this.loadData()
        })
      }
    }
  }
}
</script>
