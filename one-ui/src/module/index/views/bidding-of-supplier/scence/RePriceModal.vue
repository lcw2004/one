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
            <div class="pull-right">
              <button type="button" class="btn btn-info btn-sm">
                  <i class="fa fa-plus"></i>  导入报价信息
                </button>
              <button type="button" class="btn btn-info btn-sm">
                  <i class="fa fa-plus"></i>  下载模板
                </button>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 modal-scrollable">
            <table class="table table-bordered table-hover">
              <tbody>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>编码</th>
                  <th>名称</th>
                  <th>数量</th>
                  <th>报价</th>
                  <th>计量单位</th>
                  <th>规格型号</th>
                  <th>交货地点</th>
                </tr>
                <tr>
                  <td>1</td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>99.00</td>
                  <td><input type="text" class="form-control input-sm" style="width: 60px"></td>
                  <td>吨</td>
                  <td>xxx</td>
                  <td>长沙</td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>99.00</td>
                  <td><input type="text" class="form-control input-sm" style="width: 60px"></td>
                  <td>吨</td>
                  <td>xxx</td>
                  <td>长沙</td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>99.00</td>
                  <td><input type="text" class="form-control input-sm" style="width: 60px"></td>
                  <td>吨</td>
                  <td>xxx</td>
                  <td>长沙</td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>001</td>
                  <td>钢铁</td>
                  <td>99.00</td>
                  <td>10000</td>
                  <td>吨</td>
                  <td>xxx</td>
                  <td>长沙</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <div class="row">
          <div class="col-md-6">
            <div class="pull-left">共计 <span class="label label-success">2</span> 项需要二次报价，已报 <span class="label label-success">0</span> 项目</div>
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
      let self = this
      this.$confirm('确认提交报价信息并回应吗？在回应截止时间之前还可重新报价', () => {
        self.config.show = false
        self.$notify.success('回应成功')
      })
    }
  }
}
</script>
