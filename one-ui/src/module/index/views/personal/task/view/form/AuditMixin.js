export default {
  props: {
    taskInfo: {
      type: Object
    }
  },
  computed: {
    variables: function () {
      return this.taskInfo ? this.taskInfo.variables : null
    },
    userInfo: function () {
      return this.$store.state.system.userInfo
    },
    businessType: function () {
      let processDefinitionId = this.taskInfo.processDefinitionId
      if (processDefinitionId) {
        return processDefinitionId.substr(0, processDefinitionId.indexOf(':'))
      } else {
        return null
      }
    },
    businessId: function () {
      return this.variables ? this.variables.businessId : null
    },
    auditItemId: function () {
      return this.variables ? this.variables.auditItemId : null
    },
    secondBusinessId: function () {
      if (this.taskInfo.formKey === 'BiddingProjectSupplierApplyView') {
        return this.taskInfo.variables.supplierId
      } else {
        return ''
      }
    },
    progressImageUrl: function () {
      return '/api/flow/progressImage/' + this.taskInfo.taskId
    },
    auditParam: function () {
      return {
        taskId: this.taskInfo.taskId,
        auditResult: this.auditResult,
        remark: this.auditRemark
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
        this.$overlay.start('审核中')
        this.$api.system.audit(this.auditParam).then((response) => {
          this.$overlay.done()
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
