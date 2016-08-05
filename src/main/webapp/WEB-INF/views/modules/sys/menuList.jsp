<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var actions = {
				getMenuTree : {method : "get", url :'${ctxRest}/sys/menu/tree'}
			};
			var resource;
			new Vue({
				el:"body",
				data : function() {
					return {
						topMenu : {}
					}
				},
				ready: function () {
					resource = this.$resource(null, {}, actions);

					resource.getMenuTree().then(function (response) {
						this.topMenu = response.json();
					})
				},
				methods: {

				}
			})
		});
	</script>
</head>
<body>
<section class="content-header">
	<h1>菜单列表
	</h1>
	<ol class="breadcrumb">
		<li><a><i class="fa fa-dashboard"></i>系统设置</a></li>
		<li class="active">菜单列表</li>
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
					<treetable :menu="topMenu"></treetable>
				</div>
			</div>
		</div>
	</div>
</section>

<%@include file="/WEB-INF/views/include/component.jsp" %>
</body>
</html>
