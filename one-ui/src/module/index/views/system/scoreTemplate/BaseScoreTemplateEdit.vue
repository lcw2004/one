<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">评分模板信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <!-- 评分模板信息 -->
          <div class="row">
            <div class="col-md-4">
              <FormGroup label="模板名称" :required="true">
                <input type="text" class="form-control" v-model="obj.name" v-validate="'required'" name="模板名称"/>
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="模板类型" :required="true">
                <DictSelect v-model="obj.type" type="base_score_template_type" v-validate="'required'" data-vv-value-path="innerValue" data-vv-name="模板类型"></DictSelect>
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="总分值" :required="true">
                <input type="number" class="form-control" v-model="obj.score" v-number v-validate="'required'" name="总分值"/>
              </FormGroup>
            </div>
          </div>

          <BaseScoreTemplateSection v-for="(section, index) of obj.sectionList" :section="section" :key="section.sectionId" @remove="removeSection(index)"></BaseScoreTemplateSection>

          <div class="row">
            <div class="col-md-12">
              <FullButton @click="addSection()"> + 添加章节 </FullButton>
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
            <a class="btn btn-block btn-default" @click="preview()">预览</a>
          </div>
          <div class="col-md-2">
            <a class="btn btn-block btn-default" @click="$router.go(-1)">返回</a>
          </div>
        </div>
      </div>
    </div>

    <BaseScoreTemplatePreviewModal :config="config" :template="obj"></BaseScoreTemplatePreviewModal>
  </section>
</template>

<script>
import FormMixin from '@mixins/FormMixin'
import BaseScoreTemplateSection from './BaseScoreTemplateSection.vue'
import BaseScoreTemplatePreviewModal from './BaseScoreTemplatePreviewModal.vue'

export default {
  mixins: [FormMixin],
  components: {
    BaseScoreTemplateSection,
    BaseScoreTemplatePreviewModal
  },
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getBaseScoreTemplate,
          save: this.$api.system.saveBaseScoreTemplate,
          update: this.$api.system.updateBaseScoreTemplate
        },
        continue: false
      },
      obj: {
        templateId: '',
        name: '',
        type: null,
        score: 100,
        sectionList: []
      },
      config: {
        show: false,
        title: '评分模板预览'
      }
    }
  },
  methods: {
    preview () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          if (this.validTemplate()) {
            this.config.show = true
          }
        }
      })
    },
    removeSection (index) {
      this.obj.sectionList.splice(index, 1)
    },
    orderSection () {
      if (this.obj.sectionList) {
        let index = 1
        for (let section of this.obj.sectionList) {
          section.orderIndex = index++
        }
      }
    },
    addSection () {
      this.obj.sectionList.push({
        isEdit: true,
        templateId: '',
        name: null,
        remark: null,
        score: null,
        questionList: [],
        orderIndex: this.obj.sectionList.length + 1
      })
    },
    validAndSave () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          if (this.validTemplate()) {
            this.save()
          }
        }
      })
    },
    validTemplate () {
      if (this.obj.sectionList.length === 0) {
        this.$notify.warn('请添加至少一个章节')
        return false
      }
      let totalScore = 0
      for (let section of this.obj.sectionList) {
        totalScore += section.score
      }
      if (totalScore !== this.obj.score) {
        console.log('totalScore:' + totalScore)
        console.log('obj.score:' + this.obj.score)
        console.log(this.obj.sectionList)
        this.$notify.warn('章节总分之和必须等于模板总分值')
        return false
      }
      return true
    }
  }
}
</script>
