<template>
  <section class="content">
    <div class="box box-primary">
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
              <th>排序号</th>
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
              <td>{{ obj.href }}</td>
              <td><input type="tel" class="form-control input-sm" style="width: 100px" v-model="obj.sort" @blur="updateSort(obj)"></td>
              <td><DictLabel type="show_hide" :value="obj.isShow"></DictLabel></td>
              <td>{{ obj.permission }}</td>
              <td>
                <router-link :to='"/system/menu/" + obj.id + "/form"'>修改</router-link>
                <a @click="deleteData(obj)">删除</a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </section>
</template>

<script>
import TreeTableMiXin from '@mixins/TreeTableMiXin'

export default {
  mixins: [TreeTableMiXin],
  data: function () {
    return {
      topElement: {}
    }
  },
  mounted () {
    this.loadTreeTable()
  },
  methods: {
    loadTreeTable () {
      this.$api.system.getMenuTree().then((response) => {
        let result = response.data
        if (result.ok) {
          this.topElement = result.data
        }
      })
    },
    deleteData (obj) {
      this.$confirm('确认删除菜单[ ' + obj.name + ' ]吗？', () => {
        this.$api.system.deleteMenu(obj.id).then((response) => {
          if (response.data.ok) {
            this.$notify.success('删除成功')
            this.removeElement(obj)
          }
        })
      })
    },
    updateSort (obj) {
      this.$api.system.updateMenuSort(obj.id, obj.sort).then((response) => {
        if (response.data.ok) {
          this.$notify.success('修改成功')
        }
      })
    }
  }
}
</script>
