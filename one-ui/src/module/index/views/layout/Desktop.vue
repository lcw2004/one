<template>
  <section class="content">
    <!--首页统计组件-->
    <div class="row">
      <StatisticComponent :dataList="statisticComponentList"></StatisticComponent>
    </div>

    <!--供应商信息-->
    <template v-if="userInfo.type == 3">
      <SupplierInfo></SupplierInfo>
    </template>
  </section>
</template>

<script>
import StatisticComponent from './desktop/StatisticComponent.vue'
import DesktopToDo from './desktop/DesktopToDo.vue'

export default {
  components: {
    StatisticComponent,
    DesktopToDo
  },
  data: function () {
    return {
      homeComponentList: []
    }
  },
  computed: {
    userInfo: function () {
      return this.$store.state.system.userInfo
    },
    statisticComponentList: function () {
      return this.homeComponentList.filter(item => item.componentType === 1)
    }
  },
  mounted () {
    this.$store.dispatch('activePageTitle', '首页')
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
