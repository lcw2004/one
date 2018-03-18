<template>
  <div class="panel-contains">
    <div class="box box-solid">
      <div class="box-header">
        <h3 class="box-title">修改供应商等级</h3>
      </div>
      <div class="box-body">
        <form class="form-inline">
          <div class="form-group">
            <label class="control-label col-md-4">操作</label>
            <div class="col-md-8">
              <div class="controls">
                <div class="radio inline">
                  <label><input type="radio" value="1">转为合格供应商</label>
                </div>
                <div class="radio inline">
                  <label><input type="radio" value="2">转为不合格供应商</label>
                </div>
              </div>
            </div>
          </div>


        </form>
      </div>
      <div class="box-footer">
        <div class="row">
          <div class="col-md-6">
            <button type="button" class="btn btn-block btn-primary">
              确定
            </button>
          </div>
          <div class="col-md-6">
            <button type="button" class="btn btn-block btn-default">
              关闭
            </button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'RightPanel',
  components: {},
  data: function () {
    return {}
  },
  methods: {}
}
</script>

<style>
  .panel-contains {
    top: 50px;
    right: 0;
    width: 300px;
    z-index: 9999;
    height: 100%;
    position: fixed;
    background-color: #ffffff;
  }
</style>
