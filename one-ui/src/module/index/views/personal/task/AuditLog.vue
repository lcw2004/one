<template>
  <div class="row">
    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th style="width: 10px">#</th>
            <th>操作人</th>
            <th>操作名称</th>
            <th>操作时间</th>
            <th>审核意见</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(log, index) of logList">
            <td>{{ index + 1 }}</td>
            <td>{{ log.userInfo.name }}</td>
            <td>
              <span class="label" :class="labelClass(log)">{{ log.operateName }}</span>
            </td>
            <td>{{ log.auditTime }}</td>
            <td><LongText :text="log.remark"></LongText></td>
          </tr>
        </tbody>
      </table>
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
