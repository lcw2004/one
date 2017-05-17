<template>
<div class="modal" v-show="config.show" style="display: block">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="config.show = false">
          <span aria-hidden="true">×</span>
        </button>
        <h4 class="modal-title" v-text="config.title"></h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <tbody>
                <tr>
                  <th style="width: 50px">选择</th>
                  <th>姓名</th>
                  <th>账号</th>
                  <th>手机</th>
                  <th>类别</th>
                  <th>抽取时间</th>
                  <th>抽取方式</th>
                </tr>
                <tr>
                  <td><input type="radio"></td>
                  <td>张三</td>
                  <td>001</td>
                  <td>12345678901</td>
                  <td></td>
                  <td>2017-01-01 12:00:00</td>
                  <td>自动</td>
                </tr>
                <tr>
                  <td><input type="radio"></td>
                  <td>李四</td>
                  <td>002</td>
                  <td>12345678901</td>
                  <td></td>
                  <td>2017-01-01 12:00:00</td>
                  <td>自动</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <div class="row">
          <div class="col-md-6">
            <div class="pull-left">
              当前选中<span class="label label-success">李四</span>作为专家组长
            </div>
          </div>
          <div class="col-md-6">
            <button type="button" class="btn btn-default " data-dismiss="modal" @click="config.show = false">取消</button>
            <button type="button" class="btn btn-primary" @click="ok()">确认</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  props: {
    config: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
    }
  },
  methods: {
    ok () {
      this.config.show = false
      this.$notify.success('开标成功')
    }
  }
}
</script>
