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
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-4">购买标书截止时间</label>
                  <div class="col-md-7">
                    <TimePicker/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-4">投标截止时间</label>
                  <div class="col-md-7">
                    <TimePicker/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-4">开标时间</label>
                  <div class="col-md-7">
                    <TimePicker/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-4">评标时间</label>
                  <div class="col-md-7">
                    <TimePicker/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-4">开标地点</label>
                  <div class="col-md-7">
                    <div class="input-group">
                      <input type="text" class="form-control">
                      <span class="input-group-btn">
                        <button type="button" class="btn btn-primary btn-flat">选择</button>
                      </span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
              </div>
            </div>
          </form>
        </div>

        <div class="box-footer" v-show="show">
          <div class="col-md-2 col-sm-0"></div>
          <div class="col-md-2 col-sm-2">
            <button type="submit" class="btn btn-block btn-primary">保存</button>
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
