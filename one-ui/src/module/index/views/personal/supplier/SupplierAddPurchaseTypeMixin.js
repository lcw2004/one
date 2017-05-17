import SelectPurchaseTypeModal from '../../system/modal/SelectPurchaseTypeModal'

export default {
  components: {
    SelectPurchaseTypeModal
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
        let purchaseTypePK = {
          purchaseTypeId: purchaseType.id,
          basePurchaseType: purchaseType
        }
        this.supplier.purchaseTypeList.push(purchaseTypePK)
      }
    },
    indexOf (purchaseType) {
      let index = -1
      for (var i = 0; i < this.supplier.purchaseTypeList.length; i++) {
        let type = this.supplier.purchaseTypeList[i]
        if (type.basePurchaseType.id === purchaseType.id) {
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
        if (this.purchaseType.id) {
          this.addPurchaseType(this.purchaseType)
          this.purchaseType = {}
        }
      },
      deep: true
    }
  }
}
