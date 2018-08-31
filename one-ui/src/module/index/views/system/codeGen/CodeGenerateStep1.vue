<template>
  <div>
    <div class="box-body">
      <div class="row">
        <div class="col-md-12">
          <FormGroup label="需要生成代码的表" width="2">
            <div class="row">
              <div class="col-md-12">
                <button type="button" class="btn btn-primary" @click="config.show = true">
                  选择需要生成的表
                </button>
              </div>
            </div>
            <div class="row" style="margin-top: 0px; line-height: 25px;">
              <div class="col-md-12">
                <template v-for="(table, index) of strategy.tableList">
                  <span class="label label-primary">{{ table }}</span>
                  <template v-if="index < strategy.tableList.length - 1">,</template>
                </template>
              </div>
            </div>
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="模块">
              <div>
                <div class="radio-inline">
                  <label><input type="radio" value="1" v-model="strategy.moduleType">生成到现有模块</label>
                </div>
                <div class="radio-inline">
                  <label><input type="radio" value="2" v-model="strategy.moduleType">生成到新模块</label>
                </div>
              </div>
            </FormGroup>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <FormGroup label="是否覆盖">
              <div class="pull-left" style="margin-top: 6px;">
                <OneSwitch v-model="strategy.isOverWrite" activeText="是" inactiveText="否"></OneSwitch>
              </div>
            </FormGroup>
          </div>
        </div>

        <template v-if="strategy.moduleType == 1">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="模块名称">
                <select class="form-control" v-model="module" v-validate="'required'" name="模块名称">
                  <option v-for="module of moduleList" :value="module">{{ module.moduleName }}</option>
                </select>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="模块简称">
                <input type="text" class="form-control" v-model="strategy.moduleSimpleName" maxlength="100" v-validate="'required'" name="包名称">
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="包名称">
                <input type="text" class="form-control" v-model="strategy.modulePackage" maxlength="100" v-validate="'required'" name="包名称">
              </FormGroup>
            </div>
          </div>
        </template>
        <template v-if="strategy.moduleType == 2">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="模块名称">
                <input type="text" class="form-control" v-model="strategy.moduleName" maxlength="100" v-validate="'required'" name="模块名称">
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="模块简称">
                <input type="text" class="form-control" v-model="strategy.moduleSimpleName" maxlength="100" v-validate="'required'" name="包名称">
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="包名称">
                <input type="text" class="form-control" v-model="strategy.modulePackage" maxlength="100" v-validate="'required'" name="包名称">
              </FormGroup>
            </div>
          </div>
        </template>

        <div class="row">
          <div class="col-md-6">
            <FormGroup label="Entity前缀">
              <input type="text" class="form-control" v-model="strategy.entityPrefix" maxlength="100">
            </FormGroup>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <FormGroup label="Entity后缀">
              <input type="text" class="form-control" v-model="strategy.entitySuffix" maxlength="100">
            </FormGroup>
          </div>
        </div>
      </div>

      <div class="box-footer">
        <div class="col-md-6 col-sm-0"></div>
        <div class="col-md-2 col-sm-2">
          <button type="button" class="btn btn-block btn-primary" @click="nextStep()">下一步</button>
        </div>
      </div>

      <SelectTableModal :config="config" v-model="strategy.tableList"></SelectTableModal>
  </div>
</template>

<script>
import SelectTableModal from './modal/SelectTableModal.vue'

export default {
  components: {
    SelectTableModal
  },
  data: function () {
    return {
      moduleList: [],
      config: {
        show: false,
        title: '选择需要生成代码的表'
      },
      strategy: {
        moduleType: 1,
        isOverWrite: 1,
        moduleName: 'one-crm',
        moduleSimpleName: 'crm',
        modulePackage: 'com.lcw.one.crm',
        entityPrefix: '',
        entitySuffix: 'EO',
        tableList: ['crm_customer']
      },
      module: {}
    }
  },
  mounted () {
    this.loadModuleList()
    this.attachRule()
  },
  methods: {
    loadModuleList () {
      this.$api.system.listModule().then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.moduleList = result.data
        }
      })
    },
    attachRule () {
      this.$validator.attach({ name: '需要生成代码的表', rules: 'required' })
      this.$validator.attach({ name: '模块名称', rules: 'required' })
      this.$validator.attach({ name: '包名称', rules: 'required' })
    },
    validate () {
      return this.$validator.validateAll({
        '需要生成代码的表': this.strategy.tableList,
        '模块名称': this.strategy.moduleName,
        '包名称': this.strategy.modulePackage
      })
    },
    nextStep () {
      this.validate().then((result) => {
        if (result) {
          this.$emit('next')
          this.$emit('input', this.strategy)
        }
      })
    }
  },
  watch: {
    strategy: {
      handler: function () {
        this.$emit('input', this.strategy)
        this.validate()
      },
      deep: true,
      immediate: true
    },
    'module': {
      handler: function () {
        let packageName = this.module.modulePackage
        this.strategy.moduleName = this.module.moduleName
        this.strategy.modulePackage = this.module.modulePackage
        this.strategy.moduleSimpleName = packageName.substring(packageName.lastIndexOf('.') + 1)
      },
      deep: true
    }
  }
}
</script>
