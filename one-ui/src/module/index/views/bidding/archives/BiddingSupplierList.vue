<template>
  <div class="row" >
    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <tbody>
          <tr>
            <th style="width: 10px">#</th>
            <th>公司名称</th>
            <th>联系人</th>
            <th>手机号码</th>
            <th>参与结果</th>
          </tr>
          <tr>
            <td>1</td>
            <td>Update software</td>
            <td>张三</td>
            <td>12345678901</td>
            <td>未中标</td>
          </tr>
          <tr>
            <td>2</td>
            <td>Clean database</td>
            <td>李四</td>
            <td>12345678901</td>
            <td><span class="label label-success">中标</span></td>
          </tr>
          <tr>
            <td>3</td>
            <td>Cron job running</td>
            <td>王五</td>
            <td>12345678901</td>
            <td><span class="label label-success">中标</span></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  components: {
  },
  data: function () {
    return {
    }
  },
  methods: {
  }
}
</script>
