<template>
  <div class="score-question-option">
    <template v-if="question.type == 1">
      <div>
        <div class="radio">
          <label><input type="radio" :value="1" v-model="question.value">是 ({{ question.score }}分)</label>
        </div>
        <div class="radio">
          <label><input type="radio" :value="0" v-model="question.value">否 (0分)</label>
        </div>
      </div>
    </template>

    <template v-if="question.type == 2">
      <div>
        <div class="radio" v-for="option of question.optionList">
          <label><input type="radio" :value="option.name" v-model="question.value">{{ option.name }} ({{ option.score }}分) </label>
        </div>
      </div>
    </template>

    <template v-if="question.type == 3">
      <div>
        <div class="checkbox" v-for="option of question.optionList">
          <label>
            <input type="checkbox" :value="option.name" v-model="question.value"> {{ option.name }} ({{ option.score }}分)
          </label>
        </div>
      </div>
    </template>

    <template v-if="question.type == 4">
      <div>
        <el-input-number size="small" :min="1" :max="question.score" label="打分" v-model="question.value"></el-input-number>
        <textarea class="form-control" rows="2" v-model="question.value1" maxlength="200" placeholder="请输入理由" style="margin-top: 5px;"></textarea>
      </div>
    </template>
  </div>
</template>

<script>
export default {
  components: {
  },
  props: {
    question: {
      type: Object,
      require: true
    }
  },
  data: function () {
    return {
    }
  },
  methods: {
  }
}
</script>
