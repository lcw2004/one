]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>印章管理</title>
	<meta name="decorator" content="default"/>
	<script>
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/esign/role/">印章列表</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="seal" action="${ctx}/esign/seal/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<div>
			<label>印章调取码：</label><form:input path="sealToken" htmlEscape="false" maxlength="50" class="input-small"/>
			<label>印章名称：</label><form:input path="sealName" htmlEscape="false" maxlength="50" class="input-small"/>
		</div>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
	</div>
	</form:form>

	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th>印章名称</th><th>印章调取码</th><th>印章类型</th><th>申请者</th><th>申请时间</th><th>剩余签章次数</th></th><shiro:hasPermission name="esign:seal:edit"><th>操作</th></shiro:hasPermission></tr>
		<c:forEach items="${page.list}" var="seal">
			<tr>
				<td><a href="detail?id=${seal.id}">${seal.sealName}</a></td>
				<td>${seal.sealToken}</td>
				<td>
					<c:if test="${seal.sealType == 1}">机构公章</c:if>
					<c:if test="${seal.sealType == 2}">个人私章</c:if>
				</td>
				<td>${seal.createBy.name}</td>
				<td>${seal.createDate}</td>
				<td>${seal.signCount}</td>
				<shiro:hasPermission name="esign:seal:edit">
				<td>
					<a href="${ctx}/esign/seal/form?id=${seal.id}">修改</a>
					<a href="${ctx}/esign/seal/form/sealimg?id=${seal.id}">更新印章图片</a>
					<a href="${ctx}/esign/seal/form/sealcert?id=${seal.id}">更新证书</a>
					<a href="${ctx}/esign/seal/delete?id=${seal.id}" onclick="return confirmx('要删除该印章吗？', this.href)">删除</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>