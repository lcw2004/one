<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
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

<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal">
<tags:message content="${message}"/>
<section class="content">
	<div class="box box-info">
		<form class="form-horizontal">
			<div class="box-body">
				<div class="form-group">
					<label class="col-sm-2 control-label">归属公司</label>
					<div class="col-sm-4">
						<p class="form-control-static">${user.company.name}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">归属部门</label>
					<div class="col-sm-4">
						<p class="form-control-static">${user.office.name}</p>
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-4">
						<form:input path="name" htmlEscape="false" maxlength="50" class="form-control" placeholder="姓名"/>
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-4">
						<form:input path="email" htmlEscape="false" maxlength="50" class="form-control" placeholder="姓名"/>
					</div>
				</div>
				<div class="form-group">
					<label for="phone" class="col-sm-2 control-label">电话</label>
					<div class="col-sm-4">
						<form:input path="phone" htmlEscape="false" maxlength="50" class="form-control" placeholder="姓名"/>
					</div>
				</div>
				<div class="form-group">
					<label for="mobile" class="col-sm-2 control-label">手机</label>
					<div class="col-sm-4">
						<form:input path="mobile" htmlEscape="false" maxlength="50" class="form-control" placeholder="姓名"/>
					</div>
				</div>
				<div class="form-group">
					<label for="mobile" class="col-sm-2 control-label">备注</label>
					<div class="col-sm-4">
						<form:textarea class="form-control" path="remarks" htmlEscape="false" rows="3" maxlength="200"/>
					</div>
				</div>
				<div class="form-group">
					<label for="mobile" class="col-sm-2 control-label">用户类型</label>
					<div class="col-sm-4">
						<p class="form-control-static">${fns:getDictLabel(user.userType, 'sys_user_type', '无')}</p>
					</div>
				</div>
				<div class="form-group">
					<label for="mobile" class="col-sm-2 control-label">用户角色</label>
					<div class="col-sm-4">
						<p class="form-control-static">${user.roleNames}</p>
					</div>
				</div>
				<div class="form-group">
					<label for="mobile" class="col-sm-2 control-label">最后登陆</label>
					<div class="col-sm-4">
						<p class="form-control-static">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></p>
					</div>
				</div>
			</div>
			<div class="box-footer">
				<div class="col-sm-offset-2 col-sm-2">
					<button type="submit" class="btn btn-primary pull-left">保存</button>
				</div>
			</div>
		</form>
	</div>
</section>

</form:form>
</body>
</html>