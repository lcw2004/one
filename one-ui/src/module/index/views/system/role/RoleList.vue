<template>
  <section class="content">
    <div class="box">
      <div class="box-body">
        <div class="row row-margin-bottom">
          <div class="col-md-12">
            <form class="form-inline">
              <div class="col-md-6">
                <label class="control-label">名称</label>
                <input class="form-control inline-block" type="text" placeholder="角色名称" v-model="param.roleName">
              </div>
              <div class="col-md-6">
                <div class="pull-right">
                  <router-link to="/system/role/add" class="btn btn-primary">添加</router-link>
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
                <th style="width: 10px">#</th>
                <th>角色名称</th>
                <th>数据范围</th>
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
                <td>{{ obj.remarks }}</td>
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
  import PageMixin from '../../../../../common/mixins/PageMixin.js'

  export default {
    mixins: [PageMixin],
    data: () => {
      return {
        actions: {
          list: {method: 'get', url: '/api/sys/role'},
          deleteData: {method: 'delete', url: '/api/sys/role{/id}'}
        },
        param: {
          roleName: ''
        }
      }
    },
    methods: {
      deleteData (id) {
        this.$confirm('确认删除吗？', () => {
          this.resource.deleteData({id: id}).then((response) => {
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
