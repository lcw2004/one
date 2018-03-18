<template>
  <div class="row">
    <StatisticComponent :dataList="statisticComponentList"></StatisticComponent>
  </div>
</template>

<script>
import StatisticComponent from './desktop/StatisticComponent.vue'

export default {
  components: {
    StatisticComponent
  },
  data: function () {
    return {
      homeComponentList: []
    }
  },
  computed: {
    statisticComponentList: function () {
      return this.homeComponentList.filter(item => item.componentType === 1)
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.homeStatistic().then((response) => {
        let result = response.data
        if (result.ok) {
          this.homeComponentList = result.data
        }
      })
    }
  }
}
</script>
