<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">审批信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-12">
              <FormGroupStatic label="申请人" :width="2">{{ applyUserName }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <FormGroupStatic label="申请时间" :width="2">{{ taskCreateTime }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <FormGroupStatic label="审批结果" :width="2">
                <span class="label label-primary" v-if="auditResult">通过</span>
                <span class="label label-warning" v-if="!auditResult">驳回</span>
              </FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <FormGroupStatic label="审核意见" :width="2">{{ auditRemark }}</FormGroupStatic>
            </div>
          </div>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
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
    taskCreateTime: function () {
      return this.taskInfo ? this.taskInfo.taskCreateTime : null
    },
    applyUserName: function () {
      return this.variables ? this.variables.applyUserName : null
    },
    auditResult: function () {
      return this.variables ? this.variables.auditResult : null
    },
    auditRemark: function () {
      return this.variables ? this.variables.auditRemark : null
    }
  }
}
</script>
