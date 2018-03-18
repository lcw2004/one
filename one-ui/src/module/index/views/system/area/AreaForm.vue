<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">区域信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="上级区域">
                <div class="input-group">
                  <input type="text" class="form-control" v-model="obj.parent.name" v-validate="'required'" name="上级区域"/>
                  <span class="input-group-btn">
                    <button class="btn btn-info" type="button" @click="areaTreeModalConfig.show = true">选择</button>
                  </span>
                </div>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="区域名称">
                <input type="text" class="form-control" v-model="obj.name" v-validate="'required'" name="区域名称"/>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="区域编码">
                <input type="text" class="form-control" v-model="obj.code" v-validate="'required'" name="区域编码"/>
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

    <SelectAreaModal :config="areaTreeModalConfig" v-model="obj.parent"/>
  </section>
</template>

<script>
import FormMixin from '@mixins/FormMixin'
import SelectAreaModal from '../modal/SelectAreaModal'

export default {
  mixins: [FormMixin],
  components: {
    SelectAreaModal
  },
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getArea,
          save: this.$api.system.saveArea,
          update: this.$api.system.updateArea
        },
        continue: false
      },
      obj: {
        parent: {},
        area: {},
        name: '',
        code: '',
        type: 1,
        grade: 1,
        address: '',
        zipCode: '',
        master: '',
        phone: '',
        fax: '',
        email: '',
        remarks: ''
      },
      officeTreeModalConfig: {
        show: false,
        title: '选择上级机构'
      },
      areaTreeModalConfig: {
        show: false,
        title: '选择归属区域'
      }
    }
  }
}
</script>
