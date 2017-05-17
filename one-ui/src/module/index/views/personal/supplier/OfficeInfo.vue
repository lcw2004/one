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
  import SupplierAddPurchaseTypeMixin from './SupplierAddPurchaseTypeMixin'
  import SupplierFormMixin from './SupplierFormMixin'

  import BaseInfoEdit from '../../user/supplier/info/BaseInfoEdit.vue'

  export default {
    mixins: [SupplierAddPurchaseTypeMixin, SupplierFormMixin],
    components: {
      BaseInfoEdit
    },
    data: function () {
      return {
        actions: {
          get: { method: 'get', url: '/api/supplierInfo' }
        },

        obj: {}
      }
    },
    mounted: function () {
      this.resource = this.$resource(null, {}, this.actions)
      this.load()
    },
    methods: {
      loadQualificationType () {
        this.resource.getQualificationType().then(function (response) {
          var result = response.body
          if (result.ok && result.data) {
            this.qualificationTypeList = result.data
          }
        })
      },

      load () {
        this.resource.get().then(function (response) {
          var result = response.body
          if (result.ok && result.data) {
            this.obj = result.data
          }
        })
      }
    }
  }
</script>
