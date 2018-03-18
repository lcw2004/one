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
                    <div class="input-group">
                      <input type="text" class="form-control" v-model="obj.purchaseType == null ? '' : obj.purchaseType.name"/>
                      <span class="input-group-btn">
                        <button class="btn btn-info" type="button" @click="purchaseTreeModalConfig.show = true">选择</button>
                      </span>
                    </div>
                  </FormGroup>
                </div>
                <div class="col-md-6">
                  <FormGroup label="采购策略">
                    <DictSelect v-model="obj.type" type="base_materiel_type"></DictSelect>
                  </FormGroup>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6">
                  <FormGroup label="物料编号">
                    <input type="text" class="form-control" v-model="obj.id" maxlength="100" readonly>
                  </FormGroup>
                </div>
                <div class="col-md-6">
                  <FormGroup label="物料名称">
                    <input type="text" class="form-control" v-model="obj.name" maxlength="50" v-validate="'required'" name="物料名称">
                  </FormGroup>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6">
                  <FormGroup label="计量单位">
                    <input type="text" class="form-control" v-model="obj.unit" maxlength="20" v-validate="'required'" name="计量单位">
                  </FormGroup>
                </div>
                <div class="col-md-6">
                  <FormGroup label="规格型号">
                    <input type="text" class="form-control" v-model="obj.model" maxlength="20" v-validate="'required'" name="规格型号">
                  </FormGroup>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6">
                  <FormGroup label="标杆价格">
                    <input type="number" class="form-control" v-model="obj.price" maxlength="10">
                  </FormGroup>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <FormGroup label="备注" width="2">
                    <textarea class="form-control" rows="3" v-model="obj.remark" maxlength="200"></textarea>
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

      <SelectPurchaseTypeModal :config="purchaseTreeModalConfig" v-model="obj.purchaseType"/>
    </div>
  </OneTransition>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin'
import SelectPurchaseTypeModal from '../../modal/SelectPurchaseTypeModal.vue'

export default {
  mixins: [ModalMixin],
  components: {
    SelectPurchaseTypeModal
  },
  props: {
    materiel: {
      type: Object,
      required: true
    },
    purchaseType: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      purchaseTreeModalConfig: {
        show: false,
        title: '选择采购类别'
      },
      obj: {
        id: '',
        purchaseTypeId: '',
        type: 1,
        name: '',
        price: 0,
        unit: '',
        model: '',
        remark: '',
        purchaseType: {}
      }
    }
  },
  mounted () {
    if (this.config.isAdd) {
      this.obj.purchaseType = this.purchaseType
      this.genCode()
    } else {
      this.obj = this.materiel
    }
  },
  methods: {
    ok () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          if (this.config.isAdd) {
            this.$api.system.saveMateriel(this.obj).then((response) => {
              let result = response.data
              if (result.ok) {
                this.$notify.success('添加成功')
                this.close()
                this.$emit('ok')
              }
            })
          } else {
            this.$api.system.updateMateriel(this.obj).then((response) => {
              let result = response.data
              if (result.ok) {
                this.$notify.success('修改成功')
                this.close()
                this.$emit('ok')
              }
            })
          }
        }
      })
    },
    genCode () {
      if (this.obj.id === '') {
        this.$api.system.generateCode('020').then((response) => {
          let result = response.data
          if (result.ok) {
            this.obj.id = result.data
          }
        })
      }
    }
  }
}
</script>
