import AddPurchaseTypeModal from './AddPurchaseTypeModal'

export default {
  components: {
    AddPurchaseTypeModal
  },

  props: {
    purchaseTypeList: {}
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
      this.purchaseTypeList.splice(index, 1)
    },
    addPurchaseType (purchaseType) {
      if (this.indexOf(purchaseType) === -1) {
        this.purchaseTypeList.push(purchaseType)
      }
    },
    indexOf (purchaseType) {
      let index = -1
      for (let i = 0; i < this.purchaseTypeList.length; i++) {
        let type = this.purchaseTypeList[i]
        if (type.purchaseTypeId === purchaseType.purchaseTypeId) {
          index = i
          break
        }
      }
      return index
    },
    handlerOk (purchaseType) {
      this.addPurchaseType(purchaseType)
    }
  }
}
