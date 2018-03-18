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
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="row">
                <div class="col-md-6">
                  <FormGroup label="采购类别">
                    <div class="input-group" @click="purchaseTypeConfig.show = true">
                      <input type="text" class="form-control" v-model="purchaseTypeName" v-validate="'required'" name="采购类别" readonly>
                      <span class="input-group-btn">
                        <button type="button" class="btn btn-primary btn-flat">选择</button>
                      </span>
                    </div>
                  </FormGroup>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <FormGroup label="备注" width="2">
                    <textarea class="form-control" rows="3" v-model="obj.remark" maxLength="200"></textarea>
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

      <SelectPurchaseTypeModal :config="purchaseTypeConfig" v-model="obj.basePurchaseType"></SelectPurchaseTypeModal>
    </div>
  </OneTransition>
</template>

<script>
import SelectPurchaseTypeModal from '../../../system/modal/SelectPurchaseTypeModal'
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [ModalMixin],
  components: {
    SelectPurchaseTypeModal
  },
  data: function () {
    return {
      obj: {
        remark: '',
        basePurchaseType: {}
      },
      purchaseTypeConfig: {
        title: '选择投标类别',
        show: false
      }
    }
  },
  methods: {
    ok () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          let purchaseType = JSON.parse(JSON.stringify(this.purchaseType))
          purchaseType.purchaseTypeId = purchaseType.basePurchaseType.id
          this.$emit('ok', purchaseType)
          this.close()
          // 清除数据
          this.purchaseType.remark = ''
          this.purchaseType.basePurchaseType = {}
        }
      })
    }
  },
  computed: {
    purchaseType: function () {
      return this.obj
    },
    purchaseTypeName: function () {
      let basePurchaseTypeName = ''
      if (this.obj.basePurchaseType) {
        basePurchaseTypeName = this.obj.basePurchaseType.name
      }
      return basePurchaseTypeName
    }
  }
}
</script>
