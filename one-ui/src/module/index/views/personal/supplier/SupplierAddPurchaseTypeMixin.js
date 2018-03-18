import SupplierAddPurchaseTypeModal from './SupplierAddPurchaseTypeModal'

export default {
  components: {
    SupplierAddPurchaseTypeModal
  },

  data: function () {
    return {
      purchaseTypeConfig: {
        title: '选择投标类别',
        show: false
      },
      purchaseType: {}
    }
  },

  methods: {
    deletePurchaseType (index) {
      this.supplier.purchaseTypeList.splice(index, 1)
    },
    addPurchaseType (purchaseType) {
      if (this.indexOf(purchaseType) === -1) {
        this.supplier.purchaseTypeList.push(purchaseType)
      }
    },
    indexOf (purchaseType) {
      let index = -1
      for (let i = 0; i < this.supplier.purchaseTypeList.length; i++) {
        let type = this.supplier.purchaseTypeList[i]
        if (type.purchaseTypeId === purchaseType.purchaseTypeId) {
          index = i
          break
        }
      }
      return index
    }
  },

  watch: {
    'purchaseType': {
      handler: function () {
        if (this.purchaseType.purchaseTypeId) {
          this.addPurchaseType(this.purchaseType)
          this.purchaseType = {}
        }
      },
      deep: true
    }
  }
}
