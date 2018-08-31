<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <div class="success-label">
          <span class="success-label-icon">
            <img v-if="result.totalCount == result.successCount" src="../../../../../account/assets/images/success.png">
            <img v-if="result.totalCount != result.successCount" src="../../../../../account/assets/images/fail.png">
          </span>
          <span class="success-label-title">
            <h4>导入完成</h4>
            <div>
              共{{ result.totalCount }}条数据，成功导入{{ result.successCount }}条，失败{{ result.totalCount - result.successCount }}条
              <div class="pull-right">
                <button type="button" class="btn btn-primary" @click="$emit('last')">
                  继续导入
                </button>
              </div>
            </div>
          </span>
        </div>
      </div>
    </div>

    <div class="row"  v-if="result.failDataList.length > 0">
      <div class="col-md-12" style="margin: 10px 0">
        以下是导入失败的数据
      </div>
      <div class="col-md-12">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th>行号</th>
              <th>失败原因</th>
              <th>登录账号</th>
              <th>姓名</th>
              <th>手机</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="obj of result.failDataList">
              <td>第{{ obj.index }}行</td>
              <td><span class="label label-warning">{{ obj.failReason }}</span></td>
              <td>{{ obj.data.account }}</td>
              <td>{{ obj.data.name }}</td>
              <td>{{ obj.data.mobile }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    result: {
      type: Object,
      required: true
    }
  }
}
</script>
