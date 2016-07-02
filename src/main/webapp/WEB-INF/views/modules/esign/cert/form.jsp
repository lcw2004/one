<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>证书管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dict/">证书列表</a></li>
		<li class="active"><a href="${ctx}/esign/cert/form?id=${dict.id}">字典<shiro:hasPermission name="esign:cert:edit">${not empty dict.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="esign:cert:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="cert" action="${ctx}/esign/cert/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>

		<div class="control-group">
			<label class="control-label" for="certFile">证书文件:</label>
			<div class="controls">
				<input type="file" id="certFile" name="certFile" class="required"/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="esign:cert:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>