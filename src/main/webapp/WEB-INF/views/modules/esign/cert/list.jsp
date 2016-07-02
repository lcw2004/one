]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>证书管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/esign/cert/">证书列表</a></li>
		<shiro:hasPermission name="sys:role:edit"><li><a href="${ctx}/esign/cert/form">证书添加</a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th>角色名称</th><th>归属机构</th><th>数据范围</th><shiro:hasPermission name="esign:cert:edit"><th>操作</th></shiro:hasPermission></tr>
		<c:forEach items="${list}" var="seal">
			<tr>
				<td><a href="form?id=${seal.id}">${seal.name}</a></td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>