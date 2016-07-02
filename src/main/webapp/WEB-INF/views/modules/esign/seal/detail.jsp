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
		<li><a href="${ctx}/sys/dict/">印章申请</a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="seal" action="${ctx}/esign/seal/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<tags:message content="${message}"/>

		<div class="control-group">
			<label class="control-label">印章名称:</label>
			<div class="controls">
				<label class="lbl">${seal.sealName}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">印章所属用户/机构:</label>
			<div class="controls">
				<label class="lbl">${seal.sealOwnerName}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">印章图片:</label>
			<div class="controls">
				<img src="sealImg?id=${seal.id}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">印章调取码:</label>
			<div class="controls">
				<label class="lbl">${seal.sealToken}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">印章类型:</label>
			<div class="controls">
				<label class="lbl">${seal.sealType}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书序列号:</label>
			<div class="controls">
				<label class="lbl">${seal.cert.serialNumber}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">IssuerDN:</label>
			<div class="controls">
				<label class="lbl">${seal.cert.issuerDN}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SubjectDN:</label>
			<div class="controls">
				<label class="lbl">${seal.cert.subjectDN}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书根:</label>
			<div class="controls">
				<label class="lbl">${seal.cert.certRoot}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书有效期:</label>
			<div class="controls">
				<label class="lbl">${seal.cert.notBefore} 到 ${seal.cert.notAfter}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">剩余签章次数:</label>
			<div class="controls">
				<label class="lbl">${seal.signCount}</label>
			</div>
		</div>

		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>