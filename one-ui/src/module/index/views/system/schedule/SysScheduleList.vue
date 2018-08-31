<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row row-margin-bottom">
            <div class="col-md-4">
              <div class="pull-right">
                <router-link to="/system/schedule/add" class="btn btn-primary">添加</router-link>
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
                    <th>任务调度ID</th>
                    <th>任务名称</th>
                    <th>Cron表达式</th>
                    <th>调用方式</th>
                    <th>调用目标</th>
                    <th>状态</th>
                    <th>任务描述</th>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr v-for="(obj, index) of page.list">
	                  <td>{{ index + 1 }}</td>
                    <td>{{ obj.scheduleId }}</td>
                    <td>{{ obj.name }}</td>
                    <td>{{ obj.expression }}</td>
                    <td>{{ obj.type }}</td>
                    <td>{{ obj.service }}</td>
                    <td>{{ obj.status }}</td>
                    <td>{{ obj.remark }}</td>
	                  <td>
	                    <router-link :to='"/system/schedule/" + obj.scheduleId + "/edit"'>修改</router-link>
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
        list: {method: 'get', url: '/api/system/schedule'}
      },
      param: {
      }
    }
  },
  methods: {
    deleteData (obj) {
      this.$confirm('确认删除吗？', () => {
        this.$api.sys.deleteSysSchedule(obj.scheduleId).then((response) => {
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
