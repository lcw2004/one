<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function () {
			var actions = {
				list: {method: 'get', url: '${ctxRest}/sys/dict?pageNo={pageNo}&type={type}&description={description}'},
				listType: {method: 'get', url: '${ctxRest}/sys/dict/type'}
			};

			new Vue({
				el : "body",
				data : {
					param : {},
					page : {},
					dictTypeList : []
				},
				ready: function () {
					// 设置页码
					Vue.set(this.param, "pageNo", 1);

					// 加载字典列表
					var resource = this.$resource(null, {}, actions);
					resource.listType().then(function (response) {
						this.dictTypeList = response.json();
					})
				},
				methods: {
					setPageNo: function (pageNo) {
						this.param.pageNo = pageNo;
					},
					setDictType: function (type) {
						this.param.type = type;
					}
				},
				watch: {
					'param': {
						handler: function () {
							// 监听查询条件对象，如果有更改就查询数据
							var resource = this.$resource(null, {}, actions);
							resource.list(this.param).then(function (response) {
								this.page = response.json();
							});
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
			<li><a><i class="fa fa-dashboard"></i>系统设置</a></li>
			<li class="active">字典列表</li>
		</ol>
	</section>
	<section class="content">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<div class="col-xs-4">
							类型
							<select class="form-control" v-model="param.type">
								<option value="" selected>全部</option>
								<option v-for="dictType of dictTypeList">{{ dictType }}</option>
							</select>
						</div>
						<div class="col-xs-4">
							描述
							<input type="text" class="form-control" v-model="param.description">
						</div>
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
								<td><a @click="setDictType(obj.type)"><span v-text="obj.type"></span></a></td>
								<td><span v-text="obj.description"></span></td>
								<td><span v-text="obj.label"></span></td>
								<td><span v-text="obj.value"></span></td>
								<td><span v-text="obj.sort"></span></td>
								<td>
									<a href="${ctx}/sys/dict/form?id={{obj.id}}">修改</a>
								</td>
							</tr>
							</tbody>
						</table>

						<pagination :page="page"  v-bind:page-no.sync="param.pageNo"></pagination>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@include file="/WEB-INF/views/include/component.jsp" %>
</body>
</html>