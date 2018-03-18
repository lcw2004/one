<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
      <div class="modal-dialog">
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
                <div class="col-md-12">
                  <FormGroup label="参与人" width="2">
                    <button type="button" class="btn btn-primary" @click="selectManageUserConfig.show = true">
                      选择
                    </button>
                    <template v-for="user of obj.inquireUserList">
                      <span class="label label-info">{{ user.name }}</span>&nbsp;
                    </template>
                  </FormGroup>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <FormGroup label="评价" width="2">
                    <textarea class="form-control" rows="3" v-model="obj.remark" maxlength="200" v-validate="'required'" name="评价"></textarea>
                  </FormGroup>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <FormGroup label="调查附件" width="2">
                    <FileUpload type="doc,docx,pdf,rar,zip" :callback="uploadSuccess" :errorCallback="uploadError"></FileUpload>
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

      <SelectManageUserModal :config="selectManageUserConfig" v-model="obj.inquireUserList"></SelectManageUserModal>
    </div>
  </OneTransition>
</template>

<script>
import SelectManageUserModal from '../../modal/SelectManageUserModal.vue'
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [ModalMixin],
  components: {
    SelectManageUserModal
  },
  props: {
    supplier: {
      type: Object,
      require: true
    }
  },
  data: function () {
    return {
      obj: {
        supplierId: '',
        fileId: '',
        remark: '',
        inquireUserList: []
      },
      selectManageUserConfig: {
        show: false,
        title: '选择调查人员'
      }
    }
  },
  mounted () {
    this.$validator.attach('调查附件', 'required')
    this.$validator.attach('参与人', 'required')
  },
  methods: {
    ok () {
      this.$validator.validateAll({
        '参与人': this.obj.inquireUserList,
        '评价': this.obj.remark,
        '调查附件': this.obj.fileId
      }).then((result) => {
        if (result) {
          this.$api.user.saveUserSupplierInquire(this.supplier.supplierId, this.obj).then((response) => {
            let result = response.data
            if (result.ok) {
              this.$notify.success('保存成功')
              this.close()
              this.clear()
            }
          })
        }
      })
    },
    clear () {
      this.obj = {
        supplierId: '',
        fileId: '',
        remark: '',
        inquireUserList: []
      }
    },
    uploadSuccess (fileObj) {
      this.obj.fileId = fileObj.fileId
      this.errors.remove('调查附件')
      this.$validator.validateAll({'调查附件': this.obj.fileId})
    },
    uploadError (error) {
      this.errors.remove('调查附件')
      this.errors.add('调查附件', error, 'maxFileSize')
    }
  }
}
</script>
