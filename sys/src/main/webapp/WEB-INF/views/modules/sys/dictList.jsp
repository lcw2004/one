<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function () {
			var actions = {
				list: {method: 'get', url: '${ctxRest}/sys/dict'},
				listType: {method: 'get', url: '${ctxRest}/sys/dict/type'},
				delete: {method: 'delete', url: '${ctxRest}/sys/dict{/id}'}
			};

			var resource;
			new Vue({
				el: ".content",
				data : {
					param: {pageNo: 0, pageSize: 10},
					page : {},
					dictTypeList : []
				},
				mounted: function () {
					resource = this.$resource(null, {}, actions);

					// 设置页码
					this.param.pageNo = 1;

					// 加载字典列表
					resource.listType().then(function (response) {
						this.dictTypeList = response.body;
					})
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
		<h1>字典列表
		</h1>
		<ol class="breadcrumb">
			<li><a href="${ctx}/sys/dict"><i class="fa fa-dashboard"></i>系统设置</a></li>
			<li class="active">字典列表</li>
		</ol>
	</section>
	<section class="content">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<form class="form-inline">
							<div class="col-md-3">
								<label class="control-label">类型</label>
								<select class="form-control inline-block" v-model="param.type">
									<option value="" selected>全部</option>
									<option v-for="dictType of dictTypeList">{{ dictType }}</option>
								</select>
							</div>
							<div class="col-md-3">
								<label class="control-label">描述</label>
								<input class="form-control inline-block" type="text" v-model="param.description">
							</div>
							<div class="col-md-3">
								<a class="btn btn-primary" @click="query()" >查询</a>
								<a class="btn btn-primary" href="${ctx}/sys/dict/form">添加</a>
							</div>
						</form>
					</div>
					<div class="box-body">
						<table class="table table-bordered table-hover">
							<thead>
							<tr>
								<th>类型</th>
								<th>描述</th>
								<th>标签</th>
								<th>键值</th>
								<th>排序</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody>
							<tr v-for="obj of page.list">
								<td><a @click="param.type = obj.type"><span v-text="obj.type"></span></a></td>
								<td><span v-text="obj.description"></span></td>
								<td><span v-text="obj.label"></span></td>
								<td><span v-text="obj.value"></span></td>
								<td><span v-text="obj.sort"></span></td>
								<td>
									<a :href="'${ctx}/sys/dict/form?id=' + obj.id">修改</a>
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