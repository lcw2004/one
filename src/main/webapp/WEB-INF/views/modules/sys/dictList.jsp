<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript">
	</script>
</head>
<body>
	<section class="content-header">
		<h1>字典列表
		</h1>
		<ol class="breadcrumb">
			<li><a><i class="fa fa-dashboard"></i>系统设置</a></li>
			<li class="active">字典列表</li>
		</ol>
	</section>
	<section class="content">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Hover Data Table</h3>
					</div>
					<div class="box-body">
						<table class="table table-bordered table-hover">
							<thead>
							<tr>
								<th>Rendering engine</th>
								<th>colspan</th>
								<th>Platform</th>
								<th>version</th>
								<th>CSS grade</th>
							</tr>
							</thead>
							<tbody>
							<tr role="row" class="odd">
								<td class="">Gecko</td>
								<td class="">Seamonkey 1.1</td>
								<td class="sorting_1">Win 98+ / OSX.2+</td>
								<td class="">1.8</td>
								<td class="">A</td>
							</tr>
							<tr role="row" class="even">
								<td class="">Gecko</td>
								<td class="">Mozilla 1.7</td>
								<td class="sorting_1">Win 98+ / OSX.1+</td>
								<td class="">1.7</td>
								<td class="">A</td>
							</tr>
							</tbody>
						</table>
						<%@include file="/WEB-INF/views/include/page.jsp" %>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>