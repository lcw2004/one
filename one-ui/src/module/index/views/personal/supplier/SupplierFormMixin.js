export default {

  data: function () {
    return {
      supplier: {}
    }
  },

  methods: {
    load () {
      this.resource.get().then(function (response) {
        var result = response.body
        if (result.ok && result.data) {
          this.supplier = result.data
        }
      })
    },

    save () {
      this.$validator.validateAll().then(() => {
        this.resource.save(null, JSON.stringify(this.supplier)).then(function (response) {
          var result = response.body
          if (result.ok) {
            this.$notify.success('提交成功，请等待工作人员审核')
            this.$router.push('/')
          }
        })
      }).catch(() => {
      })
    }
  }

}
