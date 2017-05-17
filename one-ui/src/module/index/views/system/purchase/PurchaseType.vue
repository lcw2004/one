<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <div class="pull-right">
        <a class="btn btn-primary" @click="toggleAll()"><span v-if="toggleStatus">收缩</span><span v-else>展开</span>全部</a>
        <router-link class="btn btn-primary" to="/system/purchaseType/add">添加</router-link>
      </div>
    </div>
    <div class="box-body">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th>名称</th>
            <th>编码</th>
            <th>备注</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="obj of dataList" v-show="obj.isShowInTable" :key="obj.id">
            <td>
              <TreeTableColPrefix :obj="obj" @toggle="toggle(obj)"></TreeTableColPrefix>
              <span @click="toggle(obj)" v-text="obj.name"></span>
            </td>
            <td><span v-text="obj.code"></span></td>
            <td></td>
            <td>
              <router-link :to='"/system/purchaseType/" + obj.id + "/form"'>修改</router-link>
              <a @click="deleteObj(obj)">删除</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</section>
</template>

<script>
import TreeTableMiXin from '../../../../../common/mixins/TreeTableMiXin'

export default {
  mixins: [TreeTableMiXin],
  data: function () {
    return {
      topElement: {}
    }
  },
  mounted () {
    let actions = {
      getMenuTree: { method: 'get', url: '/api/base/purchaseType/tree' },
      delete: { method: 'delete', url: '/api/base/purchaseType{/id}' }
    }
    this.resource = this.$resource(null, {}, actions)
    this.loadTreeTable()
  },
  methods: {
    loadTreeTable: function () {
      this.resource.getMenuTree().then(function (response) {
        let result = response.body
        if (result.ok) {
          this.topElement = result.data
        }
      })
    },
    deleteObj: function (obj) {
      this.$confirm('确认删除[ ' + obj.name + ' ]吗？', () => {
        this.resource.delete({id: obj.id}).then(function (response) {
          if (response.body.ok) {
            this.$notify.success('删除成功')
            this.removeElement(obj)
          }
        })
      })
    }
  }
}
</script>
