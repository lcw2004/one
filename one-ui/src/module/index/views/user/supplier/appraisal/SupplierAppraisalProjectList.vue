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
                  <th>评价状态</th>
                  <th>我的评分</th>
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
                  <td>{{ obj.scoreStatusLabel }}</td>
                  <td>{{ obj.userScore ? obj.userScore.score : '--' }}</td>
                  <td>
                    <a v-if="obj.scoreStatus == 1" @click="startScore(obj)">评分</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <SupplierScoreModal v-if="config.show" :config="config" :projectId="obj.projectId" :supplierId="obj.supplierId" :templateId="obj.templateId" @ok="loadData()"></SupplierScoreModal>
  </section>
</template>

<script>
import SupplierScoreModal from './SupplierScoreModal.vue'

export default {
  components: {
    SupplierScoreModal
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
      obj: {}
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.user.listProjectForScoreOfSupplier(this.supplierId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.projectList = result.data
        }
      })
    },
    startScore (obj) {
      this.obj = obj
      this.config.show = true
    }
  }
}
</script>
