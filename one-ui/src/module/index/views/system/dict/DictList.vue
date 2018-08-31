<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row margin-bottom-10">
            <div class="col-md-4">
              <FormGroup label="字典类型">
                <input class="form-control" type="text" v-model="param.type" maxlength="100">
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="字典名称">
                <input class="form-control" type="text" v-model="param.name" maxlength="100">
              </FormGroup>
            </div>
            <div class="col-md-4">
              <div class="pull-right">
                <router-link to="/system/dict/add" class="btn btn-primary">添加</router-link>
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
                  <th>字典类型</th>
                  <th>字典名称</th>
                  <th>字典值描述</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="obj of page.list">
                  <td>{{ obj.type }}</td>
                  <td>{{ obj.name }}</td>
                  <td><LongText :text="obj.remark" title="描述"></LongText></td>
                  <td>
                    <router-link :to='"/system/dict/" + obj.dictId + "/form"'>修改</router-link>
                    <router-link :to='"/system/dict/" + obj.dictId + "/data"'>数据</router-link>
                    <a @click="deleteData(obj.dictId)">删除</a>
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
        list: {method: 'get', url: '/api/sys/dict'}
      },
      param: {
        type: '',
        description: ''
      }
    }
  },
  methods: {
    deleteData (id) {
      this.$confirm('确认删除吗？', () => {
        this.$api.system.deleteDict(id).then((response) => {
          let result = response.data
          if (result.ok) {
            this.queryForPage()
            this.$notify.success('删除成功！')
          }
        })
      })
    }
  }
}
</script>
