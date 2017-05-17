<template>
<div class="modal" v-show="config.show" style="display: block">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="config.show = false">
          <span aria-hidden="true">×</span>
        </button>
        <h4 class="modal-title" v-text="config.title"></h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label class="control-label col-md-4">标题</label>
                <div class="col-md-8">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="标题">
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <div class="form-group">
                <label class="control-label col-md-2">说明</label>
                <div class="col-md-9">
                  <Editor></Editor>
                </div>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <div class="form-group">
                <label class="control-label col-md-2">文件</label>
                <div class="col-md-9">
                  <button type="button" class="btn btn-primary"><i class="fa fa-paperclip"></i> 上传附件</button>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default " data-dismiss="modal" @click="config.show = false">取消</button>
        <button type="button" class="btn btn-primary" @click="ok()">确认</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  components: {},
  props: {
    // 模态窗属性配置
    config: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {}
  },
  methods: {
    ok: function () {

    }
  }
}
</script>
