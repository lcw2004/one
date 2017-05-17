<template>
<div class="box">
  <div class="box-header">
    <h3 class="box-title">参与进度</h3>
  </div>
  <div class="box-body">

    <TimeLine>
      <!-- 售标 -->
      <li class="done">
        <i class="fa fa-cny bg-blue"></i>
        <div class="timeline-item">
          <div class="timeline-body">
            <div class="row">
              <div class="col-md-12">
                <h4 class="page-header">缴纳标书费</h4>
                <p class="text-red">户名：湘潭电机股份有限公司</p>
                <p class="text-red">银行：工商银行下摄司支行</p>
                <p class="text-red">账号：1904103219100002331</p>
                <p>支付完成后请与我中心价格审核室伍涛涌（0731 - 58595724）联系，获取标书下载权限。</p>
                <p class="text-light-blue">请在售标截止时间之前购买标书</p>
              </div>
            </div>
          </div>
        </div>
      </li>

      <!-- 下载标书 -->
      <li class="done">
        <i class="fa fa-download bg-blue"></i>
        <div class="timeline-item">
          <div class="timeline-body">
            下载标书
          </div>
          <div class="timeline-footer">
            <a class="btn btn-primary btn-sm">下载标书</a>
          </div>
        </div>
      </li>

      <TimeLabel time="2017-03-05 16:00:00" remark="售标截止" background="bg-gray"></TimeLabel>

      <!-- 上传标书 -->
      <li>
        <i class="fa fa-upload bg-blue"></i>
        <div class="timeline-item">
          <div class="timeline-body">
            <div class="row">
              <div class="col-md-12">
                <p class="text-light-blue">请在投标截止时间之前上传标书</p>
              </div>
            </div>
          </div>
          <div class="timeline-footer">
            <a class="btn btn-primary btn-sm disabled">上传标书</a>
          </div>
        </div>
      </li>

      <TimeLabel time="2017-03-10 16:00:00" remark="开始开标"></TimeLabel>

      <!-- 上传标书 -->
      <li>
        <i class="fa fa-television bg-blue"></i>
        <div class="timeline-item">
          <div class="timeline-footer">
            <a class="btn btn-primary btn-sm">进入开标现场</a>
          </div>
        </div>
      </li>

      <TimeLabel time="2017-03-15 16:00:00" remark="结束开标"></TimeLabel>

      <!-- 开标结束 -->
      <li>
        <i class="fa fa-newspaper-o bg-blue"></i>
        <div class="timeline-item">
          <div class="timeline-body">
            <div class="row">
              <div class="col-md-12">
                <p class="text-light-blue">开标结束，请等待开标结果</p>
              </div>
            </div>
          </div>
          <div class="timeline-footer">
          </div>
        </div>
      </li>

      <li>
        <i class="fa fa-clock-o bg-gray"></i>
      </li>
    </TimeLine>

  </div>
</div>
</template>

<script>
export default {
  components: {},
  data: function () {
    return {}
  },
  methods: {}
}
</script>


<style>
.done > li :before {
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  width: 4px;
  background: #123456;
  left: 31px;
  margin: 0;
  border-radius: 2px;
}
</style>
