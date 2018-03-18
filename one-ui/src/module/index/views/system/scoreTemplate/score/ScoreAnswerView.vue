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

              <span class="score-question-score">
                得分： {{ question.answer.score }}分
              </span>
            </div>
          </div>
          <div class="col-md-12">
            <p class="text-muted score-question-remark">{{ question.remark }}</p>
          </div>
          <div class="col-md-12">
            <ScoreTemplateOptionView :question="question"></ScoreTemplateOptionView>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ScoreTemplateOptionView from './ScoreTemplateOptionView.vue'

export default {
  components: {
    ScoreTemplateOptionView
  },
  props: {
    scoreId: {
      type: String,
      required: true
    }
  },
  computed: {
    totalScore: function () {
      let totalScore = 0
      if (this.template.sectionList) {
        for (let section of this.template.sectionList) {
          for (let question of section.questionList) {
            totalScore += parseInt(question.answer.score)
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
      this.$api.system.getAnswerByScoreId(this.scoreId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.template = result.data
          this.$emit('totalScore', this.totalScore)
        }
      })
    }
  }
}
</script>
