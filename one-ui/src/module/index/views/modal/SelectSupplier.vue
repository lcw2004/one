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
          <div class="col-md-4">
            <div class="form-group">
              <label class="control-label col-md-4">供应商</label>
              <div class="col-md-8">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="供应商名称">
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
            <tbody>
              <tr>
                <th style="width: 10px"><input type="checkbox"/></th>
                <th>公司名称</th>
                <th>联系人</th>
                <th>手机号码</th>
              </tr>
              <tr>
                <td><input type="checkbox"/></td>
                <td>Update software</td>
                <td>张三</td>
                <td>12345678901</td>
              </tr>
              <tr>
                <td><input type="checkbox"/></td>
                <td>Clean database</td>
                <td>李四</td>
                <td>12345678901</td>
              </tr>
              <tr>
                <td><input type="checkbox"/></td>
                <td>Cron job running</td>
                <td>王五</td>
                <td>12345678901</td>
              </tr>
            </tbody>
          </table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default " data-dismiss="modal" @click="config.show = false">取消</button>
        <button type="button" class="btn btn-primary" @click="ok()">确认</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  props: {
    // 模态窗属性配置
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
    ok: function () {

    }
  }
}
</script>
