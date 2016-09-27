<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>区域信息</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var actions = {
				get: {method: 'get', url: '${ctxRest}/sys/area{/id}'},
				save: {method: 'post', url: '${ctxRest}/sys/area'}
			};
			var resource;
			new Vue({
				el:"body",
				data : {
					obj : {},

					// 模态窗属性
					areaTreeModalConfig: {
						show : false,
						title : "选择上级区域"
					},
					// 模态窗属性
					iconModalConfig: {
						show : false,
						title : "选择图标"
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
							window.location.href = "${ctx}/sys/area";
						})
					}
				}
			})
		});
	</script>
</head>
<body>
<section class="content-header">
	<h1>区域信息
	</h1>
	<ol class="breadcrumb">
		<li><a><i class="fa fa-dashboard"></i>系统设置</a></li>
		<li><a href="${ctx}/sys/area"><i class="fa fa-dashboard"></i>区域列表</a></li>
		<li class="active">区域信息</li>
	</ol>
</section>
<form id="inputForm" class="form-horizontal">
	<input type="hidden" id="id" value="${id}">
	<section class="content">
		<div class="box box-info">
			<form class="form-horizontal">
				<div class="box-body">
					<div class="form-group">
						<label class="col-sm-2 control-label">上级区域</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control" v-model="obj.parent.name"/>
								<span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="areaTreeModalConfig.show = true">选择</button>
								</span>
							</div>
							<area-tree-modal :config.sync="areaTreeModalConfig" :value.sync="obj.parent"></area-tree-modal>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">区域名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.name"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">区域编码</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.code"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">区域类型</label>
						<div class="col-sm-4">
							<select class="form-control" v-model="obj.type">
								<c:forEach var="dict" items="${fns:getDictList('sys_area_type')}">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">备注</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.remarks"/>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<a class="btn btn-primary" @click="save()">保存</a>
							<a class="btn btn-info" href="${ctx}/sys/area">返回</a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>
</form>

<%@include file="/WEB-INF/views/include/component.jsp" %>
</body>
</html>