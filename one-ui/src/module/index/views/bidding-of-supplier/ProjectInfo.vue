<template>
  <div class="box">
    <div class="box-header">
      <h3 class="box-title">项目信息</h3>
    </div>
    <div class="box-body">
      <div class="row">
        <div class="col-md-12">
          <h4 class="page-header">项目基本信息</h4>
        </div>
      </div>

      <form class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="control-label col-md-5">项目编号</label>
              <div class="col-md-7">
                <p class="form-control-static">ZB201701150003</p>
              </div>
            </div>
          </div>

          <div class="col-md-6">
            <div class="form-group">
              <label class="control-label col-md-5">项目名称</label>
              <div class="col-md-7">
                <p class="form-control-static">某某招标项目</p>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="control-label col-md-5">项目类型</label>
              <div class="col-md-7">
                <p class="form-control-static">公开招标</p>
              </div>
            </div>
          </div>

          <div class="col-md-6">
            <div class="form-group">
              <label class="control-label col-md-5">决标方式</label>
              <div class="col-md-7">
                <p class="form-control-static">价格决标</p>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="control-label col-md-5">执行策略</label>
              <div class="col-md-7">
                <p class="form-control-static">网上招标</p>
              </div>
            </div>
          </div>

          <div class="col-md-6">
            <div class="form-group">
              <label class="control-label col-md-5">报价类型</label>
              <div class="col-md-7">
                <p class="form-control-static">含税价</p>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="control-label col-md-5">是否唱标</label>
              <div class="col-md-7">
                <p class="form-control-static">是</p>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="control-label col-md-5">售标截止时间</label>
              <div class="col-md-7">
                <p class="form-control-static text-red">2017-03-05 16:00:00</p>
              </div>
            </div>
          </div>

          <div class="col-md-6">
            <div class="form-group">
              <label class="control-label col-md-5">投标截止时间</label>
              <div class="col-md-7">
                <p class="form-control-static text-red">2017-03-10 16:00:00</p>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-10">
            <div class="form-group">
              <label class="control-label col-md-3">开标时间</label>
              <div class="col-md-9">
                <p class="form-control-static">2017-03-11 16:00:00 到 2017-03-15 16:00:00</p>
              </div>
            </div>
          </div>
        </div>
      </form>

      <div class="row">
        <div class="col-md-12">
          <h4 class="page-header">标的列表</h4>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <table class="table table-bordered table-hover">
            <tbody>
              <tr>
                <th style="width: 10px">#</th>
                <th>名称</th>
                <th>数量</th>
                <th>计量单位</th>
                <th>规格型号</th>
                <th>交货地点</th>
              </tr>
              <tr>
                <td>1</td>
                <td>钢铁</td>
                <td>1000</td>
                <td>99.00</td>
                <td>吨</td>
                <td>长沙</td>
              </tr>
              <tr>
                <td>2</td>
                <td>钢铁</td>
                <td>1000</td>
                <td>88.05</td>
                <td>吨</td>
                <td>长沙</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <div class="box-footer">
      <div class="row">
        <div class="col-md-2">
          <button type="button" class="btn btn-block btn-default" @click="$router.go(-1)">
            返回
          </button>
        </div>
      </div>
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
