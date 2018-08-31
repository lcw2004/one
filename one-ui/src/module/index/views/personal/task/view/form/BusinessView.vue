<template>
  <div>
    <component
      v-if="isValidComponent"
      :is="componentName"
      :businessId="taskInfo.variables.businessId"
      :secondBusinessId="secondBusinessId"
      :taskInfo="taskInfo"
      :showFooter="false">
    </component>

    <div class="row" v-if="!isValidComponent">
      <div class="col-md-12 text-center margin-50">
        未配置审核页面，请联系管理员进行配置
      </div>
    </div>
  </div>
</template>

<script>
let components = [
]

export default {
  components: {
  },
  props: {
    taskInfo: {
      type: Object,
      required: true
    }
  },
  computed: {
    isValidComponent: function () {
      return components.indexOf(this.componentName) >= 0
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
  }
}
</script>
