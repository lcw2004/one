export default {

  data: function () {
    return {
      supplier: {}
    }
  },

  methods: {
    load () {
      this.$api.system.getCurrentSupplierInfo().then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.supplier = result.data
        }
      })
    },

    save () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.$api.system.updateUserSupplier(this.supplier).then((response) => {
            let result = response.data
            if (result.ok) {
              this.$notify.success('提交成功，请等待工作人员审核')
              this.$router.push('/')
            }
          })
        } else {
          this.scrollToError()
        }
      })
    }
  }
}
