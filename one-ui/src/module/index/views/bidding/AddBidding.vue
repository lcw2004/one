<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header with-border">
            <div class="col-md-12">
              <div class="puastep_tab">
                <a :class="{puastep_tab_now : step == 1}" @click="step = 1">1.基本信息</a>
                <span>&gt;</span>
                <a :class="{puastep_tab_now : step == 2}" @click="step = 2">2.标的信息</a>
                <span>&gt;</span>
                <a :class="{puastep_tab_now : step == 3}" @click="step = 3">3.投标单位</a>
                <span>&gt;</span>
                <a :class="{puastep_tab_now : step == 4}" @click="step = 4">4.招标概要</a>
                <span>&gt;</span>
                <a :class="{puastep_tab_now : step == 5}" @click="step = 5">5.投标须知</a>
                <span>&gt;</span>
                <a :class="{puastep_tab_now : step == 6}" @click="step = 6">6.标书文件</a>
                <span>&gt;</span>
                <a :class="{puastep_tab_now : step == 7}" @click="step = 7">7.投标文件</a>
              </div>
            </div>
          </div>
          <form class="form-horizontal">
            <BaseInfo v-if="step == 1"/>
            <SubjectInfo v-if="step == 2"/>
            <SupplierInfo v-if="step == 3"/>
            <SummaryInfo v-if="step == 4"/>
            <NoticeInfo v-if="step == 5"/>
            <BiaoShuFile v-if="step == 6"/>
            <TouBiaoFile v-if="step == 7"/>

            <div class="box-footer">
              <div class="col-md-4 col-sm-0"></div>
              <div class="col-md-2 col-sm-2">
                <button type="submit" v-show="step > 1" @click="lastStep" class="btn btn-block btn-default">上一步</button>
              </div>
              <div class="col-md-2 col-sm-2">
                <button type="submit" v-if="step < maxStep" @click="nextStep" class="btn btn-block btn-primary">下一步</button>
                <button type="submit" v-if="step == maxStep" @click="save" class="btn btn-block btn-primary">保存</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import BaseInfo from './edit/BaseInfo'
import SubjectInfo from './edit/SubjectInfo'
import SupplierInfo from './edit/SupplierInfo'
import SummaryInfo from './edit/SummaryInfo'
import NoticeInfo from './edit/NoticeInfo'
import BiaoShuFile from './edit/BiaoShuFile'
import TouBiaoFile from './edit/TouBiaoFile'

export default {
  components: {
    BaseInfo,
    SubjectInfo,
    SupplierInfo,
    SummaryInfo,
    NoticeInfo,
    BiaoShuFile,
    TouBiaoFile
  },
  data: function () {
    return {
      step: 1,
      maxStep: 7
    }
  },
  methods: {
    lastStep: function () {
      if (this.step > 1) {
        this.step = this.step - 1
      }
    },
    nextStep: function () {
      if (this.step < this.maxStep) {
        this.step = this.step + 1
      }
    },
    save: function () {
    }
  }
}
</script>

<style>
.puastep_tab {
    width: 100%;
    border-bottom: 1px solid #e5e5e5;
}
.puastep_tab:after {
    content: "";
    display: table;
    clear: both;
}
.puastep_tab a {
    float: left;
    font-size: 14px;
    color: #9c9c9c;
    cursor: default;
    padding: 0 20px 15px 20px;
}
.puastep_tab .puastep_tab_now {
    color: #3c8dbc;
    border-bottom: 3px solid #3c8dbc;
}
.puastep_tab span {
    float: left;
    font-size: 14px;
    color: #9c9c9c;
    cursor: default;
}
.puastep_box {
    margin: 20px 0 0 0;
}
</style>
