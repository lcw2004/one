<template>
  <div class="score-question-option">
    <template v-if="question.type == 1">
      <div>
        <div class="radio">
          <label><input type="radio" :value="1" v-model="answer.value" @click="setScore(question.score)">是 ({{ question.score }}分)</label>
        </div>
        <div class="radio">
          <label><input type="radio" :value="0" v-model="answer.value" @click="setScore(0)">否 (0分)</label>
        </div>
      </div>
    </template>

    <template v-if="question.type == 2">
      <div>
        <div class="radio" v-for="option of question.optionList">
          <label><input type="radio" :value="option.name" v-model="answer.value" @click="setScore(option.score)">{{ option.name }} ({{ option.score }}分) </label>
        </div>
      </div>
    </template>

    <template v-if="question.type == 3">
      <div>
        <div class="checkbox" v-for="option of question.optionList">
          <label>
            <input type="checkbox" :value="option" v-model="valueList" @click="isAnswered()"> {{ option.name }} ({{ option.score }}分)
          </label>
        </div>
      </div>
    </template>

    <template v-if="question.type == 4">
      <div>
        <el-input-number size="small" :min="0" :max="question.score" label="打分" v-model="answer.score"></el-input-number>
        <textarea class="form-control" rows="2" v-model="answer.value" maxlength="200" placeholder="请输入理由" style="margin-top: 5px;" @change="isAnswered()"></textarea>
      </div>
    </template>
  </div>
</template>

<script>
export default {
  props: {
    question: {
      type: Object,
      require: true
    }
  },
  mounted () {
  },
  data: function () {
    return {
      answer: {
        isAnswered: false,
        score: -1,
        value: ''
      },
      valueList: []
    }
  },
  watch: {
    'answer': {
      handler: function () {
        this.$emit('input', this.answer)
      },
      deep: true
    },
    'valueList': {
      handler: function () {
        if (this.valueList) {
          let totalScore = 0
          let answerValues = []
          for (let value of this.valueList) {
            totalScore += value.score
            answerValues.push(value.name)
          }
          this.answer.score = totalScore
          this.answer.value = JSON.stringify(answerValues)
        }
      },
      deep: true
    }
  },
  methods: {
    isAnswered () {
      this.answer.isAnswered = true
      this.$emit('answered')
    },
    setScore (score) {
      this.isAnswered()
      this.answer.score = score
    }
  }
}
</script>
