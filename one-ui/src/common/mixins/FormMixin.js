/**
 */

let FormMixin = {
  data: () => {
    return {
      isClicked: false
    }
  },
  computed: {
    id: function () {
      return this.$route.params.id
    }
  },
  mounted () {
    this.resource = this.$resource(null, {}, this.actions)
    this.load()
  },
  methods: {
    load () {
      let id = this.$route.params.id
      if (id) {
        this.resource.get({id: id}).then((response) => {
          let result = response.body
          if (result.ok && result.data) {
            this.obj = result.data
          }
        })
      }
    },
    save () {
      // 防止重复点击
      if (this.isClicked) {
        return
      }
      this.isClicked = true

      let id = this.$route.params.id
      if (id) {
        this.resource.update(null, JSON.stringify(this.obj)).then((response) => {
          this.isClicked = false
          let result = response.body
          if (result.ok) {
            this.$notify.success('保存成功')
            this.$router.go(-1)
          }
        })
      } else {
        this.resource.save(null, JSON.stringify(this.obj)).then((response) => {
          this.isClicked = false
          let result = response.body
          if (result.ok) {
            this.$notify.success('添加成功')
            this.$confirm('添加成功，是否继续添加？', () => {
              if (this.clear) {
                this.clear()
              }
            }, () => {
              this.$router.go(-1)
            }, '继续添加', '返回')
          }
        })
      }
    },
    /**
    * 验证并保存，验证不通过则弹出提示框，不保存，每个 form 表单需要实现 validate 方法
    */
    validAndSave () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.save()
        }
      })
    }
  }
}

export default FormMixin
