<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row row-margin-bottom">
            <div class="col-md-4">
              <div class="pull-right">
                <router-link to="/msg/msgTemplate/add" class="btn btn-primary">添加</router-link>
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
                  <th>消息模板ID</th>
                  <th>消息模板标题</th>
                  <th>消息内容</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
                </thead>
              <tbody>
                <tr v-for="(obj, index) of page.list">
                  <td>{{ index + 1 }}</td>
                  <td>{{ obj.templateId }}</td>
                  <td>{{ obj.title }}</td>
                  <td>{{ obj.content }}</td>
                  <td>{{ obj.status }}</td>
                  <td>
                    <router-link :to='"/msg/msgTemplate/" + obj.templateId + "/edit"'>修改</router-link>
                    <a @click="deleteData(obj)">删除</a>
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
        list: {method: 'get', url: '/api/msg/msgTemplate'}
      },
      param: {
      }
    }
  },
  methods: {
    deleteData (obj) {
      this.$confirm('确认删除吗？', () => {
        this.$api.msg.deleteMsgTemplate(obj.templateId).then((response) => {
          let result = response.data
          if (result.ok) {
            this.query()
            this.$notify.success('删除成功！')
          }
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
