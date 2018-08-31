<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">{{ taskInfo.itemsName }}({{ taskId }})</h3>
      </div>
      <div class="box-body">
        <div class="row" v-if="taskInfo.taskId">
          <div class="col-md-12">
            <AuditUserInfo :taskInfo="taskInfo"></AuditUserInfo>
          </div>
          <div class="col-md-12">
            <BusinessView v-if="taskInfo.variables.businessId" :taskInfo="taskInfo"></BusinessView>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import AuditUserInfo from './form/AuditUserInfo.vue'
import BusinessView from './form/BusinessView.vue'

export default {
  components: {
    AuditUserInfo,
    BusinessView
  },
  data: function () {
    return {
      taskInfo: {}
    }
  },
  computed: {
    taskId: function () {
      return this.$route.params.id
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.getHistoryTaskInfo(this.taskId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.taskInfo = result.data
        }
      })
    }
  }
}
</script>
