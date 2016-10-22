<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var actions = {
				get: {method: 'get', url: '${ctxRest}/sys/user/info'},
				save: {method: 'put', url: '${ctxRest}/sys/user/info'}
			};
			var resource;
			new Vue({
				el:"body",
				data : {
					obj : {}
				},
				ready: function () {
					resource = this.$resource(null, {}, actions);

					// 加载数据
					resource.get({id: id}).then(function (response) {
						this.obj = response.json();
					})
				},
				methods: {
					save : function () {
						resource.save(null, JSON.stringify(this.obj)).then(function (response) {
							var result = response.json();
							Vue.$alert(result.desc);
						})
					}
				}
			})
		});
	</script>
</head>
<body>
<section class="content-header">
	<h1>个人信息
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>个人信息</a></li>
		<li class="active">个人信息</li>
	</ol>
</section>

<form id="inputForm" class="form-horizontal">
<input type="hidden" id="id" value="1">
<section class="content">
	<div class="box box-info">
		<form class="form-horizontal">
			<div class="box-body">
				<div class="form-group">
					<label class="col-sm-2 control-label">归属公司</label>
					<div class="col-sm-4">
						<p class="form-control-static" v-text="obj.company.name"></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">归属部门</label>
					<div class="col-sm-4">
						<p class="form-control-static" v-text="obj.office.name"></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工号</label>
					<div class="col-sm-4">
						<p class="form-control-static" v-text="obj.no"></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">登录名</label>
					<div class="col-sm-4">
						<p class="form-control-static" v-text="obj.loginName"></p>
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
				<div class="form-group">
					<label class="col-sm-2 control-label">用户类型</label>
					<div class="col-sm-4">
						<p class="form-control-static">{{ obj.userType }}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户角色</label>
					<div class="col-sm-4">
						<p class="form-control-static">{{ obj.roleNames }}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">最后登陆</label>
					<div class="col-sm-4">
						<p class="form-control-static">IP: {{obj.loginIp}}&nbsp;&nbsp;&nbsp;&nbsp;时间：{{obj.loginDate}}</p>
					</div>
				</div>
			</div>
			<div class="box-footer">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a class="btn btn-primary" @click="save()">保存</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>

</form>
</body>
</html>