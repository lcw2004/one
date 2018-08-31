<template>
  <div class="row">
    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th style="width: 10px">序号</th>
            <th>操作人</th>
            <th>操作名称</th>
            <th>操作时间</th>
            <th>操作结果</th>
            <th>审核意见</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(log, index) of logList">
            <td>{{ index + 1 }}</td>
            <td>{{ log.userInfo.name }}</td>
            <td>{{ log.operateName }}</td>
            <td>{{ log.auditTime }}</td>
            <td>
              <span class="label" :class="labelClass(log)" v-if="log.result != null">{{ log.result == 1 ? '通过' : '不通过' }}</span>
            </td>
            <td><LongText :text="log.remark"></LongText></td>
          </tr>
        </tbody>
      </table>
      <TableNoData :data="logList">暂无数据</TableNoData>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    taskInfo: {
      type: Object
    },
    businessId: {
      type: String
    },
    secondBusinessId: {
      type: String
    },
    businessType: {
      type: String
    }
  },
  data: function () {
    return {
      logList: []
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.listAuditLog({
        businessId: this.businessId,
        secondBusinessId: this.secondBusinessId,
        businessType: this.businessType
      }).then((response) => {
        let result = response.data
        if (result.ok) {
          this.logList = result.data
        }
      })
    },
    labelClass (log) {
      if (log.result === 0) {
        return 'label-warning'
      } else if (log.result === 1) {
        return 'label-success'
      } else {
        return 'label-default'
      }
    }
  }
}
</script>
