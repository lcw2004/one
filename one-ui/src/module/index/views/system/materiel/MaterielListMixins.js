
export default {
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/base/materiel'}
      },
      param: {
        purchaseTypeId: '',
        name: ''
      },
      materielConfig: {
        show: false,
        title: '添加物料',
        isAdd: true
      },
      purchaseType: {},
      materiel: {}
    }
  },
  methods: {
    deleteData (obj) {
      this.$confirm('确认删除[ ' + obj.name + ' ]吗？', () => {
        this.$api.system.deleteMateriel(obj.id).then((response) => {
          if (response.data.ok) {
            this.query()
            this.$notify.success('删除成功')
          }
        })
      })
    },
    addData () {
      this.materielConfig.title = '添加物料'
      this.materielConfig.isAdd = true
      this.materielConfig.show = true
    },
    editData (obj) {
      this.materielConfig.title = '修改物料'
      this.materielConfig.isAdd = false
      this.materielConfig.show = true
      this.materiel = JSON.parse(JSON.stringify(obj))
    }
  }
}
