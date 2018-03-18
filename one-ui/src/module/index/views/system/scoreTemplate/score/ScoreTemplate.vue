<template>
  <div>
    <div class="row"  v-for="section of template.sectionList">
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

      <div class="col-md-12">
        <div class="row score-question" v-for="question of section.questionList">
          <div class="col-md-12">
            <div class="score-question-title">
              <span class="score-question-title-index">{{ section.orderIndex }}.{{ question.orderIndex }}</span>
              {{ question.title }}
              <span class="score-question-title-score">({{ question.score }}分)</span>

              <span class="score-question-valid">
                <span class="text-green" v-if="question.answer && question.answer.isAnswered"><i class="fa fa-fw fa-check"></i></span>
                <span class="has-error"  v-if="errors.has(question.questionId)">{{ errors.first(question.questionId) }}</span>
              </span>
            </div>
          </div>
          <div class="col-md-12">
            <p class="text-muted score-question-remark">{{ question.remark }}</p>
          </div>
          <div class="col-md-12">
            <ScoreTemplateOption :question="question" v-model="question.answer" @answered="answered(question)"></ScoreTemplateOption>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ScoreTemplateOption from './ScoreTemplateOption.vue'

export default {
  components: {
    ScoreTemplateOption
  },
  props: {
    templateId: {
      type: String,
      required: true
    }
  },
  computed: {
    questionList: function () {
      let questionList = []
      if (this.template.sectionList) {
        for (let section of this.template.sectionList) {
          for (let question of section.questionList) {
            questionList.push(question)
          }
        }
      }
      return questionList
    },
    questionCount: function () {
      let count = 0
      if (this.template.sectionList) {
        for (let section of this.template.sectionList) {
          count += section.questionList.length
        }
      }
      return count
    },
    answerCount: function () {
      let count = 0
      if (this.template.sectionList) {
        for (let section of this.template.sectionList) {
          for (let question of section.questionList) {
            if (question.answer && question.answer.isAnswered) {
              count++
            }
          }
        }
      }
      return count
    },
    totalScore: function () {
      let totalScore = 0
      if (this.template.sectionList) {
        for (let section of this.template.sectionList) {
          for (let question of section.questionList) {
            if (question.answer && question.answer.isAnswered) {
              totalScore += parseInt(question.answer.score)
            }
          }
        }
      }
      return totalScore
    }
  },
  data: function () {
    return {
      template: {}
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.getBaseScoreTemplate(this.templateId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.template = result.data
          this.$emit('ok')
        }
      })
    },
    valid () {
      for (let question of this.questionList) {
        if (!(question.answer != null && question.answer.isAnswered)) {
          this.errors.add(question.questionId + '', '这道题必须回答哦', 'error')
        }
      }
      if (this.errors.items.length > 0) {
        this.scrollToError()
      }
      return this.errors.items.length === 0
    },
    answered (question) {
      this.errors.remove(question.questionId + '')
    }
  },
  watch: {
    'template': {
      handler: function () {
        this.$emit('answer', this.questionCount, this.answerCount, this.totalScore, this.template)
      },
      deep: true
    }
  }
}
</script>
