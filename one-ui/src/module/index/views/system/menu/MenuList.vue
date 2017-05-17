<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <div class="row">
        <div class="col-md-12">
          <div class="pull-right">
            <a class="btn btn-primary" @click="toggleAll()"><span v-if="toggleStatus">收缩</span><span v-else>展开</span>全部</a>
            <router-link class="btn btn-primary" to="/system/menu/add">添加</router-link>
          </div>
        </div>
      </div>
    </div>
    <div class="box-body">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th>名称</th>
            <th>链接</th>
            <th>排序</th>
            <th>可见</th>
            <th>权限标识</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="obj of dataList" v-show="obj.isShowInTable" :key="obj.id">
            <td>
              <TreeTableColPrefix :obj="obj" @toggle="toggle(obj)">{{ obj.name }}</TreeTableColPrefix>
            </td>
            <td><span v-text="obj.href"></span></td>
            <td><span v-text="obj.sort"></span></td>
            <td><span v-text="obj.isShowCN"></span></td>
            <td><span v-text="obj.permission"></span></td>
            <td>
              <router-link :to='"/system/menu/" + obj.id + "/form"'>修改</router-link>
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
    var actions = {
      getMenuTree: { method: 'get', url: '/api/sys/menu/tree' },
      delete: { method: 'delete', url: '/api/sys/menu{/id}' }
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
      this.$confirm('确认删除菜单[ ' + obj.name + ' ]吗？', () => {
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
