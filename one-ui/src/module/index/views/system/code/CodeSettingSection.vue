<template>
  <div class="row row-margin-bottom">
    <div class="col-md-8">
      <div class="row">
        <div class="col-md-2">
          <p class="form-control-static">段{{ section.orderIndex }}</p>
        </div>
        <div class="col-md-4">
          规则
          <select class="form-control" v-model="section.type" style="width: 120px; display:inline">
            <option :value="2">年</option>
            <option :value="3">年月</option>
            <option :value="4">年月日</option>
            <option :value="5">固定内容</option>
            <option :value="6">流水号</option>
          </select>
        </div>
        <div class="col-md-6">
          <template v-if="section.type == 2 || section.type == 3 || section.type == 4">
            <p class="form-control-static">示例：{{ formatTime }}</p>
          </template>
          <template v-if="section.type == 5">
            内容 <input type="text" class="form-control" v-model="section.content" style="width: 110px; display:inline">
          </template>
          <template v-if="section.type == 6">
            长度
            <select class="form-control" v-model="section.length" style="width: 110px; display:inline" @change="fillContent()">
              <option :value="i + 2" v-for="i in 8">{{ i + 2}}</option>
            </select>
            起始值 <input type="text" class="form-control" v-model="section.content"  style="width: 110px; display:inline">
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as utils from './code-setting.js'
export default {
  props: {
    section: {
      type: Object,
      require: true
    }
  },
  computed: {
    formatTime: function () {
      return utils.timeNow(this.section.type)
    }
  },
  methods: {
    fillContent () {
      if (this.section.type === 6) {
        this.section.content = utils.fillZero(this.section.length)
      }
    }
  },
  watch: {
    'section.content': {
      handler: function () {
        if (this.section.type === 6 && this.section.content.length > this.section.length) {
          this.fillContent()
        }
      },
      deep: true,
      immediate: true
    }
  }
}
</script>
