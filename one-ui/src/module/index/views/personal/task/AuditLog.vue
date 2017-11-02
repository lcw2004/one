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
            <th>备注</th>
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
            <td>{{ log.remark }}</td>
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
  data: () => {
    return {
      actions: {
        list: {method: 'get', url: '/api/flow/auditLog'}
      },
      logList: []
    }
  },
  mounted () {
    this.resource = this.$resource(null, {}, this.actions)
    this.loadData()
  },
  methods: {
    loadData () {
      this.resource.list({
        businessId: this.businessId,
        secondBusinessId: this.secondBusinessId,
        businessType: this.businessType
      }).then(function (response) {
        let result = response.body
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
