<template>
<form class="form-horizontal">
  <h4 class="page-header">项目基本信息</h4>
  <div class="row">
    <div class="col-md-6">
      <FormGroup label="采购类别">
        <FormControllStatic>原材料</FormControllStatic>
      </FormGroup>
      <FormGroup label="项目编号">
        <FormControllStatic>1234567890</FormControllStatic>
      </FormGroup>
      <FormGroup label="项目名称">
        <FormControllStatic>什么什么招标项目</FormControllStatic>
      </FormGroup>
      <FormGroup label="采购部门">
        <FormControllStatic>某某采购部</FormControllStatic>
      </FormGroup>
      <FormGroup label="报名联系人">
        <FormControllStatic>张三</FormControllStatic>
      </FormGroup>
    </div>

    <div class="col-md-6">
      <FormGroup label="项目类型">
        <FormControllStatic>邀请招标</FormControllStatic>
      </FormGroup>
      <FormGroup label="决标方式">
        <FormControllStatic>专家决标</FormControllStatic>
      </FormGroup>
      <FormGroup label="执行策略">
        <FormControllStatic>网上招标</FormControllStatic>
      </FormGroup>
      <FormGroup label="是否唱标">
        <FormControllStatic>否</FormControllStatic>
      </FormGroup>
      <FormGroup label="报价类型">
        <FormControllStatic>含税价</FormControllStatic>
      </FormGroup>
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <FormGroup label="投标资格要求" width="2">
        <FormControllStatic>无</FormControllStatic>
      </FormGroup>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <FormGroup label="项目概况" width="2">
        <FormControllStatic>无</FormControllStatic>
      </FormGroup>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <FormGroup label="备注" width="2">
        <FormControllStatic>无</FormControllStatic>
      </FormGroup>
    </div>
  </div>

  <h4 class="page-header">招标概要</h4>
  <div class="row">
    <div class="col-md-6">
      <FormGroup label="正式发布时间">
        <FormControllStatic>2017-01-01 12:00:00</FormControllStatic>
      </FormGroup>
      <FormGroup label="投标截止时间">
        <FormControllStatic>2017-01-03 12:00:00</FormControllStatic>
      </FormGroup>
      <FormGroup label="标书售价">
        <FormControllStatic>￥ 300 元</FormControllStatic>
      </FormGroup>
    </div>
    <div class="col-md-6">
      <FormGroup label="购买标书截止时间">
        <FormControllStatic>2017-01-02 12:00:00</FormControllStatic>
      </FormGroup>
      <FormGroup label="开标时间">
        <FormControllStatic>2017-01-04 12:00:00 - 2017-01-05 12:00:00</FormControllStatic>
      </FormGroup>
      <FormGroup label="投标保证金">
        <FormControllStatic>￥ 0 元</FormControllStatic>
      </FormGroup>
    </div>
  </div>
</form>
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
