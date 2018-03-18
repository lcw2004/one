<template>
  <section class="content">
    <div class="box">
      <div class="box-header">
        <h3 class="box-title"></h3>
      </div>
      <div class="box-body">
        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>项目编号</th>
                  <th>项目名称</th>
                  <th>项目类型</th>
                  <th>开标/竞价时间</th>
                  <th>参与结果</th>
                  <th>评价状态</th>
                  <th>评分</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(obj, index) of projectList">
                  <td>{{ index + 1 }}</td>
                  <td>
                    <router-link :to="'/bid/project/' + obj.projectId + '/view'">{{ obj.projectId }}</router-link>
                  </td>
                  <td>{{ obj.project.projectName }}</td>
                  <td>
                    <DictLabel type="bidding_projectType" :value="obj.project.projectType"></DictLabel>
                  </td>
                  <td>{{ obj.project.openTime }}</td>
                  <td>{{ obj.biddingStatusLabel }}</td>
                  <td>{{ obj.scoreStatusLabel }}</td>
                  <td>{{ obj.scoreStatus == 3 ? obj.score : '--' }}</td>
                  <td>
                    <a v-if="obj.scoreStatus == 1" @click="startScore(obj)">发起评价</a>
                    <a v-if="obj.scoreStatus == 2" @click="endScore(obj)">结束评价</a>
                    <a v-if="obj.scoreStatus != 1" @click="showScore(obj)">查看评分</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <SupplierAppraisalModal v-if="config.show" :config="config" :projectId="projectId" :supplierId="supplierId" @ok="loadData()"></SupplierAppraisalModal>
    <SupplierProjectScoreListModal v-if="scoreListConfig.show" :config="scoreListConfig" :scoreListConfig="config" :projectId="projectId" :supplierId="supplierId" @ok="loadData()"></SupplierProjectScoreListModal>
  </section>
</template>

<script>
import SupplierAppraisalModal from './SupplierAppraisalModal.vue'
import SupplierProjectScoreListModal from './SupplierProjectScoreListModal.vue'

export default {
  components: {
    SupplierAppraisalModal,
    SupplierProjectScoreListModal
  },
  computed: {
    supplierId: function () {
      return this.$route.params.id
    }
  },
  data: function () {
    return {
      projectList: [],
      config: {
        show: false,
        title: '发起评分'
      },
      scoreListConfig: {
        show: false,
        title: '查看评分'
      },
      projectId: ''
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.user.listSupplierProjectOfSupplier(this.supplierId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.projectList = result.data
        }
      })
    },
    startScore (obj) {
      this.projectId = obj.projectId
      this.config.show = true
    },
    endScore (obj) {
      this.$confirm('确认终止评价吗？', () => {
        this.$api.user.endScore(this.supplierId, obj.projectId).then((response) => {
          let result = response.data
          if (result.ok) {
            this.$notify.success('操作成功')
            this.loadData()
          }
        })
      })
    },
    showScore (obj) {
      this.projectId = obj.projectId
      this.scoreListConfig.show = true
    }
  }
}
</script>
