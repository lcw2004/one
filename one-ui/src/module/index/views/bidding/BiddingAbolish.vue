<template>
<section class="content">
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header">
          <div class="row">
            <div class="col-md-4">
              <div class="input-group margin">
                <input type="text" class="form-control" placeholder="项目编号">
                <span class="input-group-btn">
                    <button type="button" class="btn btn-danger" @click="show = true">确定</button>
                  </span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="input-group margin">
                <p class="text-red">随意改动项目时间可能引起项目运行异常，请谨慎使用</p>
              </div>
            </div>
          </div>
        </div>

        <div class="box-body" v-show="show">
          <form class="form-horizontal">
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-4">项目名称</label>
                  <div class="col-md-7">
                    <p class="form-control-static">xxxxxxxxxxxxxxxx</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
              </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="control-label col-md-2">流标原因</label>
                  <div class="col-md-8">
                      <textarea class="form-control" rows="5"></textarea>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>

        <div class="box-footer" v-show="show">
          <div class="col-md-2 col-md-offset-2">
            <button type="submit" class="btn btn-block btn-primary">提交</button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <div class="box box-solid">
        <div class="box-header with-border">
          <i class="fa fa-text-width"></i>
          <h3 class="box-title">备注</h3>
        </div>
        <div class="box-body">
          <p class="text-muted">目前暂定修改以上几个项</p>
        </div>
      </div>
    </div>
  </div>
</section>
</template>


<script>
export default {
  components: {
  },
  data: function () {
    return {
      show: false
    }
  },
  methods: {
  }
}
</script>
