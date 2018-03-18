<template>
  <div>
    <template v-for="section of template.sectionList">
      <div class="row" style="position: relative">
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
      </div>

      <div class="row" v-for="(question, index) of section.questionList">
        <div class="col-md-12">
          <div class="score-question">
            <div style="position: relative">
              <div class="row">
                <div class="col-md-12">
                  <div class="score-question-title">
                    <span class="score-question-title-index">{{ section.orderIndex }}.{{ question.orderIndex }}</span>
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
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <hr class="divider">
        </div>
      </div>
    </template>

  </div>
</template>

<script>
import BaseScoreTemplateQuestionOption from './BaseScoreTemplateQuestionOption'

export default {
  components: {
    BaseScoreTemplateQuestionOption
  },
  props: {
    template: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
    }
  }
}
</script>
