<template>
  <div class="score-section">
    <!-- 查看状态 -->
    <div class="row" style="position: relative" @mouseover="showTool = true" @mouseout="showTool = false" v-show="!section.isEdit">
      <div class="col-md-12">
        <div class="score-section-title">
          <span class="score-section-title-index">{{ section.orderIndex }}.</span>
          {{ section.name }}
          <span class="score-section-title-score">({{ section.score }}分)</span>
        </div>
      </div>
      <div class="col-md-12" v-if="section.remark">
        <div class="callout callout-default">
          <p>{{ section.remark }}</p>
        </div>
      </div>
      <div class="score-section-tools" v-show="showTool">
        <div class="btn-group-vertical">
          <button type="button" class="btn btn-primary btn-flat btn-xs" @click="edit()"><i class="fa fa-edit"></i></button>
          <button type="button" class="btn btn-primary btn-flat btn-xs" @click="remove()"><i class="fa fa-trash-o"></i></button>
        </div>
      </div>
    </div>

    <!-- 编辑状态 -->
    <div class="row" style="position: relative" v-show="section.isEdit">
      <div class="col-md-6">
        <FormGroup label="章节名称" :required="true">
          <input type="text" class="form-control" v-model="section.name" placeholder="请输入章节名称" v-validate="'required'" name="章节名称"/>
        </FormGroup>
      </div>
      <div class="col-md-6">
        <FormGroup label="章节分值" inputWidth="6" :required="true">
          <input type="number" class="form-control" v-model.number="section.score" placeholder="请输入章节分值"  v-validate="'required'" name="章节分值" v-number/>
        </FormGroup>
      </div>
      <div class="col-md-12">
        <FormGroup label="章节描述" width="2">
          <textarea class="form-control" rows="2" v-model="section.remark" maxlength="200" placeholder="请输入章节描述"></textarea>
        </FormGroup>
      </div>

      <div class="score-section-tools">
        <div class="btn-group-vertical">
          <button type="button" class="btn btn-primary btn-flat btn-xs" @click="save()"><i class="fa fa-save"></i></button>
          <button type="button" class="btn btn-primary btn-flat btn-xs" @click="remove()"><i class="fa fa-trash-o"></i></button>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-12">
        <BaseScoreTemplateQuestion v-for="(question, index) of section.questionList"
          :key="question.questionId"
          :question="question"
          :sectionIndex="section.orderIndex"
          @remove="removeQuestion(index)"></BaseScoreTemplateQuestion>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <FullButton @click="addQuestion()"> + 添加问题 </FullButton>
      </div>
    </div>
  </div>
</template>

<script>
import BaseScoreTemplateQuestion from './BaseScoreTemplateQuestion.vue'

export default {
  components: {
    BaseScoreTemplateQuestion
  },
  props: {
    section: {
      type: Object,
      require: true
    }
  },
  data: function () {
    return {
      showTool: false
    }
  },
  methods: {
    edit () {
      this.$set(this.section, 'isEdit', true)
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.$set(this.section, 'isEdit', false)
        }
      })
    },
    remove () {
      this.$emit('remove')
    },
    removeQuestion (index) {
      this.section.questionList.splice(index, 1)
      this.orderQuestion()
    },
    orderQuestion () {
      if (this.section.questionList) {
        let index = 1
        for (let question of this.section.questionList) {
          question.orderIndex = index++
        }
      }
    },
    addQuestion () {
      this.section.questionList.push({
        sectionId: this.section.sectionId,
        orderIndex: this.section.questionList.length + 1,
        name: null,
        score: null,
        type: 1,
        remark: '',
        optionList: [],
        isEdit: true
      })
    }
  }
}
</script>

<style lang="less">
.score-section {
  position: relative;
  border: 1px solid #c0ccda;
  border-radius: 2px;
  box-sizing: border-box;
  padding: 20px;
  margin-bottom: 10px;
  margin-left: 0px;

  .score-section-tools {
    position: absolute;
    top: 0;
    right: 0;

    i {
      font-size: 15px;
    }
  }
}
.score-section-title {
  font-size: 20px;
  margin: 10px 0;

  .score-section-title-index {
    margin-right: 10px;
  }
  .score-section-title-score {
    margin-left: 10px;
    font-size: 16px;
  }
}

</style>
