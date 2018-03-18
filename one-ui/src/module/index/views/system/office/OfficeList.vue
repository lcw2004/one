<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <div class="row">
          <div class="col-md-12">
            <div class="pull-right">
              <a class="btn btn-primary" @click="toggleAll()"><span v-if="toggleStatus">收缩</span><span v-else>展开</span>全部</a>
              <router-link class="btn btn-primary" to="/system/office/add">添加</router-link>
            </div>
          </div>
        </div>
      </div>
      <div class="box-body">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th>机构名称</th>
              <th>归属区域</th>
              <th>机构编码</th>
              <th>负责人</th>
              <th>电话</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="obj of dataList" v-show="obj.isShowInTable" :key="obj.id">
              <td>
                <TreeTableColPrefix :obj="obj" @toggle="toggle(obj)">{{ obj.name }}</TreeTableColPrefix>
              </td>
              <td>{{ obj.area.name }}</td>
              <td>{{ obj.code }}</td>
              <td>{{ obj.masterUserInfo == null ? '' : obj.masterUserInfo.name }}</td>
              <td>{{ obj.phone }}</td>
              <td>
                <router-link :to='"/system/office/" + obj.id + "/form"'>修改</router-link>
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
    loadTreeTable: function () {
      this.$api.system.getOfficeTree().then((response) => {
        let result = response.data
        if (result.ok) {
          this.topElement = result.data
        }
      })
    },
    deleteData: function (obj) {
      this.$confirm('确认删除机构[ ' + obj.name + ' ]吗？', () => {
        this.$api.system.deleteOffice(obj.id).then((response) => {
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
