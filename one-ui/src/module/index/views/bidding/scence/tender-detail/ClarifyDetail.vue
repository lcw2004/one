<template>
  <div class="row">
    <div class="col-md-12">

      <div class="box box-solid">
        <div class="box-header">
          <h3 class="box-title">第 {{index}} 轮澄清
            <span v-if="status == 1" class="label label-default">已结束</span>
            <span v-if="status == 0" class="label label-warning">未结束</span>

            <span class="label label-default">已回应</span>
            <span class="text-muted">2017-01-01 15:00:00</span>
          </h3>
          <div class="box-tools pull-right">
            <button type="button" class="btn btn-default btn-sm" data-widget="collapse" @click="show = !show"><i class="fa" :class="{'fa-minus': isShow, 'fa-plus': !isShow}"></i></button>
          </div>
        </div>
        <div class="box-body" v-show="isShow">
          <form class="form-horizontal">
            <div class="row">
              <div class="col-md-12">
                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label col-md-4">澄清类型</label>
                      <div class="col-md-8">
                        <p class="form-control-static">公开(含价格澄清)</p>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label col-md-4">附件</label>
                      <div class="col-md-8">
                        <p class="form-control-static"><a><i class="fa fa-paperclip"></i> 下载</a></p>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label col-md-4">发布时间</label>
                      <div class="col-md-8">
                        <p class="form-control-static">2017-01-01 10:00:00</p>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label col-md-4">回应截止时间</label>
                      <div class="col-md-8">
                        <p class="form-control-static">2017-01-01 15:00:00</p>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-12">
                    <div class="form-group">
                      <label class="control-label col-md-2">说明</label>
                      <div class="col-md-9">
                        <p class="form-control-static">1111111111111111111111111111111111111111111111111</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['index', 'status'],
  data: function () {
    return {
      show: false
    }
  },
  computed: {
    isShow: function () {
      return this.show || this.status === '0'
    }
  }
}
</script>
