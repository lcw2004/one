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
            <ScoreAnswerView :scoreId="scoreId" @totalScore="totalScore = arguments[0]"></ScoreAnswerView>
          </div>
          <div class="modal-footer">
            <div class="row">
              <div class="col-md-6">
                <div class="pull-left">
                  总计得分：<span class="label label-primary">{{ totalScore }}分</span>
                </div>
              </div>
              <div class="col-md-6">
                <div class="pull-right">
                    <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">关闭</button>
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
import ScoreAnswerView from './ScoreAnswerView.vue'

export default {
  mixins: [ModalMixin],
  components: {
    ScoreAnswerView
  },
  props: {
    scoreId: {
      type: String,
      required: true
    }
  },
  data: function () {
    return {
      totalScore: '--'
    }
  }
}
</script>
