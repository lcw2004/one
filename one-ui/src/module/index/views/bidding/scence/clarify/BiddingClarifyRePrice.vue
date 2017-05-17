<template>
<div class="modal" v-show="config.show" style="display: block;">
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
          <div class="col-md-12 modal-scrollable">
            <table class="table table-bordered table-hover">
              <tbody>
                <tr>
                  <th style="width: 10px"><input type="checkbox"/></th>
                  <th>编码</th>
                  <th>名称</th>
                  <th>数量</th>
                  <th>参考价</th>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>99.00</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>1000</td>
                  <td>88.05</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <div class="row">
          <div class="col-md-6">
            <div class="pull-left">选中 <span class="label label-success">5</span> 个标的物</div>
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
  }
}
</script>
