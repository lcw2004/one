<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function () {
			var actions = {
				list: {method: 'get', url: '${ctxRest}/sys/user'},
				listType: {method: 'get', url: '${ctxRest}/sys/user/type'},
				delete: {method: 'delete', url: '${ctxRest}/sys/user{/id}'}
			};

			var resource;
			new Vue({
				el : "body",
				data : {
					param: {pageNo: 0},
					page : {}
				},
				ready: function () {
					resource = this.$resource(null, {}, actions);

					// 设置页码
					this.param.pageNo = 1;
				},
				methods: {
					query : function () {
						resource.list(this.param).then(function (response) {
							this.page = response.json();
						});
					},
					deleteData : function (id) {
						resource.delete({id : id}).then(function (response) {
							alert("删除成功！");
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
	<h1>用户列表
	</h1>
	<ol class="breadcrumb">
		<li><a href="${ctx}/sys/user"><i class="fa fa-dashboard"></i>系统设置</a></li>
		<li class="active">用户列表</li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<form class="form-inline">
						<div class="col-md-3">
							<label class="control-label">姓名</label>
							<input class="form-control inline-block" type="text" placeholder="姓名" v-model="param.name">
						</div>
						<div class="col-md-3">
							<a class="btn btn-primary" @click="query()" >查询</a>
							<a class="btn btn-primary" href="${ctx}/sys/user/form">添加</a>
						</div>
					</form>
				</div>
				<div class="box-body">
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
								<a href="${ctx}/sys/user/form?id={{obj.id}}">修改</a>
								<a @click="deleteData(obj.id)">删除</a>
							</td>
						</tr>
						</tbody>
					</table>

					<pagination :page="page"  :page-no.sync="param.pageNo"></pagination>
				</div>
			</div>
		</div>
	</div>
</section>

<%@include file="/WEB-INF/views/include/component.jsp" %>
</body>
</html>