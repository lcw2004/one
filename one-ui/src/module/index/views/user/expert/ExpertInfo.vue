<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="nav-tabs-custom">
          <ul class="nav nav-tabs">
            <li :class="{active : step == 1}" @click="step = 1"><a>基础信息</a></li>
            <li :class="{active : step == 2}" @click="step = 2"><a>资质文件</a></li>
            <li :class="{active : step == 3}" @click="step = 3"><a>项目记录</a></li>
          </ul>
          <div class="tab-content">
            <div class="tab-pane active">
              <BaseInfo v-if="step == 1"></BaseInfo>
              <Qualifications v-if="step == 2"></Qualifications>
              <Biddings v-if="step == 3"></Biddings>
            </div>
          </div>
          <div class="box-footer">
            <div class="col-md-4 col-sm-0"></div>
            <div class="col-md-2 col-sm-2">
              <button class="btn btn-block btn-default" @click="$router.go(-1)">返回</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
  import BaseInfo from './info/BaseInfo'
  import Biddings from './info/Biddings'
  import Qualifications from './info/Qualifications'

  export default {
    components: {
      BaseInfo,
      Biddings,
      Qualifications
    },
    data: () => {
      return {
        step: 1
      }
    }
  }
</script>
