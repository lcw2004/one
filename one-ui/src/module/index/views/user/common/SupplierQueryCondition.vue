<template>
<div>
  <form class="form-horizontal query">
    <div class="row">
      <div class="col-md-4">
        <label class="control-label col-md-4">供应商</label>
        <div class="col-md-8">
          <input type=" text" class="form-control" placeholder="公司名/简称/曾用名称" v-model="param.supplierLikeName" />
        </div>
      </div>
      <div class="col-md-8">
        <div class="pull-right">
          <a @click="isShow = true" v-if="!isShow">
            <p class="form-control-static">高级查询 <i class="fa fa-fw fa-chevron-down"></i></p>
          </a>
          <a @click="isShow = false" v-if="isShow">
            <p class="form-control-static">基本查询 <i class="fa fa-fw fa-chevron-up"></i></p>
          </a>
        </div>
      </div>
    </div>

    <div class="row" v-if="isShow">
      <div class="col-md-4">
        <label class="control-label col-md-4">联系人</label>
        <div class="col-md-8">
          <input type=" text" class="form-control" placeholder="姓名/账号/手机号码/邮箱" v-model="param.principalUserLikeName" />
        </div>
      </div>
      <div class="col-md-4">
        <label class="control-label col-md-4">法人</label>
        <div class="col-md-8">
          <input type=" text" class="form-control" placeholder="姓名/身份证" v-model="param.legalPersonLikeName" />
        </div>
      </div>
      <div class="col-md-4">
        <label class="control-label col-md-4">信用代码</label>
        <div class="col-md-8">
          <input type=" text" class="form-control" placeholder="统一社会信用代码" v-model="param.officeCode" />
        </div>
      </div>

      <div class="col-md-4">
        <label class="control-label col-md-4">状态</label>
        <div class="col-md-8">
          <select class="form-control inline-block input-sm" v-model="param.supplierStatus">
            <option value="">全部</option>
            <option value="1">审核中</option>
            <option value="2">审核通过</option>
            <option value="3">审核驳回</option>
            <option value="4">已停用</option>
            <option value="5">黑名单</option>
            <option value="-1">已删除</option>
          </select>
        </div>
      </div>

      <div class="col-md-4">
        <label class="control-label col-md-4">注册时间</label>
        <div class="col-md-8">
          <div style="width: 100%">
            <TimeRangePicker :from="param.registryTimeFrom" :to="param.registryTimeTo"
              @from="param.registryTimeFrom = arguments[0]" @to="param.registryTimeTo = arguments[0]"/>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <label class="control-label col-md-4">公司地址</label>
        <div class="col-md-8">
          <input type=" text" class="form-control" placeholder="公司地址" v-model="param.officeAddress" />
        </div>
      </div>

      <div class="col-md-4">
        <label class="control-label col-md-4">供货区域</label>
        <div class="col-md-8">
          <input type=" text" class="form-control" placeholder="供货区域" v-model="param.supplyArea" />
        </div>
      </div>
    </div>
  </form>
</div>
</template>

<script>
export default {
  name: 'SupplierQueryCondition',
  components: {
  },
  data: function () {
    return {
      isShow: false,
      param: {
        supplierLikeName: '',
        legalPersonLikeName: '',
        principalUserLikeName: '',
        officeCode: '',
        supplierStatus: '',
        registryTimeFrom: '',
        registryTimeTo: '',
        officeAddress: '',
        supplyArea: ''
      }
    }
  },
  watch: {
    'param': {
      handler: function () {
        this.$emit('input', this.param)
      },
      deep: true
    }
  }
}
</script>
