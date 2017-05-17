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
          <div class="form-group">
            <label class="control-label col-md-4">开标密码</label>
            <div class="col-md-8">
              <div class="input-group">
                <input type="text" class="form-control" >
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-12 col-md-offset-4">
              <p class="form-control-static text-red">警告：投标供应商数量少于3家</p>
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
    ok () {
      this.config.show = false
      this.$notify.success('开标成功')
    }
  }
}
</script>
