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
          <form class="form-inline">
            <div class="col-md-6"><label class="control-label">姓名</label> <input type="text" placeholder="姓名" class="form-control inline-block"></div>
          </form>
        </div>
        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <tbody>
                <tr>
                  <th style="width: 10px"><input type="checkbox"></th>
                  <th>姓名</th>
                  <th>单位</th>
                  <th>电话</th>
                  <th>状态</th>
                </tr>
                <tr>
                  <td><input type="checkbox"></td>
                  <td>
                    <router-link to="/expert/info">张三</router-link>
                  </td>
                  <td>某某公司</td>
                  <td>13900000000</td>
                  <td>
                    <span class="label label-warning">审核驳回</span>
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox"></td>
                  <td>
                    <router-link to="/expert/info">李四</router-link>
                  </td>
                  <td>某某公司</td>
                  <td>13900000000</td>
                  <td>
                    <span class="label label-danger">停用</span>
                  </td>
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
  }
}
</script>
