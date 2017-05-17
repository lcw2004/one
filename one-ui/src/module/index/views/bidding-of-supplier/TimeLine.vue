<template>
<div class="box">
  <div class="box-header">
    <h3 class="box-title">参与进度</h3>
  </div>
  <div class="box-body">

    <TimeLine>
      <!-- 售标 -->
      <li>
        <i class="fa fa-cny bg-blue"></i>
        <div class="timeline-item">
          <h3 class="timeline-header">缴纳标书费</h3>
          <div class="timeline-body">
            <p class="text-red">户名：某某股份有限公司</p>
            <p class="text-red">银行：工商银行下摄司支行</p>
            <p class="text-red">账号：12345678901234567</p>
            <p>支付完成后请<a class="btn btn-primary btn-sm">上传缴费凭证</a>，与我中心价格审核室张三（0731 - 99999999）联系，获取标书下载权限。</p>
            <div class="callout callout-warning">
              请在售标截止时间之前购买标书
            </div>
          </div>
        </div>
      </li>

      <!-- 投标保证金 -->
      <li>
        <i class="fa fa-cny bg-blue"></i>
        <div class="timeline-item">
          <h3 class="timeline-header">缴纳投标保证金</h3>
          <div class="timeline-body">
            <p class="text-red">户名：某某股份有限公司</p>
            <p class="text-red">银行：工商银行下摄司支行</p>
            <p class="text-red">账号：12345678901234567</p>
            <p>支付完成后请<a class="btn btn-primary btn-sm">上传缴费凭证</a>，与我中心价格审核室张三（0731 - 99999999）联系，获取投标权限。</p>
            <div class="callout callout-warning">
              请在售标截止时间之前上传投标保证金
            </div>
          </div>
        </div>
      </li>

      <!-- 问题与澄清 -->
      <li>
        <i class="fa fa-download bg-blue"></i>
        <div class="timeline-item">
          <h3 class="timeline-header">提问与澄清</h3>
          <div class="timeline-body">
            <p class="text-red">共有<span class="label label-danger">1</span>条澄清，<router-link to='/bidding/s/questions'>点击查看</router-link></p>
            <p class="text-red">共有<span class="label label-danger">1</span>条回答，<router-link to='/bidding/s/clarifies'>点击查看</router-link></p>
          </div>
          <div class="timeline-footer">
            <a class="btn btn-primary btn-sm" @click="config.show = true">提问</a>
          </div>
        </div>
      </li>

      <!-- 下载标书 -->
      <li>
        <i class="fa fa-download bg-blue"></i>
        <div class="timeline-item">
          <h3 class="timeline-header">下载标书</h3>
          <div class="timeline-footer">
            <a class="btn btn-primary btn-sm">下载标书</a>
          </div>
        </div>
      </li>

      <TimeLabel time="2017-03-05 16:00:00" remark="售标截止" background="bg-gray"></TimeLabel>

      <!-- 上传投标文件 -->
      <li>
        <i class="fa fa-upload bg-blue"></i>
        <div class="timeline-item">
          <h3 class="timeline-header">上传投标文件</h3>
          <div class="timeline-body">
            <div class="callout callout-danger">
              请在投标截止时间之前上传投标文件
            </div>
          </div>
          <div class="timeline-footer">
            <router-link class="btn btn-primary btn-sm" to="/bidding/s/submit-bidding">上传投标文件</router-link>
          </div>
        </div>
      </li>

      <TimeLabel time="2017-03-10 16:00:00" remark="开始开标"></TimeLabel>

      <!-- 上传标书 -->
      <li>
        <i class="fa fa-television bg-blue"></i>
        <div class="timeline-item">
          <div class="timeline-footer">
            <router-link class="btn btn-primary btn-sm" to="/bidding/s/scence">进入开标现场</router-link>
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
        </div>
      </li>

      <li>
        <i class="fa fa-clock-o bg-blue"></i>
        <div class="timeline-item">
          <div class="timeline-body">
          </div>
        </div>
      </li>
    </TimeLine>

  </div>

  <AskQuestion :config="config"></AskQuestion>
</div>
</template>

<script>
import AskQuestion from './clarify/AskQuestion'

export default {
  components: {
    AskQuestion
  },
  data: function () {
    return {
      config: {
        title: '提问',
        show: false
      }
    }
  },
  methods: {}
}
</script>
