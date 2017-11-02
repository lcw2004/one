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
            <div class="row">
              <div class="col-md-12">
                <FormGroup label="驳回原因" width="2">
                  <textarea class="form-control" placeholder="请输入驳回原因" v-model="remark" v-validate="'required'" name="驳回原因" rows="4"
                            maxlength="100"></textarea>
                </FormGroup>
              </div>
            </div>
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
      },
      callback: {
        type: Function,
        required: true
      }
    },
    data: () => {
      return {
        remark: ''
      }
    },
    methods: {
      ok: function () {
        this.$validator.validateAll().then((result) => {
          if (result) {
            if (this.callback) {
              this.callback(this.remark)
            }
            this.close()
            this.remark = ''
          }
        })
      }
    }
  }
</script>
