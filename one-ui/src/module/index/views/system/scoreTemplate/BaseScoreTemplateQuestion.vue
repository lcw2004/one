<template>
  <div class="score-question" @mouseover="showTool = true" @mouseout="showTool = false">
    <div style="position: relative" v-show="!question.isEdit">
      <div class="row">
        <div class="col-md-12">
          <div class="score-question-title">
            <span class="score-question-title-index">{{ sectionIndex }}.{{ question.orderIndex }}</span>
            {{ question.title }}
            <span class="score-question-title-score">({{ question.score }}分)</span>
          </div>
        </div>
        <div class="col-md-12">
          <p class="text-muted score-question-remark">{{ question.remark }}</p>
        </div>
        <div class="col-md-12">
          <BaseScoreTemplateQuestionOption :question="question"></BaseScoreTemplateQuestionOption>
        </div>

        <div class="score-section-tools" v-show="showTool">
          <div class="btn-group-vertical">
            <button type="button" class="btn btn-primary btn-flat btn-xs" @click="edit()"><i class="fa fa-edit"></i></button>
            <button type="button" class="btn btn-primary btn-flat btn-xs" @click="remove()"><i class="fa fa-trash-o"></i></button>
          </div>
        </div>
      </div>
    </div>

    <div class="score-question-edit" style="position: relative" v-show="question.isEdit">
      <div class="row">
        <div class="col-md-6">
          <FormGroup label="问题标题">
            <input type="text" class="form-control" v-model="question.title" maxlength="100"  placeholder="请输入问题标题" v-validate="'required'" name="问题标题">
          </FormGroup>
        </div>
        <div class="col-md-6">
          <FormGroup label="问题分值" inputWidth="6">
            <input type="number" class="form-control" v-model.number="question.score" maxlength="100"  placeholder="请输入问题分值" v-validate="'required'" name="问题分值" v-number>
          </FormGroup>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <FormGroup label="问题描述" width="2">
            <textarea class="form-control" rows="2" v-model="question.remark" maxlength="200"  placeholder="请输入问题标题"></textarea>
          </FormGroup>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <FormGroup label="选项类型">
            <DictSelect v-model="question.type" type="base_score_template_question_type"></DictSelect>
          </FormGroup>
        </div>
      </div>
      <div class="row" v-if="question.type == 2 || question.type == 3">
        <div class="col-md-12">
          <FormGroup label="选项" width="2">
            <BaseScoreTemplateQuestionOptionEdit :question="question"></BaseScoreTemplateQuestionOptionEdit>
          </FormGroup>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10 col-md-offset-2">
          <div class="callout callout-default">
            <p v-if="question.type == 1">是（{{ question.score }}分）, 否（0分）</p>
            <p v-if="question.type == 2">所有选项的最大分值不能超过问题分值{{ question.score }}，且必须存在一个选项的分值是问题分值 {{ question.score }}</p>
            <p v-if="question.type == 3">所有选项之和必须等于问题分值{{ question.score }}</p>
            <p v-if="question.type == 4">可以在0到{{ question.score }}之间自由打分</p>
          </div>
        </div>
      </div>

      <div class="score-section-tools">
        <div class="btn-group-vertical">
          <button type="button" class="btn btn-primary btn-flat btn-xs" @click="save()"><i class="fa fa-save"></i></button>
          <button type="button" class="btn btn-primary btn-flat btn-xs" @click="remove()"><i class="fa fa-trash-o"></i></button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BaseScoreTemplateQuestionOption from './BaseScoreTemplateQuestionOption.vue'
import BaseScoreTemplateQuestionOptionEdit from './BaseScoreTemplateQuestionOptionEdit.vue'

export default {
  components: {
    BaseScoreTemplateQuestionOption,
    BaseScoreTemplateQuestionOptionEdit
  },
  props: {
    sectionIndex: {
      type: Number,
      require: true
    },
    question: {
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
      this.$set(this.question, 'isEdit', true)
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          if (this.validQuestionOption()) {
            this.$set(this.question, 'isEdit', false)
          }
        }
      })
    },
    remove () {
      this.$emit('remove')
    },
    validQuestionOption () {
      if (this.question.type === 2) {
        let maxScore = 0
        for (let option of this.question.optionList) {
          if (option.score > this.question.score) {
            this.$notify.warn(`选项[${option.name}]的分值大于该问题总分值`)
            return false
          }
          maxScore = maxScore < option.score ? option.score : maxScore
        }
        if (maxScore < this.question.score) {
          this.$notify.warn('单选选项必须存在一项分值等于该问题总分值的项')
          return false
        }
      } else if (this.question.type === 3) {
        let totalScore = 0
        for (let option of this.question.optionList) {
          totalScore += option.score
        }
        if (totalScore !== this.question.score) {
          this.$notify.warn(`该问题选项分值之和不等于该问题总分值`)
          return false
        }
      }
      return true
    }
  }
}
</script>


<style lang="less">
.score-question {
  margin-bottom: 10px;

  .score-question-edit {
    border: 1px solid #eee;
    padding-top: 10px;
    padding-right: 10px;
    background-color: #f9f9f9;
  }

  .score-question-title {
    font-size: 15px;
    margin: 10px 0;

    .score-question-title-index {
      margin-right: 10px;
    }
    .score-question-title-score {
      margin-left: 10px;
      font-size: 15px;
    }
    .score-question-valid {
      margin-left: 10px;

      .has-error {
        background: red;
        border-radius: 3px;
        font-size: 10px;
        color: #ffffff;
        padding: 1px 4px;
      }
    }

    .score-question-score {
      margin-left: 10px;
      background: #00c0ef;
      border-radius: 3px;
      font-size: 10px;
      color: #ffffff;
      padding: 1px 4px;
    }
  }

  .score-question-remark {
    margin-left: 41px;
  }

  .score-question-option {
    margin-left: 41px;
  }
}

.margin-bottom-5 {
  margin-bottom: 5px
}
</style>
