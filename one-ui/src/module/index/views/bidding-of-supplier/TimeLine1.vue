<template>
<div class="box">
  <div class="box-header">
    <h3 class="box-title">参与进度(完成状态)</h3>
  </div>
  <div class="box-body">

    <TimeLine>
      <!-- 售标 -->
      <li>
        <i class="fa fa-cny bg-gray"></i>
        <div class="timeline-item">
          <span class="time"><i class="fa fa-clock-o"></i> 2017-03-04 12:05</span>
          <h3 class="timeline-header">缴纳标书费</h3>
        </div>
      </li>


      <!-- 下载标书 -->
      <li>
        <i class="fa fa-download bg-gray"></i>
        <div class="timeline-item">
          <span class="time"><i class="fa fa-clock-o"></i> 2017-03-04 12:05</span>
          <h3 class="timeline-header">下载标书</h3>
          <div class="timeline-footer">
            <a class="btn btn-primary btn-sm">下载标书</a>
          </div>
        </div>
      </li>

      <TimeLabel time="2017-03-05 16:00:00" remark="售标截止" background="bg-gray"></TimeLabel>

      <!-- 上传标书 -->
      <li>
        <i class="fa fa-upload bg-gray"></i>
        <div class="timeline-item">
          <span class="time"><i class="fa fa-clock-o"></i> 2017-03-04 12:05</span>
          <h3 class="timeline-header">上传投标文件</h3>
          <div class="timeline-footer">
            <a class="btn btn-primary btn-sm disabled">上传投标文件</a>
          </div>
        </div>
      </li>

      <TimeLabel time="2017-03-10 16:00:00" remark="开始开标" background="bg-gray"></TimeLabel>

      <!-- 上传标书 -->
      <li>
        <i class="fa fa-television bg-gray"></i>
        <div class="timeline-item">
          <div class="timeline-footer">
            <a class="btn btn-primary btn-sm">进入开标现场</a>
          </div>
        </div>
      </li>

      <TimeLabel time="2017-03-15 16:00:00" remark="结束开标" background="bg-gray"></TimeLabel>

      <!-- 中标公示 -->
      <li>
        <i class="fa fa-newspaper-o bg-blue"></i>
        <div class="timeline-item">
          <span class="time"><i class="fa fa-clock-o"></i> 2017-03-04 12:05</span>
          <h3 class="timeline-header">中标公示</h3>
        </div>
      </li>

      <!-- 废标 -->
      <li>
        <i class="fa fa-recycle bg-blue"></i>
        <div class="timeline-item">
          <span class="time"><i class="fa fa-clock-o"></i> 2017-03-04 12:05</span>
          <h3 class="timeline-header">废标</h3>
          <div class="timeline-body">
            <div class="row">
              <div class="col-md-12">
                <div class="callout callout-default">
                  <p class="text-red">因为什么什么什么什么原因废标</p>
                </div>
              </div>
            </div>
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
