<template>
  <FixedContainer v-if="config.show">
    <div class="box box-primary box-solid ">
      <div class="box-header with-border">
        <h3 class="box-title">{{ config.title }}</h3>
        <div class="box-tools pull-right">
          <button type="button" class="btn btn-box-tool" @click="config.show = false"><i class="fa fa-times"></i></button>
        </div>
      </div>
      <div class="box-body">
        <div class="overlay" v-if="loading">
          <i class="fa fa-refresh fa-spin"></i>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label class="control-label col-md-4">项目编号</label>
              <div class="col-md-8">
              	ZB201701150003
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label class="control-label col-md-4">项目名称</label>
              <div class="col-md-8">
              	某某招标项目
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label class="control-label col-md-4">负责人</label>
              <div class="col-md-8">
              	系统管理员
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label class="control-label col-md-4">项目类型</label>
              <div class="col-md-8">
              		邀请招标
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label class="control-label col-md-4">售标时间</label>
              <div class="col-md-8">
                2017-01-15 到 2017-01-16
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label class="control-label col-md-4">投标截止时间</label>
              <div class="col-md-8">
              	2017-01-17 18:00:00
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label class="control-label col-md-4">开标时间</label>
              <div class="col-md-8">
              		2017-01-17 18:00:00
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="box-footer">
        <button type="button" class="btn btn-primary">
          查看详情
        </button>
      </div>
    </div>
  </FixedContainer>
</template>

<script>
export default {
  props: {
    config: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      loading: true
    }
  },
  mounted () {
    setTimeout(() => { this.loading = false }, 3000)
  },
  methods: {
  }
}
</script>
