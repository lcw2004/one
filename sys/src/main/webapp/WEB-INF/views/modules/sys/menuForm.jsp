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
				el: ".content",
				data : {
					obj : {
						isShow : 1
					},
					menuTreeModalConfig: {
						show : false,
						title : "选择上级菜单"
					},
					iconModalConfig: {
						show : false,
						title : "选择图标"
					}
				},
				mounted: function () {
					resource = this.$resource(null, {}, actions);

					// 加载数据
					var id = $("#id").val();
					if (id) {
						resource.get({id: id}).then(function (response) {
							this.obj = response.body;
						})
					}
				},
				methods: {
					save : function () {
						resource.save(null, JSON.stringify(this.obj)).then(function (response) {
							Vue.$alert("保存成功");
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
						<label class="col-sm-2 control-label">上级菜单</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control" v-model="obj.parent.name"/>
								<span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="menuTreeModalConfig.show = true">选择</button>
								</span>
							</div>
							<menu-tree-modal :config.sync="menuTreeModalConfig" :value.sync="obj.parent"></menu-tree-modal>
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
							<button class="btn btn-info" type="button" @click="iconModalConfig.show = true">选择</button>
							<i v-if="obj.icon" :class="obj.icon" style="font-size: 25px"></i>
							<icon-modal :config="iconModalConfig" v-model="obj.icon"></icon-modal>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">可见</label>
						<div class="col-sm-4">
							<div class="radio">
								<c:forEach var="dict" items="${fns:getDictList('show_hide')}">
									<label>
										<input type="radio" value="${dict.value}" v-model="obj.isShow">
										${dict.label}
									</label>
								</c:forEach>
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
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<a class="btn btn-primary" @click="save()">保存</a>
							<a class="btn btn-info" href="${ctx}/sys/menu">返回</a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>
</form>
</body>
</html>