<template>
  <div>
    <TimeLine>
      <template v-for="log of list">
        <TimeItem icon="fa fa-user bg-blue" :time="log.operateTime" :showFooter="false" :showBody="log.remark">
          <TimeItemHeader slot="header">
            <a>{{ log.userInfo ? log.userInfo.name : '' }}</a> {{ log.operateType }}
          </TimeItemHeader>
          <TimeItemBody slot="body">
            <component :is="viewComponent(log)" :log="log"></component>
          </TimeItemBody>
        </TimeItem>
      </template>

      <li>
        <i class="fa fa-clock-o bg-blue"></i>
      </li>
    </TimeLine>
  </div>
</template>

<script>
import LevelUpdateView from './log/LevelUpdateView.vue'
import SimpleTextView from './log/SimpleTextView.vue'

export default {
  components: {
    LevelUpdateView,
    SimpleTextView
  },
  props: {
    obj: {
      type: Object,
      required: true
    },
    supplierId: {
      type: String,
      required: true
    }
  },
  data: function () {
    return {
      list: []
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    viewComponent (log) {
      if (log.operateCode === '106') {
        return 'LevelUpdateView'
      } else {
        return 'SimpleTextView'
      }
    },
    loadData () {
      this.$api.user.listSupplierLog(this.supplierId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.list = result.data
        }
      })
    }
  }
}
</script>
