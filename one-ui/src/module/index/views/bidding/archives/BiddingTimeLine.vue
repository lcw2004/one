<template>
  <div>
    <TimeLine>
      <TimeItem icon="fa fa-download" time="2017-01-01 12:05">
        <TimeItemHeader slot="header">
          <a>张三</a> 项目立项
        </TimeItemHeader>
        <TimeItemBody slot="body">
          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover">
                <tbody>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>审核人</th>
                    <th>审核时间</th>
                    <th>审核结果</th>
                    <th>备注</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>张三</td>
                    <td>2017-01-02 12:05</td>
                    <td><span class="label label-warning">驳回</span></td>
                    <td>因为什么什么驳回</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>张三</td>
                    <td>2017-01-02 13:05</td>
                    <td><span class="label label-success">通过</span></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <td>李四</td>
                    <td>2017-01-02 17:05</td>
                    <td><span class="label label-success">通过</span></td>
                    <td></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </TimeItemBody>
      </TimeItem>

      <TimeItem icon="fa fa-download" time="2017-01-04 12:05">
        <TimeItemHeader slot="header">
          <a>张三</a> 发布项目公告
        </TimeItemHeader>
      </TimeItem>

      <TimeItem icon="fa fa-download">
        <TimeItemHeader slot="header">
          供应商报名
        </TimeItemHeader>
        <TimeItemBody slot="body">
          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover">
                <tbody>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>供应商</th>
                    <th>报名时间</th>
                    <th>报名结果</th>
                    <th>备注</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>供应商1</td>
                    <td>2017-01-04 16:05</td>
                    <td><span class="label label-warning">驳回</span></td>
                    <td>因为什么什么原因驳回了供应商报名资格</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>供应商2</td>
                    <td>2017-01-04 16:05</td>
                    <td><span class="label label-success">通过</span></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>供应商3</td>
                    <td>2017-01-04 16:05</td>
                    <td><span class="label label-success">同意邀请</span></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>供应商3</td>
                    <td>2017-01-04 16:05</td>
                    <td><span class="label label-warning">拒绝邀请</span></td>
                    <td>因为什么什么原因不能参与项目</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </TimeItemBody>
      </TimeItem>

      <TimeItem icon="fa fa-download" time="2017-03-05 12:05">
        <TimeItemHeader slot="header">
          澄清与回应
        </TimeItemHeader>
        <TimeItemBody slot="body">
          <table class="table table-bordered table-hover">
            <tbody>
              <tr>
                <th style="width: 10px">#</th>
                <th>标题</th>
                <th>发布人</th>
                <th>发布时间</th>
                <th>附件</th>
                <th>阅读次数</th>
              </tr>
              <tr>
                <td>1</td>
                <td><a><router-link to="/bidding/clarify/clarify-info">关于某某项目的澄清</router-link></a></td>
                <td>李四</td>
                <td>2017-01-16 00:00:00</td>
                <td>
                  <a><i class="fa fa-paperclip"></i> 附件一（点击下载）</a>
                </td>
                <td>5</td>
              </tr>
            </tbody>
          </table>
        </TimeItemBody>
      </TimeItem>

      <TimeItem icon="fa fa-download" time="2017-03-09 11:15">
        <TimeItemHeader slot="header">
          <a>张三</a> 抽取专家
        </TimeItemHeader>
        <TimeItemBody slot="body">
          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover">
                <tbody>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>专家姓名</th>
                    <th>联系方式</th>
                    <th>抽取时间</th>
                    <th>抽取方式</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>专家1</td>
                    <td>12345678900</td>
                    <td>2017-03-09 16:00</td>
                    <td>自动抽取</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>专家2</td>
                    <td>12345678900</td>
                    <td>2017-03-09 16:00</td>
                    <td>自动抽取</td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <td>专家3</td>
                    <td>12345678900</td>
                    <td>2017-03-09 16:00</td>
                    <td>手动抽取</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </TimeItemBody>
      </TimeItem>

      <TimeItem icon="fa fa-download" time="2017-03-10 11:15">
        <TimeItemHeader slot="header">
          <a>张三</a> 录入标底
        </TimeItemHeader>
        <TimeItemBody slot="body">
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
                    <th>标底价</th>
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
                    <td>99.00</td>
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
                    <td>88.05</td>
                    <td>吨</td>
                    <td>xxx</td>
                    <td>长沙</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </TimeItemBody>
      </TimeItem>

      <TimeItem icon="fa fa-download" time="2017-03-10 11:15">
        <TimeItemHeader slot="header">
          <a>张三</a> 开标
        </TimeItemHeader>
        <TimeItemFooter slot="footer">
          <a class="btn btn-primary btn-sm">进入开标现场</a>
        </TimeItemFooter>
      </TimeItem>

      <TimeItem icon="fa fa-download" time="2017-03-10 11:15">
        <TimeItemHeader slot="header">
          <a>张三</a> 填写评标报告
        </TimeItemHeader>
      </TimeItem>

      <TimeItem icon="fa fa-download" time="2017-03-10 11:15">
        <TimeItemHeader slot="header">
          <a>张三</a> 授标
        </TimeItemHeader>
        <TimeItemBody slot="body">
          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover">
                <tbody>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>审核人</th>
                    <th>审核时间</th>
                    <th>审核结果</th>
                    <th>备注</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>张三</td>
                    <td>2017-01-02 12:05</td>
                    <td><span class="label label-warning">驳回</span></td>
                    <td>因为什么什么驳回</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>张三</td>
                    <td>2017-01-02 13:05</td>
                    <td><span class="label label-success">通过</span></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <td>李四</td>
                    <td>2017-01-02 17:05</td>
                    <td><span class="label label-success">通过</span></td>
                    <td></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </TimeItemBody>
      </TimeItem>

      <TimeItem icon="fa fa-download" time="2017-03-10 11:15">
        <TimeItemHeader slot="header">
          <a>张三</a> 发布中标公告
        </TimeItemHeader>
      </TimeItem>

      <TimeItem icon="fa fa-download" time="2017-03-10 11:15">
        <TimeItemHeader slot="header">
          <a>张三</a> 项目归档
        </TimeItemHeader>
      </TimeItem>

      <li>
        <i class="fa fa-clock-o bg-gray"></i>
      </li>
    </TimeLine>
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
