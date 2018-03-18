<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title"></h3>
      </div>
      <div class="box-body">
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="模板类别">
              <select class="form-control" v-model="selectCode">
                <option v-for="(obj, index) of templateTypeList" :value="obj.code">{{ obj.name }}</option>
              </select>
            </FormGroup>
          </div>
          <div class="col-md-6" v-if="selectCode">
            <div class="pull-right">
              <button type="button" class="btn btn-primary" @click="addTemplate()">
                添加模板
              </button>
            </div>
          </div>
        </div>
  
        <div class="row">
          <div class="col-md-12">
            <hr class="divider">
          </div>
        </div>
  
        <div class="row" v-if="selectCode">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>模板名称</th>
                  <th>是否是默认模板</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(obj, index) of templateList">
                  <td>{{ index + 1 }}</td>
                  <td>{{ obj.name }} </td>
                  <td>
                    <template v-if="obj.isDefault == 1">
                      <span class="label label-success">是</span>
                    </template>
                    <template v-if="obj.isDefault != 1">
                      <span class="label label-default">否</span>
                    </template>
                  </td>
                  <td>
                    <a @click="deleteTemplate(obj.templateId)">删除</a>
                    <a @click="updateTemplate(obj)">编辑</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <TableNoData :data="templateList">
          暂无数据
        </TableNoData>
      </div>
      <div class="box-footer">
  
      </div>
    </div>
  
    <TemplateFormModal :config="updateTemplateConfig" :template="template" @ok="loadTemplate(selectCode)"></TemplateFormModal>
  </section>
</template>

<script>
import TemplateFormModal from './TemplateFormModal.vue'

export default {
  components: {
    TemplateFormModal
  },
  data: function () {
    return {
      templateTypeList: [],
      templateList: [],
      selectCode: '',
      template: {},
      updateTemplateConfig: {
        title: '修改模板',
        show: false
      }
    }
  },
  mounted () {
    this.loadTemplateType()
  },
  methods: {
    loadTemplateType () {
      this.$api.system.listTemplateType().then((response) => {
        let result = response.data
        if (result.ok) {
          this.templateTypeList = result.data
        }
      })
    },
    loadTemplate (code) {
      this.$api.system.listTemplateByCode(code).then((response) => {
        let result = response.data
        if (result.ok) {
          this.templateList = result.data
        }
      })
    },
    deleteTemplate (templateId) {
      this.$confirm('确认删除模板吗？', () => {
        this.$api.system.deleteTemplate(templateId).then((response) => {
          let result = response.data
          if (result.ok) {
            this.$notify.success('删除成功')
            this.loadTemplate(this.selectCode)
          }
        })
      })
    },
    updateTemplate (template) {
      this.template = template
      this.updateTemplateConfig.show = true
    },
    addTemplate () {
      this.template = {
        templateId: '',
        content: '',
        name: '',
        isDefault: 0,
        code: this.selectCode
      }
      this.updateTemplateConfig.show = true
    }
  },
  watch: {
    'selectCode': function () {
      if (this.selectCode) {
        this.loadTemplate(this.selectCode)
      }
    }
  }
}
</script>
