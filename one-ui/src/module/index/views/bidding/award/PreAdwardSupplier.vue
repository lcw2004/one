<template>
  <div class="box">
    <div class="box-header with-border">
      <h3 class="box-title">中标供应商</h3>
    </div>
    <div class="box-body">
      <form class="form-horizontal">
        <div class="form-group">
          <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
          <div class="col-sm-10">
            <p class="form-control-static text-red">{{ supplier.name }}</p>
          </div>
        </div>
        <div class="form-group">
          <label for="inputPassword3" class="col-sm-2 control-label">联系方式</label>
          <div class="col-sm-10">
            <p class="form-control-static">0731-88999988</p>
          </div>
        </div>
      </form>
    </div>
    <div class="box-footer">
      <button type="submit" class="btn btn-primary">提交审核</button>
      <button type="submit" class="btn btn-primary">报告预览</button>
      <button type="submit" class="btn btn-primary">导出Excel</button>
      <button type="submit" class="btn btn-primary">中标公告设置</button>
      <router-link to='/bidding/set-floor-price' tag="button" class="btn btn-primary">设置拦标价</router-link>
    </div>
  </div>
</template>

<script>
export default {
  props: ['supplier'],
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
