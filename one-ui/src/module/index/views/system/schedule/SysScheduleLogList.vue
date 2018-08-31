<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row row-margin-bottom">
            <div class="col-md-4">
              <div class="pull-right">
                <router-link to="/system/scheduleLog/add" class="btn btn-primary">添加</router-link>
              </div>
            </div>
          </div>
        </form>
        <!--Query End-->

        <div class="row">
          <div class="col-md-12">
          	<div class="scroll-div">
	            <table class="table table-bordered table-hover">
	              <thead>
	                <tr>
	                  <th style="width: 10px">#</th>
                    <th>日志ID</th>
                    <th>调度时间</th>
                    <th>调用结果</th>
                    <th>任务调度ID</th>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr v-for="(obj, index) of page.list">
	                  <td>{{ index + 1 }}</td>
                    <td>{{ obj.logId }}</td>
                    <td>{{ obj.scheduleTime }}</td>
                    <td>{{ obj.result }}</td>
                    <td>{{ obj.scheduleId }}</td>
	                  <td>
	                    <router-link :to='"/system/scheduleLog/" + obj.logId + "/edit"'>修改</router-link>
	                    <a @click="deleteData(obj)">删除</a>
	                  </td>
	                </tr>
	              </tbody>
	            </table>
	          </div>
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
        list: {method: 'get', url: '/api/system/scheduleLog'}
      },
      param: {
      }
    }
  },
  methods: {
    deleteData (obj) {
      this.$confirm('确认删除吗？', () => {
        this.$api.sys.deleteSysScheduleLog(obj.logId).then((response) => {
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
