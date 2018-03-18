<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">系统日志</h3>
      </div>
      <div class="box-body">
        <div class="row row-margin-bottom">
          <div class="col-md-12">
            <form class="form-inline" @keyup.enter="query()">
              <div class="form-group">
                <label class="control-label">URI</label>
                <input class="form-control inline-block" type="text" v-model="param.requestUri">
              </div>
              <div class="form-group">
                <label class="control-label">操作用户</label>
                <input class="form-control inline-block" type="text" v-model="param.createByName" placeholder="姓名/登录名">
              </div>
              <div class="form-group">
                <label class="control-label">时间</label>
                <div class='input-group date'>
                  <input type='text' class="form-control form-date" v-model="param.beginDate"/>
                  <span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
                </div>
                -
                <div class='input-group date'>
                  <input type='text' class="form-control form-date" v-model="param.endDate"/>
                  <span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
                </div>
              </div>
              <div class="form-group">
                <a class="btn btn-primary" @click="query()">查询</a>
              </div>
            </form>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>所在公司</th>
                  <th>所在部门</th>
                  <th>操作用户</th>
                  <th>URI</th>
                  <th>提交方式</th>
                  <th>操作者IP</th>
                  <th>创建时间</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="obj of page.list">
                  <td>{{ obj.createBy.company.name }}</td>
                  <td>{{ obj.createBy.office.name }}</td>
                  <td>{{ obj.createBy.name }}（{{ obj.createBy.loginName }}）</td>
                  <td>{{ obj.requestUri }}</td>
                  <td>{{ obj.method }}</td>
                  <td>{{ obj.remoteAddr }}</td>
                  <td>{{ obj.createDate }}</td>
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
export default {
  data: function () {
    return {
      param: {
        pageNo: 1,
        pageSize: 10,
        requestUri: '',
        createByName: '',
        beginDate: '',
        endDate: ''
      },
      page: {}
    }
  },
  mounted: function () {
    this.query()
  },
  methods: {
    query: function () {
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
