<template>
  <OneModal :title="config.title" :show.sync="config.show">
    <form class="form-horizontal">
      <div class="row">
        <div class="col-md-10">
          <FormGroup label="字典数据名称">
            <input type="text" class="form-control" v-model="obj.label" maxlength="50" v-validate="'required'" name="字典数据名称">
          </FormGroup>
        </div>
        <div class="col-md-10">
          <FormGroup label="字典数据值">
            <input type="text" class="form-control" v-model="obj.value" maxlength="50" v-validate="'required'" name="字典数据值">
          </FormGroup>
        </div>
        <div class="col-md-10" v-if="data.dataId">
          <FormGroup label="排序号">
            <input type="text" class="form-control" v-model="obj.sort" maxlength="2" v-validate="'required'" name="排序号">
          </FormGroup>
        </div>
        <div class="col-md-10">
          <FormGroup label="备注">
            <textarea class="form-control" rows="3" v-model="obj.remark" maxlength="200"></textarea>
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
    dictId: {
      type: String,
      required: true
    },
    data: {
      type: Object
    }
  },
  data: function () {
    return {
      obj: {
        label: null,
        value: null,
        sort: null,
        remark: null
      }
    }
  },
  mounted () {
    if (this.data.dataId) {
      this.obj = this.data
    }
  },
  methods: {
    ok () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          if (this.data.dataId) {
            this.$overlay.start()
            this.$api.system.updateDictData(this.dictId, this.obj).then((response) => {
              this.$overlay.done()
              let result = response.data
              if (result.ok) {
                this.$emit('ok')
                this.close()
                this.$notify.success('修改成功')
              }
            })
          } else {
            this.$overlay.start()
            this.$api.system.saveDictData(this.dictId, this.obj).then((response) => {
              this.$overlay.done()
              let result = response.data
              if (result.ok) {
                this.$emit('ok')
                this.close()
                this.$notify.success('添加成功')
              }
            })
          }
        }
      })
    }
  }
}
</script>
