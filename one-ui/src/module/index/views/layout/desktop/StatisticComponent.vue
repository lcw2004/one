<template>
  <div>
    <template v-for="(obj, index) of showDataList">
      <CommonStatistic1 v-if="index < 8" :value="obj"></CommonStatistic1>
    </template>
  </div>
</template>

<script>
import CommonStatistic from './statistic/CommonStatistic.vue'
import CommonStatistic1 from './statistic/CommonStatistic1.vue'

export default {
  components: {
    CommonStatistic,
    CommonStatistic1
  },
  props: {
    dataList: {
      type: Array,
      required: true
    }
  },
  computed: {
    showDataList: function () {
      return this.dataList
    }
  }
}
</script>
