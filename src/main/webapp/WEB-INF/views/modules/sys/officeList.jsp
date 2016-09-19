<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script src="${ctxStatic}/js/sys/menu.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			var actions = {
				getMenuTree: {method: "get", url: '${ctxRest}/sys/office/tree'},
				delete: {method: 'delete', url: '${ctxRest}/sys/office{/id}'}
			};
			var resource;
			new Vue({
				el: "body",
				data: {
					topMenu: {},
					dataList: [],

					toggleStatus: true
				},
				ready: function () {
					resource = this.$resource(null, {}, actions);
					this.loadTreeTable();
				},
				methods: {
					loadTreeTable: function () {
						resource.getMenuTree().then(function (response) {
							this.topMenu = response.json();

							if (this.topMenu) {
								var tempList = [];
								initMenu(this.topMenu, 1, tempList);
								this.dataList = tempList;
							}
						});
					},
					toggle: function (menu) {
						var isExpanded = menu.isExpanded;
						if (isExpanded) {
							toggleChildMenuListRecursion(menu, !isExpanded);
						} else {
							toggleChildMenuList(menu, !isExpanded);
						}
						menu.isExpanded = !isExpanded;
					},
					toggleAll: function () {
						toggleAllMenu(this.topMenu, !this.toggleStatus);
						this.toggleStatus = !this.toggleStatus;
					},
					deleteData: function (id) {
						resource.delete({id: id}).then(function (response) {
							this.loadTreeTable();
						});
					}
				}
			})
		});
	</script>
</head>
<body>
<section class="content-header">
	<h1>机构列表
	</h1>
	<ol class="breadcrumb">
		<li><a><i class="fa fa-dashboard"></i>系统设置</a></li>
		<li class="active">机构列表</li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<form class="form-inline">
						<div class="col-md-3">
							<a class="btn btn-primary" @click="toggleAll()" ><span v-if="toggleStatus">收缩</span><span v-else>展开</span>全部</a>
							<a class="btn btn-primary" href="${ctx}/sys/office/form">添加</a>
						</div>
					</form>
				</div>
				<div class="box-body">
					<table class="table table-bordered table-hover">
						<thead>
						<tr>
							<th>机构名称</th>
							<th>归属区域</th>
							<th>机构编码</th>
							<th>机构类型</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody>
						<tr v-for="obj of dataList" v-show="obj.isShowInTable" track-by="id">
							<td>
								{{{ obj.level | fillSpace }}}
								<a @click="toggle(obj)" v-if="obj.childList != null && obj.childList.length > 0">
									<i v-show="!obj.isExpanded" class="fa fa-caret-right"></i>
									<i v-show="obj.isExpanded" class="fa fa-caret-down"></i>
								</a>
								<i v-if="obj.icon" class="{{ obj.icon }}" style="font-size: 16px"></i>
								<span @click="toggle(obj)" v-text="obj.name"></span>
							</td>
							<td><span v-text="obj.area.name"></span></td>
							<td><span v-text="obj.code"></span></td>
							<td><span v-text="obj.typeCN"></span></td>
							<td><span v-text="obj.remarks"></span></td>
							<td>
								<a href="${ctx}/sys/office/form?id={{obj.id}}">修改</a>
								<a @click="deleteData(obj.id)">删除</a>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>

<%@include file="/WEB-INF/views/include/component.jsp" %>
</body>
</html>
