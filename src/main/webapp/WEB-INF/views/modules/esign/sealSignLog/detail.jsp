<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>证书管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>签章日志详情</li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="seal" action="${ctx}/esign/seal/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<tags:message content="${message}"/>

		<div class="control-group">
			<label class="control-label">印章名称:</label>
			<div class="controls">
				<label class="lbl">${sealSignLog.sealName}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">签章人:</label>
			<div class="controls">
				<label class="lbl">${sealSignLog.signUserName}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">签章方式:</label>
			<div class="controls">
				<label class="lbl">${sealSignLog.signMethod}</label>/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">签章结果:</label>
			<div class="controls">
				<label class="lbl">${sealSignLog.signResult}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">签章时间:</label>
			<div class="controls">
				<label class="lbl">${sealSignLog.signTime}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">IP地址:</label>
			<div class="controls">
				<label class="lbl">${sealSignLog.ip}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件名称:</label>
			<div class="controls">
				<label class="lbl">${sealSignLog.fileName}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<label class="lbl">${sealSignLog.remarks}</label>
			</div>
		</div>

		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>