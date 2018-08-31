<template>
  <div v-if="hasPermission" style="display: inline;">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'Permission',
  props: {
    value: {
      type: String,
      required: true
    }
  },
  data: function () {
    return {}
  },
  computed: {
    permisionMap: function () {
      return this.$store.state.system.permisionMap
    },
    hasPermission: function () {
      return this.permisionMap[this.value]
    }
  }
}
</script>
