<template>
  <OneModal :title="config.title" :show.sync="config.show">
    <form class="form-horizontal">
      <div class="row">
        <div class="col-md-12">
          <FormGroup label="驳回原因" width="2">
            <textarea class="form-control" placeholder="请输入驳回原因" v-model="remark" v-validate="'required'" name="驳回原因" rows="4" maxlength="500"></textarea>
          </FormGroup>
        </div>
      </div>
    </form>

    <div class="modal-footer" slot="footer">
      <button type="button" class="btn btn-default " @click="close()">取消</button>
      <button type="button" class="btn btn-primary" @click="ok()">确认</button>
    </div>
  </OneModal>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [ModalMixin],
  props: {
    callback: {
      type: Function,
      required: true
    }
  },
  data: function () {
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
