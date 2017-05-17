<template>
<section class="content">
  <div class="box">
    <div class="box-body">
      <!-- 标题 -->
      <div class="row row-margin-bottom">
        <div class="col-md-12">
          <form class="form-inline">
            <div class="col-md-6">
							<label class="control-label">姓名</label>
							<input class="form-control inline-block" type="text" placeholder="姓名" v-model="param.name">
						</div>
            <div class="col-md-6">
              <div class="pull-right">
                <a class="btn btn-primary" @click="query()" >查询</a>
                <router-link to="/system/user/add" class="btn btn-primary">添加</router-link>
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
							<th>归属公司</th>
							<th>归属部门</th>
							<th>登录名</th>
							<th>姓名</th>
							<th>电话</th>
							<th>手机</th>
							<th>角色</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody>
						<tr v-for="obj of page.list">
						</td>
							<td><span v-text="obj.company.name"></span></td>
							<td><span v-text="obj.office.name"></span></td>
							<td><a><span v-text="obj.loginName"></span></a></td>
							<td><span v-text="obj.name"></span></td>
							<td><span v-text="obj.phone"></span></td>
							<td><span v-text="obj.mobile"></span></td>
							<td><span v-text="obj.roleNames"></span></td>
							<td>
                <router-link :to='"/system/user/" + obj.id + "/form"'>修改</router-link>
                <a @click="deleteData(obj.id)">删除</a>
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
export default {
  components: {},
  data: function () {
    return {
      param: {
        pageNo: 1,
        pageSize: 10
      },
      page: {},
      dictTypeList: []
    }
  },
  mounted () {
    let actions = {
      list: {method: 'get', url: '/api/sys/user'},
      delete: {method: 'delete', url: '/api/sys/user{/id}'}
    }
    this.resource = this.$resource(null, {}, actions)
    this.query()
  },
  methods: {
    query () {
      this.resource.list(this.param).then(function (response) {
        this.page = response.body
      })
    },
    deleteData (id) {
      let self = this
      self.$confirm('确认删除吗？', function () {
        self.resource.delete({id: id}).then(function (response) {
          self.query()
          self.$notify.success('删除成功！')
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
