<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="nav-tabs-custom">
          <ul class="nav nav-tabs">
            <li :class="{active : step == 1}" @click="step = 1"><a>报价信息</a></li>
            <li :class="{active : step == 2}" @click="step = 2"><a>商务偏离</a></li>
            <li :class="{active : step == 3}" @click="step = 3"><a>技术偏离</a></li>
            <li :class="{active : step == 4}" @click="step = 4"><a>标书文件</a></li>
          </ul>
          <div class="tab-content">
            <div class="tab-pane active">
              <PriceInfo v-if="step == 1"></PriceInfo>
              <BusinessDeviation v-if="step == 2"></BusinessDeviation>
              <TechnicalDeviation v-if="step == 3"></TechnicalDeviation>
              <UploadFile v-if="step == 4"></UploadFile>
            </div>
          </div>
          <div class="box-footer">
            <div class="col-md-2 col-md-offset-2"><button class="btn btn-block btn-default" @click="$router.go(-1)">返回</button></div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import PriceInfo from './PriceInfo'
import BusinessDeviation from './BusinessDeviation'
import TechnicalDeviation from './TechnicalDeviation'
import UploadFile from './UploadFile'

export default {
  components: {
    PriceInfo,
    BusinessDeviation,
    TechnicalDeviation,
    UploadFile
  },
  data: function () {
    return {
      step: 1
    }
  },
  methods: {
  }
}
</script>
