/**
 * 需要提供两个data:
 *  1.  form.actions：操作数据的三个接口（查询、修改、新建）
 *  2.  form.continue：保存成功之后是否继续添加
 *  2.  obj: form表单需要维护的对象
 *
 * Mixin提供了两个方法：
 *  1.  save 直接保存
 *  2.  validAndSave 验证并保存
 *
 * 示例：
 * {
 *  mixins: [FormMixin],
 *  data: function () {
      return {
        form: {
          actions: {
            get: this.$api.system.getRole,
            save: this.$api.system.saveRole,
            update: this.$api.system.updateRole
          },
          continue: false
        },
        obj: {
          office: {},
          name: '',
          dataScope: '',
          remarks: '',
          isDefault: 0,
          sysMenuEOIdList: []
        }
      }
    }
 * }
 * <a class="btn btn-block btn-primary" @click="validAndSave()">保存</a>
 *
 */

let FormMixin = {
  data: function () {
    return {
      loading: false
    }
  },

  computed: {
    id: function () {
      return this.$route.params.id
    }
  },

  mounted () {
    this.load()
  },

  methods: {
    /**
     * 加载数据
     */
    load () {
      if (this.id) {
        this.form.actions.get(this.id).then((response) => {
          const result = response.data
          if (result.ok && result.data) {
            this.obj = result.data
          }
        })
      }
    },

    /**
     * 保存数据
     */
    save () {
      // 防止重复点击
      if (this.loading) {
        return
      }
      this.loading = true

      this.form.actions.save(this.obj).then((response) => {
        this.loading = false
        const result = response.data
        if (result.ok) {
          this.$notify.success('添加成功')
          if (this.form.continue) {
            this.$confirm('添加成功，是否继续添加？', () => {
              if (this.clear) {
                this.clear()
              }
            }, () => {
              this.$router.go(-1)
            }, '继续添加', '返回')
          } else {
            this.$router.go(-1)
          }
        }
      })
    },

    /**
     * 修改数据
     */
    update () {
      // 防止重复点击
      if (this.loading) {
        return
      }
      this.loading = true

      this.form.actions.update(this.obj).then((response) => {
        this.loading = false
        const result = response.data
        if (result.ok) {
          this.$notify.success('修改成功')
          this.$router.go(-1)
        }
      })
    },

    /**
    * 验证并保存，验证不通过则弹出提示框，不保存，每个 form 表单需要实现 validate 方法
    */
    validAndSave () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          if (this.$route.params.id) {
            this.update()
          } else {
            this.save()
          }
        } else {
          this.scrollToError()
        }
      })
    }
  }
}

export default FormMixin
