<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <h3 class="box-title">{{ taskInfo.itemsName }}({{ taskId }})</h3>
    </div>
    <div class="box-body">
      <div class="row">
        <div class="col-md-12">
          <AuditOperation :taskInfo="taskInfo"></AuditOperation>
        </div>
      </div>
      <div class="row">
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
  data: () => {
    return {
      actions: {
        getTaskInfo: {method: 'get', url: '/api/flow/task/todo/{taskId}'}
      },
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
    this.resource = this.$resource(null, {}, this.actions)
    this.loadData()
  },
  methods: {
    loadData () {
      this.resource.getTaskInfo({taskId: this.taskId}).then(function (response) {
        let result = response.body
        if (result.ok) {
          this.taskInfo = result.data
        }
      })
    }
  }
}
</script>
