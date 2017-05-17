<template>
  <div class="modal" v-show="config.show" style="display: block">
    <div class="modal-dialog">
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
              <div class="col-md-12">
                <div class="form-group">
                  <label class="control-label col-md-2">招标文件条目</label>
                  <div class="col-md-8">
                    <div class="input-group">
                      <input type="text" class="form-control" placeholder="招标文件条目">
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="control-label col-md-2">物料名称</label>
                  <div class="col-md-9">
                    <div class="input-group">
                      <input type="text" class="form-control" placeholder="物料名称">
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="control-label col-md-2">偏离</label>
                  <div class="col-md-9">
                    <textarea class="form-control" rows="3"></textarea>
                  </div>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="control-label col-md-2">说明</label>
                  <div class="col-md-9">
                    <textarea class="form-control" rows="3"></textarea>
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
  props: {
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
  }
}
</script>
