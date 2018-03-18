<template>
  <div>
    <template v-for="(obj, index) of dataList">
      <CommonStatistic v-if="index < 8" :value="obj"></CommonStatistic>
    </template>
  </div>
</template>

<script>
import TaskStatistic from './statistic/TaskStatistic.vue'
import CommonStatistic from './statistic/CommonStatistic.vue'

export default {
  components: {
    TaskStatistic,
    CommonStatistic
  },
  props: {
    dataList: {
      type: Array,
      required: true
    }
  },
  data: function () {
    return {
    }
  },
  methods: {
  }
}
</script>
