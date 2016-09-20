<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var actions = {
				get: {method: 'get', url: '${ctxRest}/sys/dict{/id}'},
				save: {method: 'post', url: '${ctxRest}/sys/dict'}
			};
			var resource;
			new Vue({
				el:"body",
				data : {
					obj : {}
				},
				ready: function () {
					resource = this.$resource(null, {}, actions);

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
							alert("保存成功");
						})
					}
				}
			})
		});
	</script>
</head>
<body>
<section class="content-header">
	<h1>字典信息
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>字典管理</a></li>
		<li><a href="#"><i class="fa fa-dashboard"></i>字典列表</a></li>
		<li class="active">字典信息</li>
	</ol>
</section>
<form id="inputForm" class="form-horizontal">
	<input type="hidden" id="id" value="${id}">
	<section class="content">
		<div class="box box-info">
			<form class="form-horizontal">
				<div class="box-body">
					<div class="form-group">
						<label class="col-sm-2 control-label">类型</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.type"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">描述</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.description"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">标签</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.label"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">键值</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.value"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">排序</label>
						<div class="col-sm-4">
							<input type="number" class="form-control" v-model="obj.sort"/>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<div class="col-sm-offset-2 col-sm-1">
						<a class="btn btn-primary pull-left" @click="save()">保存</a>
					</div>
					<div class="col-sm-1">
						<a class="btn btn-info pull-left" href="${ctx}/sys/dict/list">返回</a>
					</div>
				</div>
			</form>
		</div>
	</section>
</form>
</body>
</html>