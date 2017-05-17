<template>
<div class="box-body">
  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-3">报价响应率</label>
        <div class="col-md-5">
          <div class="input-group">
            <input type="text" class="form-control">
            <span class="input-group-addon">%</span>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-4"></div>
    <div class="col-md-1">
      <button type="button" class="btn btn-info btn-sm pull-right" @click="config.show = true">
        <i class="fa fa-plus"></i>  导入标的
      </button>
    </div>
    <div class="col-md-1">
      <button type="button" class="btn btn-info btn-sm pull-right" @click="config.show = true">
        <i class="fa fa-plus"></i>  添加标的
      </button>
    </div>

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
            <td>
              <a>删除</a> | <a>编辑</a>
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
            <td>
              <a>删除</a> | <a>编辑</a>
            </td>
          </tr>
          <tr>
            <td>3</td>
            <td>
              <div class="col-md-12"><input type="text" class="form-control input-sm" style="width: 80px"></div>
            </td>
            <td>
              <div class="col-md-12"><input type="text" class="form-control input-sm"></div>
            </td>
            <td>
              <div class="col-md-12"><input type="text" class="form-control input-sm" style="width: 80px"></div>
            </td>
            <td>
              <div class="col-md-12"><input type="text" class="form-control input-sm" style="width: 80px"></div>
            </td>
            <td>
              <div class="col-md-12"><input type="text" class="form-control input-sm" style="width: 80px"></div>
            </td>
            <td>
              <div class="col-md-12"><input type="text" class="form-control input-sm" style="width: 80px"></div>
            </td>
            <td>
              <div class="col-md-12"><input type="text" class="form-control input-sm"></div>
            </td>
            <td>
              <a>删除</a> | <a>编辑</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>
</template>

<script>
export default {}
</script>
