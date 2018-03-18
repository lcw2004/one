<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <div class="row">
          <div class="col-md-12">
            <div class="pull-right">
              <a class="btn btn-primary" @click="toggleAll()"><span v-if="toggleStatus">收缩</span><span v-else>展开</span>全部</a>
              <router-link class="btn btn-primary" to="/system/area/add">添加</router-link>
            </div>
          </div>
        </div>
      </div>
      <div class="box-body">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th>区域名称</th>
              <th>区域编码</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="obj of dataList" v-show="obj.isShowInTable" :key="obj.id">
              <td>
                <TreeTableColPrefix :obj="obj" @toggle="toggle(obj)">{{ obj.name }}</TreeTableColPrefix>
              </td>
              <td>{{ obj.code }}</td>
              <td>
                <router-link :to='"/system/area/" + obj.id + "/form"'>修改</router-link>
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
      this.$api.system.getAreaTree().then((response) => {
        let result = response.data
        if (result.ok) {
          this.topElement = result.data
        }
      })
    },
    deleteData (obj) {
      this.$confirm('确认删除区域【' + obj.name + '】吗？', () => {
        this.$api.system.deleteArea(obj.id).then((response) => {
          if (response.data.ok) {
            this.$notify.success('删除成功')
            this.removeElement(obj)
          }
        })
      })
    }
  }
}
</script>
