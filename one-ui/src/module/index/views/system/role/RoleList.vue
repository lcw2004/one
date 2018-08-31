<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row margin-bottom-10">
            <div class="col-md-4">
              <FormGroup label="角色名称">
                <input class="form-control" type="text" placeholder="角色名称" v-model="param.roleName">
              </FormGroup>
            </div>
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
              <div class="pull-right">
                <router-link to="/system/role/add" class="btn btn-primary">添加</router-link>
              </div>
            </div>
          </div>
        </form>
        <!--Query End-->

        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>角色名称</th>
                  <th>数据范围</th>
                  <th>排序号</th>
                  <th>备注</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(obj, index) of page.list">
                  <td>{{ index + 1 }}</td>
                  <td>{{ obj.name }}</td>
                  <td>
                    <DictLabel type="sys_data_scope" :value="obj.dataScope"></DictLabel>
                  </td>
                  <td>{{ obj.orderIndex }}</td>
                  <td>{{ obj.remarks | limitLength(30) }}</td>
                  <td>
                    <router-link :to='"/system/role/" + obj.id + "/form"'>修改</router-link>
                    <a @click="deleteData(obj.id)" v-if="obj.isDefault == 0">删除</a>
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
import PageMixin from '@mixins/PageMixin'

export default {
  mixins: [PageMixin],
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/sys/role'}
      },
      param: {
        roleName: ''
      }
    }
  },
  methods: {
    deleteData (id) {
      this.$confirm('确认删除吗？', () => {
        this.$api.system.deleteRole(id).then((response) => {
          let result = response.data
          if (result.ok) {
            this.query()
            this.$notify.success('删除成功！')
          }
        })
      })
    }
  }
}
</script>
