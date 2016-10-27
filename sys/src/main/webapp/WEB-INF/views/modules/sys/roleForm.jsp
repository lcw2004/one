<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>角色信息</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var actions = {
				get: {method: 'get', url: '${ctxRest}/sys/role{/id}'},
				save: {method: 'post', url: '${ctxRest}/sys/role'},
				getMenuTree: {method: "get", url: '${ctxRest}/sys/menu/tree'}
			};
			var resource;
			new Vue({
				el: ".content",
				data : {
					obj: {},
					menu: {},

					// 模态窗属性
					companyTreeModalConfig: {
						show : false,
						title : "选择所属机构"
					}
				},
				mounted: function () {
					resource = this.$resource(null, {}, actions);

					// 加载数据
					this.loadTree();
				},
				methods: {
					loadData: function () {
						var id = $("#id").val();
						if (id) {
							resource.get({id: id}).then(function (response) {
								this.obj = response.body;

								// TODO 调用tree组件的init事件，由于设置obj值有延迟？广播事件的时候需要延迟一下。
								// TODO 但是为什么延迟时间为0也能生效，而去掉setTimeout则不能生效，这是什么鬼。
								var self = this;
								setTimeout(function () {
									self.$broadcast("init");
								}, 0);
							})
						} else {
							this.$set(this.obj, "menuIdList", []);
						}
					},
					save : function () {
						resource.save(null, JSON.stringify(this.obj)).then(function (response) {
							Vue.$alert("保存成功！");
						})
					},
					loadTree: function () {
						resource.getMenuTree().then(function (response) {
							this.menu = response.body;
							this.loadData();
						});
					}
				}
			})
		});
	</script>
</head>
<body>
<section class="content-header">
	<h1>角色信息
	</h1>
	<ol class="breadcrumb">
		<li><a><i class="fa fa-dashboard"></i>系统设置</a></li>
		<li><a href="${ctx}/sys/role"><i class="fa fa-dashboard"></i>角色列表</a></li>
		<li class="active">角色信息</li>
	</ol>
</section>
<form id="inputForm" class="form-horizontal">
	<input type="hidden" id="id" value="${id}">
	<section class="content">
		<div class="box box-info">
			<form class="form-horizontal">
				<div class="box-body">
					<div class="form-group">
						<label class="col-sm-2 control-label">归属机构</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control" v-model="obj.office.name"/>
								<span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="companyTreeModalConfig.show = true">选择</button>
								</span>
							</div>
							<office-tree-modal :config.sync="companyTreeModalConfig" :value.sync="obj.office"></office-tree-modal>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">角色名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.name"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">数据范围</label>
						<div class="col-sm-4">
							<select class="form-control" v-model="obj.dataScope">
								<c:forEach var="dict" items="${fns:getDictList('sys_data_scope')}">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							特殊情况下，设置为“按明细设置”，可进行跨机构授权
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">角色授权</label>
						<div class="col-sm-4">
							<tree :element="menu" :value.sync="obj.menuIdList" select-type="checkbox"></tree>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<a class="btn btn-primary" @click="save()">保存</a>
							<a class="btn btn-info" href="${ctx}/sys/role">返回</a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>
</form>
</body>
</html>