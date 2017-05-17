<template>
<section class="content">
  <div class="box">
    <div class="box-body">
      <!-- 标题 -->
      <div class="row row-margin-bottom">
        <div class="col-md-12">
          <form class="form-inline">
            <div class="col-md-6">
              <label class="control-label">类型</label>
              <select class="form-control inline-block" v-model="param.type">
  							<option value="">全部</option>
  							<option v-for="dictType of dictTypeList" :value="dictType">{{ dictType }}</option>
  						</select>

              <label class="control-label">描述</label>
              <input class="form-control inline-block" type="text" v-model="param.description">
            </div>
            <div class="col-md-6">
              <div class="pull-right">
                <router-link to="/system/dict/add" class="btn btn-primary">添加</router-link>
              </div>
            </div>
          </form>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <table class="table table-bordered table-hover">
            <thead>
              <tr>
                <th>类型</th>
                <th>描述</th>
                <th>标签</th>
                <th>键值</th>
                <th>排序</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="obj of page.list">
                <td><a @click="param.type = obj.type"><span v-text="obj.type"></span></a></td>
                <td><span v-text="obj.description"></span></td>
                <td><span v-text="obj.label"></span></td>
                <td><span v-text="obj.value"></span></td>
                <td><span v-text="obj.sort"></span></td>
                <td>
                  <router-link :to='"/system/dict/" + obj.id + "/form"'>修改</router-link>
                  <a @click="deleteData(obj.id)">删除</a>
                </td>
              </tr>
            </tbody>
          </table>

          <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
        </div>
      </div>
    </div>
  </div>
</section>
</template>

<script>
import PageMixin from '../../../../../common/mixins/PageMixin.js'

export default {
  mixins: [PageMixin],
  data: function () {
    return {
      actions: {
        list: { method: 'get', url: '/api/sys/dict' },
        listType: { method: 'get', url: '/api/sys/dict/type' },
        delete: { method: 'delete', url: '/api/sys/dict{/id}' }
      },
      param: {
        type: '',
        description: ''
      },
      dictTypeList: []
    }
  },
  mounted () {
    this.loadDictType()
  },
  methods: {
    loadDictType () {
      this.resource.listType().then(function (response) {
        let result = response.body
        if (result.ok) {
          this.dictTypeList = result.data
        }
      })
    },
    deleteData (id) {
      // TODO 在confirm中注册this
      this.$confirm('确认删除吗？', () => {
        this.resource.delete({id: id}).then(function (response) {
          this.query()
          this.$notify.success('删除成功！')
        })
      })
    }
  },
  watch: {
    'param': {
      handler: function () {
        this.query()
      },
      deep: true
    }
  }
}
</script>
