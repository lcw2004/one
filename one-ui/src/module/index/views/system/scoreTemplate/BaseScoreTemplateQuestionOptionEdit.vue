<template>
  <div>
    <template v-if="question.type == 1">
    </template>

    <template v-if="question.type == 2">
      <template v-for="(option, index) of question.optionList">
        <div class="row margin-bottom-5">
          <div class="col-md-12">
            <input type="text" class="form-control" v-model="option.name" style="width: 200px; display: inline-block" placeholder="请输入选项内容"/>
            <input type="number" class="form-control" v-model.number="option.score"  style="width: 80px; display: inline-block" placeholder="请输入选项分值" /> 分
            <a @click="removeOption(index)">移除</a>
          </div>
        </div>
      </template>
      <div class="row margin-bottom-5">
        <div class="col-md-4">
          <FullButton @click="addOption()">+ 添加选项</FullButton>
        </div>
      </div>
    </template>

    <template v-if="question.type == 3">
      <template v-for="(option, index) of question.optionList">
        <div class="row margin-bottom-5">
          <div class="col-md-12">
            <input type="text" class="form-control" v-model="option.name" style="width: 200px; display: inline-block" placeholder="请输入选项内容"/>
            <input type="number" class="form-control" v-model.number="option.score"  style="width: 80px; display: inline-block" placeholder="请输入选项分值" /> 分
            <a @click="removeOption(index)">移除</a>
          </div>
        </div>
      </template>

      <div class="row margin-bottom-5">
        <div class="col-md-4">
          <FullButton @click="addOption()">+ 添加选项</FullButton>
        </div>
      </div>
    </template>

    <template v-if="question.type == 4">
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
    addOption () {
      this.question.optionList.push({
        name: '新选项',
        score: 1,
        questionId: this.question.questionId
      })
    },
    removeOption (index) {
      this.question.optionList.splice(index, 1)
    }
  }
}
</script>
