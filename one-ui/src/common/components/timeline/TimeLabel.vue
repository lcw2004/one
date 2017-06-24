<template>
  <li class="time-label">
    <span :class="backgroundColor">{{ time }}</span>
    <template v-if="remark">{{ remark }}</template>
  </li>
</template>

<script>
  import * as dateUtil from '../../../common/utils/date-util'
  export default {
    name: 'TimeLabel',
    props: {
      time: {},
      remark: {},
      background: {
        required: false,
        default: 'bg-red'
      }
    },
    components: {},
    data: () => {
      return {}
    },
    computed: {
      backgroundColor: function () {
        if (dateUtil.isBefore(this.time)) {
          return 'bg-gray'
        } else {
          return 'bg-blue'
        }
      }
    }
  }
</script>
