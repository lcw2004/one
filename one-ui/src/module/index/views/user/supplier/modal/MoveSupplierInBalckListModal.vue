<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
              <span aria-hidden="true">×</span>
            </button>
            <h4 class="modal-title" v-text="config.title"></h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="row row-margin-bottom">
                <div class="col-md-12">
                  确定将供应商 <span class="label label-primary">{{ supplier.name }}</span> 从移入黑名单中吗?
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <FormGroup label="拉黑原因" width="0">
                    <textarea class="form-control" rows="3" v-model="remark" maxlength="200" v-validate="'required'" name="拉黑原因" placeholder="请输入拉黑原因"></textarea>
                  </FormGroup>
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
import ModalMixin from '@mixins/ModalMixin.js'

export default {
  mixins: [ModalMixin],
  props: {
    supplier: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      level: 1,
      remark: ''
    }
  },
  methods: {
    ok () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.$api.user.moveInBlackList(this.supplier.supplierId, this.remark).then((response) => {
            let result = response.data
            if (result.ok) {
              this.$notify.success('操作成功')
              this.$emit('ok')
              this.close()
            }
          })
        }
      })
    }
  }
}
</script>
