<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>机构信息</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var actions = {
				get: {method: 'get', url: '${ctxRest}/sys/office{/id}'},
				save: {method: 'post', url: '${ctxRest}/sys/office'}
			};
			var resource;
			new Vue({
				el:"body",
				data : {
					obj : {},

					// 模态窗属性
					officeTreeModalConfig: {
						show : false,
						title : "选择上级机构"
					},
					// 模态窗属性
					areaTreeModalConfig: {
						show : false,
						title : "选择归属区域"
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
							window.location.href = "${ctx}/sys/office";
						})
					}
				}
			})
		});
	</script>
</head>
<body>
<section class="content-header">
	<h1>机构信息
	</h1>
	<ol class="breadcrumb">
		<li><a><i class="fa fa-dashboard"></i>系统设置</a></li>
		<li><a href="${ctx}/sys/office"><i class="fa fa-dashboard"></i>机构列表</a></li>
		<li class="active">机构信息</li>
	</ol>
</section>
<form id="inputForm" class="form-horizontal">
	<input type="hidden" id="id" value="${id}">
	<section class="content">
		<div class="box box-info">
			<form class="form-horizontal">
				<div class="box-body">
					<div class="form-group">
						<label class="col-sm-2 control-label">上级机构</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control" v-model="obj.parent.name"/>
								<span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="officeTreeModalConfig.show = true">选择</button>
								</span>
							</div>
							<office-tree-modal :config.sync="officeTreeModalConfig" :value.sync="obj.parent"></office-tree-modal>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">归属区域</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control" v-model="obj.area.name"/>
								<span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="areaTreeModalConfig.show = true">选择</button>
								</span>
							</div>
							<area-tree-modal :config.sync="areaTreeModalConfig" :value.sync="obj.area"></area-tree-modal>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">机构名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.name"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">机构编码</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.code"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">机构类型</label>
						<div class="col-sm-4">
							<select class="form-control" v-model="obj.type">
								<c:forEach var="dict" items="${fns:getDictList('sys_office_type')}">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">机构级别</label>
						<div class="col-sm-4">
							<select class="form-control" v-model="obj.grade">
								<c:forEach var="dict" items="${fns:getDictList('sys_office_grade')}">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">联系地址</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.address"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邮政编码</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.zipCode"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">负责人</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.master"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">电话</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.phone"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">传真</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.fax"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" v-model="obj.email"/>
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
							<a class="btn btn-info" href="${ctx}/sys/office">返回</a>
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