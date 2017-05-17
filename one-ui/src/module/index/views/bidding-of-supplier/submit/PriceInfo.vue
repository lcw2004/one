<template>
<section class="content">
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
    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <tbody>
          <tr>
            <th style="width: 10px">#</th>
            <th>编码</th>
            <th>名称</th>
            <th>数量</th>
            <th>参考价</th>
            <th>计量单位</th>
            <th>规格型号</th>
            <th>交货地点</th>
            <th>报价</th>
            <th>操作</th>
          </tr>
          <tr>
            <td>1</td>
            <td>001</td>
            <td>钢铁</td>
            <td>1000</td>
            <td>99.00</td>
            <td>吨</td>
            <td>xxx</td>
            <td>长沙</td>
            <td>10000</td>
            <td>
              <a>编辑</a>
            </td>
          </tr>
          <tr>
            <td>2</td>
            <td>001</td>
            <td>钢铁</td>
            <td>1000</td>
            <td>88.05</td>
            <td>吨</td>
            <td>xxx</td>
            <td>长沙</td>
            <td><input type="text" class="form-control input-sm" style="width: 100px"></td>
            <td>
              <a>保存</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <div class="form-group">
        <label class="control-label col-md-1">总计</label>
        <div class="col-md-2">
          <p class="form-control-static">10000.00 元</p>
        </div>
      </div>
    </div>
  </div>
</section>
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
