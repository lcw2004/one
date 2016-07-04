<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>签章日志审计</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
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
	<form:form id="searchForm" action="${ctx}/esign/signlog/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div>
			<label>印章名称：</label><input id="sealName" name="sealName" type="text" maxlength="50" class="input-small" value="${sealName}"/>
			<label>签章人名称：</label><input id="signUserName" name="signUserName" type="text" maxlength="50" class="input-small" value="${signUserName}"/>
			<label>文件名称：</label><input id="fileName" name="fileName" type="text" maxlength="50" class="input-small" value="${fileName}"/>
			&nbsp;
			<label>开始日期：</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
									   value="${beginDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			<label>结束日期：</label><input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
									   value="${endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th>印章名称</th><th>签章人</th><th>签章方式</th><th>签章结果</th><th>签章时间</th><th>IP地址</th><th>文件名称</th></tr>
		<c:forEach items="${page.list}" var="sealSignLog">
			<tr>
				<%--<td><a href="detail?id=${sealSignLog.id}">${sealSignLog.id}</a></td>--%>
				<td>${sealSignLog.sealName}</td>
				<td>${sealSignLog.signUserName}</td>
				<td>
					<c:if test="${sealSignLog.signMethod == 1}">PDF批量签章</c:if>
				</td>
				<td>
					<c:if test="${sealSignLog.signResult}">成功</c:if>
					<c:if test="${not sealSignLog.signResult}">失败</c:if>
				</td>
				<td>${sealSignLog.signTime}</td>
				<td>${sealSignLog.ip}</td>
				<td>${sealSignLog.fileName}</td>
				<%--<td>--%>
					<%--<a href="${ctx}/esign/signlog/detail?id=${sealSignLog.id}">详情</a>--%>
				<%--</td>--%>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>