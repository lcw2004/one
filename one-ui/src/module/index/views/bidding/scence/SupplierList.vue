<template>
  <div class="row">
    <div class="col-md-12">
      <div class="row" >
        <div class="col-md-12">
          <table class="table table-bordered table-hover">
            <tbody>
              <tr>
                <th style="width: 10px">#</th>
                <th>投标单位</th>
                <th>投标时间</th>
                <th>投标状态</th>
                <th>开标状态</th>
                <th>操作</th>
              </tr>
              <tr>
                <td>1</td>
                <td>投标单位1</td>
                <td>2017-01-01 11:11:11</td>
                <td><span class="label label-success">已投标</span></td>
                <td><span class="label label-success">开标成功</span></td>
                <td>
                  <a @click="supplierInvalidConfig.show = true">废标</a>
                  <router-link to='/bidding/scene/tender-detail'>投标详情</router-link>
                </td>
              </tr>
              <tr>
                <td>2</td>
                <td>投标单位2</td>
                <td>2017-01-01 11:11:11</td>
                <td><span class="label label-success">已投标</span></td>
                <td><span class="label label-success">开标成功</span></td>
                <td>
                  <a @click="supplierInvalidConfig.show = true">废标</a>
                  <router-link to='/bidding/scene/tender-detail'>投标详情</router-link>
                </td>
              </tr>
              <tr>
                <td>3</td>
                <td>投标单位3 </td>
                <td></td>
                <td><span class="label label-danger">未投标</span></td>
                <td><span class="label label-success">开标成功</span></td>
                <td>
                  <a @click="supplierInvalidConfig.show = true">废标</a>
                  <router-link to='/bidding/scene/tender-detail'>投标详情</router-link>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="row" >
        <div class="col-md-12">
          <div class="pull-right">
            <button type="button" class="btn btn-info" @click="config.show = true">开标</button>
            <router-link to='/bidding/scene/quotes' tag='button' class="btn btn-info">开标一览表</router-link>
            <router-link to='/bidding/scene/clarify' tag='button' class="btn btn-info">澄清与回应 <span class="badge">1</span></router-link>

            <button type="button" class="btn btn-primary disabled">
              结束澄清，进入授标
            </button>
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
              <p class="text-muted">1. 到开标时间之后才显示开标按钮</p>
              <p class="text-muted">2. 点击开标之后才出现"废标"、"开标一览表"、"澄清与回应"、"结束澄清，进入授标"按钮</p>
              <p class="text-muted">3. 低于3家提示不足3家是否开标</p>
              <p class="text-muted">4. 如果有澄清未关闭，在"澄清与回应"按钮上标识出来</p>
              <p class="text-muted">5. 如果有澄清未关闭，"结束澄清，进入授标"不可点击</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <OpenBiddingConfirm :config="config"></OpenBiddingConfirm>
    <AuditReject :config="supplierInvalidConfig"></AuditReject>
  </div>
</template>

<script>
import OpenBiddingConfirm from './OpenBiddingConfirm'
import AuditReject from '../../modal/AuditReject.vue'
export default {
  components: {
    OpenBiddingConfirm,
    AuditReject
  },
  data: function () {
    return {
      config: {
        show: false,
        title: '开标密码'
      },
      supplierInvalidConfig: {
        show: false,
        title: '废标'
      }
    }
  }
}
</script>
