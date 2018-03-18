<template>
  
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
              <span aria-hidden="true">×</span>
            </button>
            <h4 class="modal-title">{{ config.title }}</h4>
          </div>
  
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="row">
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label col-md-4">父专业名称</label>
                    <div class="col-md-6">
                      <p class="form-control-static">工艺类</p>
                    </div>
                  </div>
                </div>
                <div class="col-md-6">
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label col-md-4">专业类别名称</label>
                    <div class="col-md-6">
                      <input type="text" class="form-control" placeholder="专业类别名称">
                    </div>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label col-md-4">专业类别编码</label>
                    <div class="col-md-8">
                      <div class="input-group">
                        <input type="text" class="form-control" placeholder="专业类别编码">
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-2">备注</label>
                    <div class="col-md-9">
                      <textarea class="form-control"></textarea>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
  
          <div class="modal-footer">
            <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">取消</button>
            <button type="button" class="btn btn-primary" @click="select()">确认</button>
          </div>
        </div>
      </div>
    </div>
  
</template>

<script>
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [ModalMixin]
}
</script>
