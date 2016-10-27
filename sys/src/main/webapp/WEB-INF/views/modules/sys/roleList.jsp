<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script>
		$(document).ready(function () {
			var actions = {
				list: {method: 'get', url: '${ctxRest}/sys/role?pageNo={pageNo}'},
				delete: {method: 'delete', url: '${ctxRest}/sys/role{/id}'}
			};

			var resource;
			new Vue({
				el: ".content",
				data : {
					param: {pageNo: 0, pageSize: 10, name: ""},
					page : {}
				},
				mounted: function () {
					resource = this.$resource(null, {}, actions);

					// 设置页码
					this.param.pageNo = 1;
				},
				methods: {
					query : function () {
						resource.list(this.param).then(function (response) {
							this.page = response.body;
						});
					},
					deleteData : function (id) {
						Vue.$confirm("确认删除吗？", function() {
							resource.delete({id : id}).then(function (response) {
								this.query();
								Vue.$alert("删除成功！");
							});
						});
					}
				},
				watch: {
					'param': {
						handler: function () {
							// 监听查询条件对象，如果有更改就查询数据
							this.query();
						},
						deep: true
					}
				}
			});
		});

	</script>
</head>
<body>
	<section class="content-header">
		<h1>角色列表
		</h1>
		<ol class="breadcrumb">
			<li><a href="${ctx}/sys/role"><i class="fa fa-dashboard"></i>系统设置</a></li>
			<li class="active">角色列表</li>
		</ol>
	</section>
	<section class="content">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<form class="form-inline">
							<div class="col-md-3">
								<label class="control-label">名称</label>
								<input class="form-control inline-block" type="text" placeholder="角色名称" v-model="param.name">
							</div>
							<div class="col-md-3">
								<a class="btn btn-primary" href="${ctx}/sys/role/form">添加</a>
							</div>
						</form>
					</div>
					<div class="box-body">
						<table class="table table-bordered table-hover">
							<thead>
							<tr>
								<th>角色名称</th>
								<th>归属机构</th>
								<th>数据范围</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody>
							<tr v-for="obj of page.list">
								<td><span v-text="obj.name"></span></td>
								<td><span v-text="obj.office.name"></span></td>
								<td><span v-text="obj.sort"></span></td>
								<td>
									<a href="'${ctx}/sys/role/form?id=' + obj.id">修改</a>
									<a @click="deleteData(obj.id)">删除</a>
								</td>
							</tr>
							</tbody>
						</table>

						<pagination :page="page"  :page-no.sync="param.pageNo" :page-size.sync="param.pageSize"></pagination>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>