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
                    <label class="control-label col-md-2">操作</label>
                    <div class="col-md-10">
                      <div class="controls">
                        <div class="radio ">
                          <label><input type="radio" value="1">转不合格供应商</label>
                        </div>
                        <div class="radio ">
                          <label><input type="radio" value="2">转合格供应商</label>
                        </div>
                        <div class="radio ">
                          <label><input type="radio" value="3">转战略合作供应商</label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-2">原因</label>
                    <div class="col-md-9">
                      <Editor :height="100"></Editor>
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
    methods: {
      ok () {
        this.close()
        this.$notify.success('提交成功')
      }
    }
  }
</script>
