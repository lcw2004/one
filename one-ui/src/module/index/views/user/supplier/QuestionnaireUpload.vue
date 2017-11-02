<template>
  <OneTransition>
    <div class="modal" v-show="config.show" style="display: block">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
              <span aria-hidden="true">×</span>
            </button>
            <h4 class="modal-title">{{ config.title }}</h4>
          </div>
          <div class="modal-body modal-scrollable">
            <form class="form-horizontal">
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-2">参与人</label>
                    <div class="col-md-8">
                      <div class="input-group">
                        <input type="text" class="form-control">
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-2">评价</label>
                    <div class="col-md-9">
                      <textarea class="form-control" rows="3"></textarea>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-2">附件</label>
                    <div class="col-md-9">
                      <button type="button" class="btn btn-primary"><i class="fa fa-paperclip"></i> 上传附件</button>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">取消</button>
            <button type="button" class="btn btn-primary" @click="ok()">确认</button>
          </div>
        </div>
      </div>
    </div>
  </OneTransition>
</template>

<script>
  import ModalMixin from 'mixins/ModalMixin.js'
  export default {
    mixins: [ModalMixin],
    props: {
      config: {
        type: Object,
        required: true
      }
    },
    data: () => {
      return {}
    },
    methods: {}
  }
</script>
