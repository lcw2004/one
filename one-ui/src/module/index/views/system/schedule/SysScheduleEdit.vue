<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">任务调度信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="任务调度ID" >
                <input type="text" class="form-control" v-model="obj.scheduleId" v-validate="'required'" name="任务调度ID" />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="任务名称" >
                <input type="text" class="form-control" v-model="obj.name"  />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="Cron表达式" >
                <input type="text" class="form-control" v-model="obj.expression"  />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="调用方式" >
                <input type="text" class="form-control" v-model="obj.type"  />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="调用目标" >
                <input type="text" class="form-control" v-model="obj.service"  />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="状态" >
                <input type="text" class="form-control" v-model="obj.status"  />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="任务描述" >
                <input type="text" class="form-control" v-model="obj.remark"  />
              </FormGroup>
            </div>
          </div>
        </form>
      </div>
      <div class="box-footer">
        <div class="row">
          <div class="col-md-2 col-md-offset-2">
            <a class="btn btn-block btn-primary" @click="validAndSave()">保存</a>
          </div>
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
  },
  mounted () {
  },
  methods: {
  }
}
</script>
