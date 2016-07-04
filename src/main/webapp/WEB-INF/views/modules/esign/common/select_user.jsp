]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>证书管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			// 表格排序
			tableSort({callBack : page});
		});

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/selectUser").submit();
			return false;
		}
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/selectUser" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<div>
			<label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-small"/>
			<label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		</div><div style="margin-top:8px;">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
	</div>
	</form:form>

	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择</th><th class="sort loginName">登录名</th><th class="sort name">姓名</th><th>归属公司</th><th>归属部门</th><th>电话</th><th>手机</th><th>角色</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td><input type="radio" name="selectUserRdo" value="${user.id}_${user.name}"/></td>
				<td>${user.loginName}</td>
				<td>${user.name}</td>
				<td>${user.company.name}</td>
				<td>${user.office.name}</td>
				<td>${user.phone}</td>
				<td>${user.mobile}</td>
				<td>${user.roleNames}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>