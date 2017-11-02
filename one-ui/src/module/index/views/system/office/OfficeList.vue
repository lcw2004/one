<template>
  <section class="content">
    <div class="box">
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
  import TreeTableMiXin from '../../../../../common/mixins/TreeTableMiXin'
  import TreeTableColPrefix from '../../../../../common/components/tree/TreeTableColPrefix'

  export default {
    mixins: [TreeTableMiXin],
    components: {
      TreeTableColPrefix
    },
    data: () => {
      return {
        topElement: {}
      }
    },
    mounted () {
      let actions = {
        query: {method: 'get', url: '/api/sys/office/tree'},
        deleteData: {method: 'delete', url: '/api/sys/office{/id}'}
      }
      this.resource = this.$resource(null, {}, actions)
      this.loadTreeTable()
    },
    methods: {
      loadTreeTable: function () {
        this.resource.query().then((response) => {
          let result = response.body
          if (result.ok) {
            this.topElement = result.data
          }
        })
      },
      deleteData: function (obj) {
        this.$confirm('确认删除机构[ ' + obj.name + ' ]吗？', () => {
          this.resource.deleteData({id: obj.id}).then((response) => {
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
