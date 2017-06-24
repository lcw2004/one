<template>
  <span :class="statusClass">{{ desc }}</span>
</template>

<script>
  export default {
    props: {
      status: {},
      desc: {}
    },
    computed: {
      statusClass: function () {
        if (this.status === -2) {
          return 'label label-default'
        } else if (this.status === -1) {
          return 'label label-default'
        } else if (this.status === 1) {
          return 'label label-primary'
        } else if (this.status === 2) {
          return 'label label-success'
        } else if (this.status === 3) {
          return 'label label-warning'
        } else if (this.status === 4) {
          return 'label label-default'
        } else {
          return ''
        }
      }
    }
  }
</script>
