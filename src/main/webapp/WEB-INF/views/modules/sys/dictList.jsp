<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		var v;
		$(document).ready(function () {
			var actions = {
				list: {method: 'get', url: '${ctxRest}/sys/dict?pageNo={pageNo}'}
			};

			v = new Vue({
				el : "body",
				data : {
					page : {}
				},
				ready: function () {
					this.query(1);
				},
				methods: {
					query: function (pageNo) {
						var resource = this.$resource(null, {}, actions);
						resource.list({pageNo: pageNo}).then(function (response) {
							this.page = response.json();
						});
					}
				}
			});


		})
	</script>
</head>
<body>
	<section class="content-header">
		<h1>字典列表
		</h1>
		<ol class="breadcrumb">
			<li><a><i class="fa fa-dashboard"></i>系统设置</a></li>
			<li class="active">字典列表</li>
		</ol>
	</section>
	<section class="content">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						//
						<h3 class="box-title">Hover Data Table</h3>
					</div>
					<div class="box-body">
						<table class="table table-bordered table-hover">
							<thead>
							<tr>
								<th>键值</th>
								<th>标签</th>
								<th>类型</th>
								<th>描述</th>
								<th>排序</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody>
							<tr v-for="obj of page.list">
								<td><span v-text="obj.value"></span></td>
								<td><span v-text="obj.label"></span></td>
								<td><span v-text="obj.type"></span></td>
								<td><span v-text="obj.description"></span></td>
								<td><span v-text="obj.sort"></span></td>
								<td>
									<a href="${ctx}/sys/dict/form?id={{obj.id}}">修改</a>
								</td>
							</tr>
							</tbody>
						</table>
						<pagination :page="page" :callback="query"></pagination>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@include file="/WEB-INF/views/include/component.jsp" %>
</body>
</html>