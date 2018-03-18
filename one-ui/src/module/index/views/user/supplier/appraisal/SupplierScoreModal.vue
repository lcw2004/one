<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
              <span aria-hidden="true">×</span>
            </button>
            <h4 class="modal-title" v-text="config.title"></h4>
          </div>
          <div class="modal-body">
            <ScoreTemplate ref="temp" :templateId="templateId" @answer="onAnswer(arguments)"></ScoreTemplate>
          </div>
          <div class="modal-footer">
            <div class="row">
              <div class="col-md-6">
                <div class="pull-left modal-selected-data">
                  KPI考核总数：<span class="label label-primary">{{ questionCount }}</span>，
                  <template v-if="questionCount - answerCount > 0">剩余未回答：<span class="label label-warning">{{ questionCount - answerCount }}</span>，</template>
                  总得分：<span class="label label-primary">{{ totalScore }}</span>
                </div>
              </div>
              <div class="col-md-6">
                <div class="pull-right">
                  <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">取消</button>
                  <button type="button" class="btn btn-primary" @click="ok()">确认</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </OneTransition>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin.js'
import ScoreTemplate from '../../../system/scoreTemplate/score/ScoreTemplate.vue'

export default {
  components: {
    ScoreTemplate
  },
  mixins: [ModalMixin],
  props: {
    config: {
      type: Object,
      required: true
    },
    templateId: {
      type: String,
      required: true
    },
    projectId: {
      type: String,
      required: true
    },
    supplierId: {
      type: String,
      required: true
    }
  },
  data: function () {
    return {
      questionCount: null,
      answerCount: null,
      totalScore: null,
      template: {}
    }
  },
  mounted () {
  },
  methods: {
    ok () {
      if (this.valid()) {
        this.$api.user.saveScore(this.supplierId, this.projectId, this.template).then((response) => {
          let result = response.data
          if (result.ok) {
            this.config.show = false
            this.$notify.success('评分成功')
            this.$emit('ok')
          }
        })
      }
    },
    valid () {
      if (!this.$refs.temp.valid()) {
        return false
      }
      if (this.answerCount < this.questionCount) {
        this.$notify.warn('还有' + (this.questionCount - this.answerCount) + '道问题未回答')
        return false
      }
      return true
    },
    onAnswer (args) {
      this.questionCount = args[0]
      this.answerCount = args[1]
      this.totalScore = args[2]
      this.template = args[3]
    }
  }
}
</script>
