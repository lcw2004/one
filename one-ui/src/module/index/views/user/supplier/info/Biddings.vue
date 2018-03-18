<template>
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
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    obj: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      projectList: []
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.user.listSupplierProjectOfSupplier(this.obj.supplierId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.projectList = result.data
        }
      })
    }
  }
}
</script>
