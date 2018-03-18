<template>
  <div class="box box-primary">
    <div class="box-header">
      <h3 class="box-title">审核</h3>
    </div>
    <div class="box-body">
      <div class="row">
        <div class="col-md-12">
          <AuditLog v-if="taskInfo.variables" :businessType="businessType" :businessId="taskInfo.variables.businessId" :secondBusinessId="secondBusinessId"></AuditLog>
        </div>
      </div>

      <form class="form-horizontal">
        <div class="row">
          <div class="col-md-8">
            <FormGroup label="是否审核通过">
              <div>
                <div class="radio-inline">
                  <label><input type="radio" :value="true" v-model="auditResult">通过</label>
                </div>
                <div class="radio-inline">
                  <label><input type="radio" :value="false" v-model="auditResult">驳回</label>
                </div>
              </div>
            </FormGroup>
          </div>
          <div class="col-md-8">
            <FormGroup label="审核意见">
              <textarea class="form-control" rows="3" v-model="auditRemark" maxlength="200"></textarea>
            </FormGroup>
          </div>
        </div>
      </form>
    </div>
    <div class="box-footer">
      <div class="pull-right">
        <a class="btn btn-primary" @click="audit()">确认</a>
        <a class="btn btn-default" @click="$router.go(-1)">返回</a>
      </div>
    </div>
  </div>
</template>

<script>
import AuditLog from './AuditLog.vue'

export default {
  components: {
    AuditLog
  },
  props: {
    taskInfo: {
      type: Object
    }
  },
  data: function () {
    return {
      auditResult: true,
      auditRemark: ''
    }
  },
  computed: {
    userInfo: function () {
      return this.$store.state.system.userInfo
    },
    userId: function () {
      // TODO 将userId缓存token
      let userId = ''
      if (this.userInfo) {
        userId = this.userInfo.userId
      }
      return userId
    },
    businessType: function () {
      let processDefinitionId = this.taskInfo.processDefinitionId
      return processDefinitionId.substr(0, processDefinitionId.indexOf(':'))
    },
    secondBusinessId: function () {
      if (this.taskInfo.formKey === 'BiddingProjectSupplierApplyView') {
        return this.taskInfo.variables.supplierId
      } else {
        return ''
      }
    }
  },
  methods: {
    audit () {
      if (!this.auditResult && this.auditRemark === '') {
        this.$notify.warn('请输入驳回理由')
        return
      }

      let auditText = '确认审核通过吗？'
      if (!this.auditResult) {
        auditText = '确认驳回申请吗？'
      }

      this.$confirm(auditText, () => {
        let data = {
          taskId: this.taskInfo.taskId,
          auditUserId: this.userId,
          auditResult: this.auditResult,
          remark: this.auditRemark
        }
        this.$api.system.audit(data).then((response) => {
          let result = response.data
          if (result.ok) {
            this.$notify.success('审核成功')
            this.$router.go(-1)
          }
        })
      })
    }
  }
}
</script>
