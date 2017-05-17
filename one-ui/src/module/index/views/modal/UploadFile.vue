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
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="control-label col-md-4">文件类型</label>
              <div class="col-md-6">
                <select class="form-control">
                  <option>模板 1</option>
                  <option>模板 2</option>
                  <option>模板 3</option>
                  <option>模板 4</option>
                  <option>模板 5</option>
                </select>
              </div>
            </div>
          </div>
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
                <textarea class="form-control"></textarea>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label class="control-label col-md-2">文件</label>
              <div class="col-md-9">
                <button type="button" class="btn btn-primary">上传文件文件</button>
              </div>
            </div>
          </div>
        </div>

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
  props: {
    // 模态窗属性配置
    config: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
    }
  },
  methods: {
    select: function () {

    }
  }
}
</script>
