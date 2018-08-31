<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">参数信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="参数名称" :required="true">
                <input type="text" class="form-control" v-model="obj.configName" v-validate="'required'" name="参数名称" />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="参数键名" :required="true">
                <input type="text" class="form-control" v-model="obj.configKey" v-validate="'required'" name="参数键名" readonly/>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="参数值" :required="true">
                <input type="text" class="form-control" v-model="obj.configValue" v-validate="'required'" name="参数值" />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="参数描述" :required="true">
                <textarea class="form-control" rows="3" v-model="obj.remark" maxlength="200" v-validate="'required'" name="参数描述"></textarea>
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
          get: this.$api.system.getConfig,
          save: this.$api.system.saveConfig,
          update: this.$api.system.updateConfig
        },
        continue: true
      },
      obj: {
        configName: '',
        configKey: '',
        configValue: '',
        remark: ''
      }
    }
  },
  methods: {
    clear () {
      this.obj.label = ''
      this.obj.value = ''
      this.obj.sort = Number.parseInt(this.obj.sort) + 1
    }
  }
}
</script>
