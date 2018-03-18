<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
              <span aria-hidden="true">×</span>
            </button>
            <h4 class="modal-title" v-text="config.title"></h4>
          </div>
          <div class="modal-body  modal-scrollable">
            <form class="form-horizontal">
              <div class="row">
                <div class="col-md-6">
                  <FormGroup label="模板标题">
                    <input type="text" class="form-control" v-model="template.name" maxlength="100" v-validate="'required'" name="模板标题">
                  </FormGroup>
                </div>
                <div class="col-md-6">
                  <FormGroup label="是否默认">
                    <div class="pull-left" style="margin-top: 6px;">
                      <OneSwitch v-model="template.isDefault"></OneSwitch>
                    </div>
                  </FormGroup>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <FormGroup label="模板内容" width="2">
                    <InlineEditor height="450px" v-model="template.content" v-validate="'required'" data-vv-value-path="innerValue" data-vv-name="模板内容"></InlineEditor>
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
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [ModalMixin],
  props: {
    template: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      onValue: 1,
      offValue: 0
    }
  },
  mounted () {

  },
  methods: {
    save () {
      let id = this.template.templateId
      if (id) {
        this.$api.system.updateTemplate(this.template).then((response) => {
          let result = response.data
          if (result.ok) {
            this.$notify.success('修改成功')
            this.close()
            this.$emit('ok')
          }
        })
      } else {
        this.$api.system.saveTemplate(this.template).then((response) => {
          let result = response.data
          if (result.ok) {
            this.$notify.success('添加成功')
            this.close()
            this.$emit('ok')
          }
        })
      }
    },
    ok () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.save()
        }
      })
    }
  }
}
</script>
