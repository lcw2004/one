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
              <th>操作</th>
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

    <AuditReject :config="config" />
  </form>
</template>

<script>
import AuditReject from '../../../../modal/AuditReject'

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
      config: {
        title: '供应商审核',
        show: false,
        callback: Function
      },
      actions: {
        audit: { method: 'post', url: '/api/user/supplier/audit{/supplierId}{/purchaseTypeId}?auditResult={auditResult}&remark={remark}' }
      }
    }
  },
  mounted () {
    this.resource = this.$resource(null, {}, this.actions)
  },
  methods: {
    pass (purchaseType) {
      let data = {
        supplierId: this.obj.supplierId,
        purchaseTypeId: purchaseType.purchaseTypeId,
        auditResult: true
      }
      this.$confirm('确认审核通过吗？', () => {
        this.resource.audit(data, null).then(function (response) {
          this.$notify.info('审核成功')
          this.$emit('reload')
        })
      })
    },
    reject (purchaseType) {
      this.config.show = true
      let self = this
      this.config.callback = function (remark) {
        let data = {
          supplierId: self.obj.supplierId,
          purchaseTypeId: purchaseType.purchaseTypeId,
          auditResult: false,
          remark: remark
        }
        self.resource.audit(data, null).then(function (response) {
          self.$notify.info('审核成功')
          self.$emit('reload')
        })
      }
    }
  }
}
</script>
