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
		<li><a href="${ctx}/sys/dict/">更新证书</a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="seal" action="${ctx}/esign/seal/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<tags:message content="${message}"/>
		<form:hidden path="id"></form:hidden>

		<div class="control-group">
			<label class="control-label" for="certFile">证书文件:</label>
			<div class="controls">
				<input type="file" id="certFile" name="certFile" class="required certFile"/>
				<span class="help-inline">PFX格式证书文件。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="certPassword">证书密码:</label>
			<div class="controls">
				<input id="certPassword" name="certPassword" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline">证书密码。</span>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="esign:seal:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>