<template>
  <span :class="statusClass">
    <DictLabel type="supplier_level" :value="value"></DictLabel>
  </span>
</template>

<script>
export default {
  props: {
    value: {}
  },
  computed: {
    statusClass: function () {
      if (this.value === 1) {
        return 'label label-danger'
      } else if (this.value === 2) {
        return 'label label-primary'
      } else if (this.value === 3) {
        return 'label label-success'
      } else if (this.value === 4) {
        return 'label label-success'
      } else {
        return ''
      }
    }
  }
}
</script>
