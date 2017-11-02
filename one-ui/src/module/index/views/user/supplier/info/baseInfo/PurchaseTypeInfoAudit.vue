<template>
  <form class="form-horizontal">
    <div class="row">
      <div class="col-md-12">
        <table class="table table-bordered table-hover">
          <tbody>
          <tr>
            <th style="width: 10px">#</th>
            <th>投标类别</th>
            <th>备注</th>
            <th>审核人</th>
            <th>审核状态</th>
            <th>操作</th>
          </tr>
          <tr v-for="(purchaseType, index) of obj.purchaseTypeList">
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
    data: () => {
      return {
        rejectConfig: {
          title: '供应商审核',
          show: false
        },
        rejectCallback: Function,
        actions: {
          audit: {method: 'post', url: '/api/user/supplier/audit{/supplierId}{/purchaseTypeId}?auditResult={auditResult}&remark={remark}'}
        }
      }
    },
    mounted () {
      this.resource = this.$resource(null, {}, this.actions)
    },
    methods: {
      pass (purchaseType) {
        this.$confirm('确认审核通过吗？', () => {
          let data = {
            supplierId: this.obj.supplierId,
            purchaseTypeId: purchaseType.purchaseTypeId,
            auditResult: true
          }
          this.$confirm('确认审核通过吗？', () => {
            this.resource.audit(data, null).then((response) => {
              this.$notify.success('审核成功！')
              this.$emit('reload')
            })
          })
        })
      },
      reject (purchaseType) {
        this.rejectConfig.show = true
        this.rejectCallback = (remark) => {
          let data = {
            supplierId: this.obj.supplierId,
            purchaseTypeId: purchaseType.purchaseTypeId,
            auditResult: false,
            remark: remark
          }
          this.resource.audit(data, null).then((response) => {
            this.$notify.success('驳回成功')
            this.$emit('reload')
          })
        }
      }
    }
  }
</script>
