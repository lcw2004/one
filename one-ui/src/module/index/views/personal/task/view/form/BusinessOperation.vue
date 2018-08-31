<template>
  <div>
    <component
      v-if="componentName"
      :is="componentName"
      :taskInfo="taskInfo">
    </component>
  </div>
</template>

<script>
import AuditMixin from './AuditMixin'
import AuditOperation from './business/AuditOperation.vue'

export default {
  mixins: [AuditMixin],
  components: {
    AuditOperation
  },
  computed: {
    componentName: function () {
      return this.variables && this.variables.operationForm ? this.variables.operationForm : 'AuditOperation'
    }
  }
}
</script>
