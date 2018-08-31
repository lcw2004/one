<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">任务调度详情</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="任务调度ID">{{ obj.scheduleId }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="任务名称">{{ obj.name }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="Cron表达式">{{ obj.expression }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="调用方式">{{ obj.type }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="调用目标">{{ obj.service }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="状态">{{ obj.status }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="任务描述">{{ obj.remark }}</FormGroupStatic>
            </div>
          </div>
        </form>
      </div>
      <div class="box-footer">
        <div class="row">
          <div class="col-md-2">
            <a class="btn btn-block btn-default" @click="$router.go(-1)">返回</a>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import FormMixin from '@mixins/FormMixin'

export default {
  mixins: [FormMixin],
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getSysSchedule,
          save: this.$api.system.saveSysSchedule,
          update: this.$api.system.updateSysSchedule
        }
      },
      obj: {
        scheduleId: '',
        name: '',
        expression: '',
        type: 0,
        service: '',
        status: 0,
        remark: ''
      }
    }
  }
}
</script>
