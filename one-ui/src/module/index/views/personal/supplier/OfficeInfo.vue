<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box box-solid">
          <div class="box-header">
            <h3 class="box-title">机构信息</h3>
          </div>
          <div class="box-body">
            <BaseInfoEdit :obj="obj" :onSave="load"/>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import SupplierFormMixin from './SupplierFormMixin'
import BaseInfoEdit from '../../user/supplier/info/BaseInfoEdit'

export default {
  mixins: [SupplierFormMixin],
  components: {
    BaseInfoEdit
  },
  data: function () {
    return {
      obj: {}
    }
  },
  mounted: function () {
    this.load()
  },
  methods: {
    load () {
      this.$api.system.getCurrentSupplierInfo().then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.obj = result.data
        }
      })
    }
  }
}
</script>
