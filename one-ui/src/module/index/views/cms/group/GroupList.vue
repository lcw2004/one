<template>
  <section class="content">
    <div class="box">
      <div class="box-header">
        <div class="row">
          <div class="col-md-12">
            <div class="pull-right">
              <a class="btn btn-primary" @click="toggleAll()"><span v-if="toggleStatus">收缩</span><span v-else>展开</span>全部</a>
              <router-link class="btn btn-primary" to="/cms/group/add">添加</router-link>
            </div>
          </div>
        </div>
      </div>
      <div class="box-body">
        <table class="table table-bordered table-hover">
          <thead>
          <tr>
            <th>名称</th>
            <th>URL</th>
            <th>关键字</th>
            <th>备注</th>
            <th>排序号</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="obj of dataList" v-show="obj.isShowInTable" :key="obj.id">
            <td>
              <TreeTableColPrefix :obj="obj" @toggle="toggle(obj)">{{ obj.name }}</TreeTableColPrefix>
            </td>
            <td>{{ obj.url }}</td>
            <td>{{ obj.keyword }}</td>
            <td>{{ obj.remark }}</td>
            <td>{{ obj.orderIndex }}</td>
            <td>
              <router-link :to='"/cms/group/" + obj.id + "/form"'>修改</router-link>
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
        query: {method: 'get', url: '/api/cms/group/tree'},
        deleteData: {method: 'delete', url: '/api/cms/group/{/id}'}
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
        this.$confirm('确认删除栏目[ ' + obj.name + ' ]吗？', () => {
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
