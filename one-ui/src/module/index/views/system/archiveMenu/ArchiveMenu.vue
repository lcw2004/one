<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <div class="pull-right">
          <a class="btn btn-primary" @click="toggleAll()"><span v-if="toggleStatus">收缩</span><span v-else>展开</span>全部</a>
          <router-link class="btn btn-primary" to="/system/archiveMenu/add">添加</router-link>
        </div>
      </div>
      <div class="box-body">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th>名称</th>
              <th>视图</th>
              <th>备注</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="obj of dataList" v-show="obj.isShowInTable" :key="obj.id">
              <td>
                <TreeTableColPrefix :obj="obj" @toggle="toggle(obj)"></TreeTableColPrefix>
                <span @click="toggle(obj)">{{ obj.name }}</span>
              </td>
              <td>{{ obj.view }}</td>
              <td>{{ obj.remark }}</td>
              <td>
                <router-link :to='"/system/archiveMenu/" + obj.id + "/form"'>修改</router-link>
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
      topElement: {},
      purchaseType: {}
    }
  },
  mounted () {
    this.loadTreeTable()
  },
  methods: {
    loadTreeTable () {
      this.$api.system.getArchiveMenuTree().then((response) => {
        let result = response.data
        if (result.ok) {
          this.topElement = result.data
        }
      })
    },
    deleteData (obj) {
      this.$confirm('确认删除[ ' + obj.name + ' ]吗？', () => {
        this.$api.system.deleteArchiveMenu(obj.id).then((response) => {
          if (response.data.ok) {
            this.$notify.success('删除成功')
            this.removeElement(obj)
          }
        })
      })
    },
    supplierList (obj) {
      this.purchaseType = obj
      this.config.show = true
    }
  }
}
</script>
