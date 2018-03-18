<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">{{ taskInfo.itemsName }}({{ taskId }})</h3>
      </div>
      <div class="box-body">
        <div class="row">
          <div class="col-md-12">
            <AuditOperation :taskInfo="taskInfo"></AuditOperation>
          </div>
        </div>
        <div class="row" v-if="taskInfo.taskId">
          <div class="col-md-12">
            <component :is="componentName" v-if="taskInfo.variables.businessId" :businessId="taskInfo.variables.businessId" :secondBusinessId="secondBusinessId" :taskInfo="taskInfo"></component>
          </div>
        </div>
      </div>
      <div class="box-footer">
      </div>
    </div>
  </section>
</template>

<script>
import AuditOperation from './AuditOperation.vue'

export default {
  components: {
    AuditOperation
  },
  data: function () {
    return {
      taskInfo: {}
    }
  },
  computed: {
    taskId: function () {
      return this.$route.params.id
    },
    componentName: function () {
      return this.taskInfo.formKey
    },
    secondBusinessId: function () {
      if (this.taskInfo.formKey === 'BiddingProjectSupplierApplyView') {
        return this.taskInfo.variables.supplierId
      } else {
        return ''
      }
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.getTaskInfo(this.taskId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.taskInfo = result.data
        }
      })
    }
  }
}
</script>
