<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">{{ taskInfo.itemsName }}({{ taskId }})</h3>
      </div>
      <div class="box-body">
        <div class="row">
          <div class="col-md-12">
            <BusinessOperation :taskInfo="taskInfo"></BusinessOperation>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <AuditInfo :taskInfo="taskInfo"></AuditInfo>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <BusinessView :taskInfo="taskInfo"></BusinessView>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import api from '@api'
import AuditInfo from './form/AuditInfo.vue'
import BusinessOperation from './form/BusinessOperation.vue'
import BusinessView from './form/BusinessView.vue'

export default {
  components: {
    BusinessOperation,
    BusinessView,
    AuditInfo
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
  beforeRouteEnter (to, from, next) {
    api.system.getTaskInfo(to.params.id).then((response) => {
      let result = response.data
      if (result.ok) {
        next(vm => {
          vm.taskInfo = result.data
        })
      }
    })
  }
}
</script>
