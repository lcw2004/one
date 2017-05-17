<template>
<section class="content">
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header">
          <h3 class="box-title">投标信息</h3>
        </div>
        <div class="box-body">
          <div class="row">
            <div class="col-md-12">
              <div class="pull-right">
                <button type="button" class="btn btn-default" @click="$router.go(-1)">
                  返回
                </button>
              </div>
            </div>
          </div>

          <!-- 报价信息 -->
          <div class="row">
            <div class="col-md-12">
              <h4 class="page-header">报价信息</h4>
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
                    <td>10000</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>

          <!-- 标书文件 -->
          <div class="row">
            <div class="col-md-12">
              <h4 class="page-header">标书文件</h4>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover">
                <tbody>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>类型</th>
                    <th>标题</th>
                    <th>内容</th>
                    <th>文件</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>技术部分电子版</td>
                    <td>技术部分标书文件</td>
                    <td></td>
                    <td>123.pdf</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>商务部分电子版</td>
                    <td>商务部分标书文件</td>
                    <td></td>
                    <td>456.pdf</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>

          <!-- 商务偏离 -->
          <div class="row">
            <div class="col-md-12">
              <h4 class="page-header">商务偏离</h4>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover">
                <tbody>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>招标文件条目</th>
                    <th>商务条款</th>
                    <th>偏离</th>
                    <th>说明</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>1.1</td>
                    <td>xxxxxx</td>
                    <td>xxxx</td>
                    <td>xxxxx</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>

          <!-- 技术偏离 -->
          <div class="row">
            <div class="col-md-12">
              <h4 class="page-header">技术偏离</h4>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover">
                <tbody>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>招标文件条目</th>
                    <th>物料名称</th>
                    <th>偏离</th>
                    <th>说明</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>1.1</td>
                    <td>xxxxxx</td>
                    <td>xxxx</td>
                    <td>xxxxx</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
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
