<template>
  <div>
    <div class="row" v-if="!isShow">
      <div class="col-md-12">
        <div class="pull-right">
          <a @click="isShow = true">
            <p class="form-control-static">高级查询 <i class="fa fa-fw fa-chevron-down"></i></p>
          </a>
        </div>
      </div>
    </div>

    <div class="row" v-if="isShow">
      <div class="col-md-12" style="margin-top: 10px">
        <div class="col-md-4">
          <label class="control-label">法人姓名</label>
          <input type="text" placeholder="法人姓名" class="form-control inline-block">
        </div>
        <div class="col-md-4">
          <label class="control-label">法人身份证</label>
          <input type="text" placeholder="法人身份证" class="form-control inline-block">
        </div>
        <div class="col-md-4">
          <label class="control-label">信用编码</label>
          <input type="text" placeholder="姓名" class="form-control inline-block">
        </div>
      </div>
      <div class="col-md-12" style="margin-top: 10px">
        <div class="col-md-4">
          <label class="control-label">联系人姓名</label>
          <input type="text" placeholder="联系人姓名" class="form-control inline-block">
        </div>
        <div class="col-md-4">
          <label class="control-label">联系人身份证</label>
          <input type="text" placeholder="联系人身份证" class="form-control inline-block">
        </div>
        <div class="col-md-4">
          <label class="control-label">联系人账户</label>
          <input type="text" placeholder="联系人账户" class="form-control inline-block">
        </div>
      </div>
      <div class="col-md-12" style="margin-top: 10px">
        <div class="col-md-4">
          <label class="control-label">状态</label>
          <select class="form-control  inline-block">
            <option>全部</option>
            <option></option>
          </select>
        </div>
        <div class="col-md-4">
          <label class="control-label">注册时间</label>
          <TimeRangePicker></TimeRangePicker>
        </div>
      </div>
      <div class="col-md-12" style="margin-top: 10px">
        <div class="col-md-4">
          <label class="control-label">公司地址</label>
          <input type="text" placeholder="公司地址" class="form-control inline-block">
        </div>
        <div class="col-md-4">
          <label class="control-label">供货区域</label>
          <input type="text" placeholder="供货区域" class="form-control inline-block">
        </div>
      </div>
    </div>

    <div class="row" v-if="isShow">
      <div class="col-md-12">
        <div class="pull-right">
          <a @click="isShow = false">
            <p class="form-control-static">基本查询11111111111 <i class="fa fa-fw fa-chevron-up"></i></p>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
  },
  data: function () {
    return {
      isShow: false
    }
  },
  methods: {
  }
}
</script>
