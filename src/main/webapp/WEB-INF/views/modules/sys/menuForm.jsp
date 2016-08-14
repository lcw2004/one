<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>菜单信息</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var actions = {
				get: {method: 'get', url: '${ctxRest}/sys/menu{/id}'},
				save: {method: 'post', url: '${ctxRest}/sys/menu'}
			};
			var resource;
			new Vue({
				el:"body",
				data : {
					obj : {},
					showHideDictList : [],

                    // 模态窗属性
					modalConfig: {
						show : false,
						title : "选择上级菜单"
					}
				},
				ready: function () {
					resource = this.$resource(null, {}, actions);

					// 加载数据
					var id = $("#id").val();
					if (id) {
						resource.get({id: id}).then(function (response) {
							this.obj = response.json();
						})
					}

					// TODO 优化，整个系统使用一个字典
					// 加载数据字典
					var me = this;
					getDictList(function (response) {
						me.showHideDictList = response.json();
					});
				},
				methods: {
					save : function () {
						resource.save(null, JSON.stringify(this.obj)).then(function (response) {
							window.location.href = "${ctx}/sys/menu";
						})
					}
				}
			})
		});
	</script>
</head>
<body>
<section class="content-header">
	<h1>菜单信息
	</h1>
	<ol class="breadcrumb">
		<li><a><i class="fa fa-dashboard"></i>系统设置</a></li>
		<li><a href="${ctx}/sys/menu"><i class="fa fa-dashboard"></i>菜单列表</a></li>
		<li class="active">菜单信息</li>
	</ol>
</section>
<form id="inputForm" class="form-horizontal">
	<input type="hidden" id="id" value="${id}">
	<section class="content">
		<div class="box box-info">
			<form class="form-horizontal">
				<div class="box-body">
					<div class="form-group">
						<label class="col-sm-2 control-label">ID</label>
						<div class="col-sm-4">
							<p class="form-control-static">${id}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">上级菜单</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.parent.name"/>
							<a class="btn btn-info" @click="modalConfig.show = true">选择</a>
							<menu-tree :config.sync="modalConfig" :value.sync="obj.parent"></menu-tree>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.name"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">链接</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.href"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">目标</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.target"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">排序</label>
						<div class="col-sm-4">
							<input type="number" class="form-control" v-model="obj.sort"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">图标</label>
							<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.icon"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">可见</label>
						<div class="col-sm-4">
							<div class="radio">
								<label v-for="dict of showHideDictList">
									<input type="radio" :value="dict.value" v-model="obj.isShow">
									{{ dict.label }}
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">权限标识</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.permission"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">备注</label>
						<div class="col-sm-4">
							<textarea class="form-control" v-model="obj.remarks"></textarea>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<div class="col-sm-offset-2 col-sm-2">
						<a class="btn btn-primary pull-left" @click="save()">保存</a>
						<a class="btn btn-info pull-left" href="${ctx}/sys/menu">返回</a>
					</div>
				</div>
			</form>
		</div>
	</section>
</form>

<%@include file="/WEB-INF/views/include/component.jsp" %>
</body>
</html>