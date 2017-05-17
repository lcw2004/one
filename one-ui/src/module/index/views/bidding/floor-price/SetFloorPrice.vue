<template>
<section class="content">
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header">
          <h3 class="box-title">设置拦标价</h3>
        </div>
        <div class="box-body">
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
                    <th>拦标价</th>
                    <th>计量单位</th>
                    <th>规格型号</th>
                    <th>交货地点</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>001</td>
                    <td>钢铁</td>
                    <td>1000</td>
                    <td>99.00</td>
                    <td><input type="text" class="form-control input-sm" style="width: 60px"></td>
                    <td>吨</td>
                    <td>xxx</td>
                    <td>长沙</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>001</td>
                    <td>钢铁</td>
                    <td>1000</td>
                    <td>88.05</td>
                    <td><input type="text" class="form-control input-sm" style="width: 60px"></td>
                    <td>吨</td>
                    <td>xxx</td>
                    <td>长沙</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>

          <div class="row">
            <div class="col-md-4">
              <form class="form-horizontal">
                <div class="form-group">
                  <label class="control-label col-md-4">拦标价总价</label>
                  <div class="col-md-8">
                    <div class="input-group">
                      <span class="input-group-addon">￥</span>
                      <input type="text" class="form-control">
                      <span class="input-group-addon">元</span>
                    </div>
                  </div>
                </div>
              </form>
            </div>
            <div class="col-md-6">
              <div class="pull-left">
                <button type="button" class="btn btn-primary">
                  下载模板
                </button>
                <button type="button" class="btn btn-primary">
                  上传文件，批量设置
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="box-footer">
          <div class="row">
            <div class="col-md-2 col-md-offset-2">
              <button type="button" class="btn btn-block btn-primary">
                提交
              </button>
            </div>
            <div class="col-md-2">
              <button type="button" class="btn btn-block btn-default" @click="$router.go(-1)">
                  返回
                </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <div class="box box-solid">
        <div class="box-header with-border">
          <i class="fa fa-text-width"></i>
          <h3 class="box-title">备注</h3>
        </div>
        <div class="box-body">
          <ol>
            <li>拦标价两种方式，如果设置了单个产品的拦标价，就不需要设置拦标价总价（程序换算出来）</li>
          </ol>
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
