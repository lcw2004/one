<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户信息</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var actions = {
				get: {method: 'get', url: '${ctxRest}/sys/user{/id}'},
				save: {method: 'post', url: '${ctxRest}/sys/user'}
			};
			var resource;
			new Vue({
				el:"body",
				data : {
					obj : {},

					// 模态窗属性
					companyTreeModalConfig: {
						show : false,
						title : "选择所属机构"
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
				},
				methods: {
					save : function () {
						resource.save(null, JSON.stringify(this.obj)).then(function (response) {
						})
					}
				}
			})
		});
	</script>
</head>
<body>
<section class="content-header">
	<h1>用户信息
	</h1>
	<ol class="breadcrumb">
		<li><a><i class="fa fa-dashboard"></i>系统设置</a></li>
		<li><a href="${ctx}/sys/user"><i class="fa fa-dashboard"></i>用户列表</a></li>
		<li class="active">用户信息</li>
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
						<label class="col-sm-2 control-label">归属公司</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control" v-model="obj.company.name"/>
								<span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="companyTreeModalConfig.show = true">选择</button>
								</span>
							</div>
							<office-tree-modal :config.sync="companyTreeModalConfig" :value.sync="obj.company"></office-tree-modal>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">登录名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.loginName"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">工号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.no"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.name"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.email"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">电话</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.phone"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">手机</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.mobile"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">备注</label>
						<div class="col-sm-4">
							<textarea type="text" class="form-control" v-model="obj.remarks"></textarea>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<div class="col-sm-offset-2 col-sm-1">
						<a class="btn btn-primary pull-left" @click="save()">保存</a>
					</div>
					<div class="col-sm-1">
						<a class="btn btn-info pull-left" href="${ctx}/sys/user">返回</a>
					</div>
				</div>
			</form>
		</div>
	</section>
</form>

<%@include file="/WEB-INF/views/include/component.jsp" %>
</body>
</html>