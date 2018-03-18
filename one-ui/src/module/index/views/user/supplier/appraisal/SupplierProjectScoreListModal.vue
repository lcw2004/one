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
            <div class="row row-margin-bottom">
              <div class="col-md-12">
                <template v-if="notScoreCount > 0">未评价数量：<span class="label label-primary">{{ notScoreCount }}</span>，</template>
                本次评价平均分：<span class="label label-primary">{{ averageScore }}</span>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <table class="table table-bordered table-hover">
                  <thead>
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>评分人</th>
                      <th>评分状态</th>
                      <th>评分时间</th>
                      <th>评分</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(obj, index) of list">
                      <td>{{ index + 1}}</td>
                      <td>{{ obj.userInfo.name }}</td>
                      <td>{{ obj.scoreStatusLabel }}</td>
                      <td>{{ obj.userScore ? obj.userScore.createTime : ''}}</td>
                      <td>{{ obj.userScore ? obj.userScore.score : ''}}</td>
                      <td><a @click="viewAnswer(obj)" v-if="obj.userScore">查看评分调查表</a></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">取消</button>
            <button type="button" class="btn btn-primary" @click="ok()">确认</button>
          </div>
        </div>
      </div>

      <ScoreAnswerViewModal v-if="viewAnswerConfig.show" :config="viewAnswerConfig" :scoreId="scoreId"></ScoreAnswerViewModal>
    </div>
  </OneTransition>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin.js'
import ScoreAnswerViewModal from '../../../system/scoreTemplate/score/ScoreAnswerViewModal.vue'

export default {
  mixins: [ModalMixin],
  components: {
    ScoreAnswerViewModal
  },
  props: {
    supplierId: {
      type: String,
      required: true
    },
    projectId: {
      type: String,
      required: true
    }
  },
  computed: {
    totalScore: function () {
      let totalScore = 0
      if (this.list) {
        for (let obj of this.list) {
          if (obj.userScore) {
            totalScore += obj.userScore.score
          }
        }
      }
      return totalScore
    },
    scoreCount: function () {
      let count = 0
      if (this.list) {
        for (let obj of this.list) {
          if (obj.userScore) {
            count++
          }
        }
      }
      return count
    },
    notScoreCount: function () {
      return this.list.length - this.scoreCount
    },
    averageScore: function () {
      if (this.scoreCount > 0) {
        return (this.totalScore / this.scoreCount).toFixed(2)
      } else {
        return '--'
      }
    }
  },
  data: function () {
    return {
      list: [],
      scoreId: '',
      viewAnswerConfig: {
        show: false,
        title: '查看评分调查表'
      }
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.user.listScoreOfSupplier(this.supplierId, this.projectId).then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.list = result.data
        }
      })
    },
    viewAnswer (obj) {
      this.scoreId = obj.userScore.scoreId
      this.viewAnswerConfig.show = true
    },
    ok () {
      this.config.show = false
    }
  }
}
</script>
