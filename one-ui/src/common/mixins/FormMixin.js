/**
* 分页mixin，主要功能如下：
*   1. 定义了pageNo\pageSize\page三个属性，其他地方不再需要定义一遍
*   2. 根据actions注入resource，引用方可以直接调用
*   3. 分页变化的时候自动调用 queryForPage 方法查询数据
*   4. 提供 query 接口供引用方调用，该方法会清除分页
*
* 使用：
*   1. 定义actions
*   2. 定义param，如果没有则不需要定义
*   3. 引入分页组件：<Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
*/

let FormMixin = {
  mounted: function () {
    this.resource = this.$resource(null, {}, this.actions)
    this.load()
  },
  methods: {
    load: function () {
      let id = this.$route.params.id
      if (id) {
        this.resource.get({id: id}).then(function (response) {
          var result = response.body
          if (result.ok && result.data) {
            this.obj = result.data
          }
        })
      }
    },
    save: function () {
      let id = this.$route.params.id
      if (id) {
        this.resource.update(null, JSON.stringify(this.obj)).then(function (response) {
          var result = response.body
          if (result.ok) {
            this.$notify.success('保存成功')
            this.$router.go(-1)
          }
        })
      } else {
        this.resource.save(null, JSON.stringify(this.obj)).then(function (response) {
          var result = response.body
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
    }
  }
}

export default FormMixin
