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

			$("#s2id_sealType").change(function () {
				alert($("#sealType").val());
			});

			$("#sealType").change(function () {
				toggleUserAndCompany($(this).val());
			});

			toggleUserAndCompany($("#sealType").val());

			$("#companyName").change(function () {
				createSealName();
			});
			$("#userName").change(function () {
				createSealName();
			})
		});

		function saveData() {
			var id = $("#id").val();
			var form = $("form");
			if(id != null && id.length > 0 ) {
				form.attr("action", "${ctx}/esign/seal/update");
			}
			form.submit();
		}

		var toggleUserAndCompany = function (sealType) {
			if(sealType == 1) {
				$("#companyDiv").show();
				$("#userDiv").hide();
			} else if(sealType == 2) {
				$("#companyDiv").hide();
				$("#userDiv").show();
			}
		};

		var createSealName = function () {
			var sealType = $("#sealType").val();
			var sealTypeCn = $("#sealType > option[value=" + sealType + "]").text();

			if(sealType == 1) {
				$("#sealName").val($("#companyName").val() + sealTypeCn);
			} else if(sealType == 2) {
				$("#sealName").val($("#userName").val() + sealTypeCn);
			}
		}

		// 正常打开
		function selectUser() {
			top.$.jBox.open("iframe:${ctx}/sys/user/selectUser", "选择用户", 800, 420, {
				buttons:{"确定":"ok", ${allowClear ? "\"清除\" : \"clear\", ":""}"关闭":true}, submit:function(v, h, f){
					if (v=="ok"){
						var iframe = h.find("iframe")[0].contentWindow;
						var selectUserInfo = iframe.$("input[name=selectUserRdo]:checked").val();
						var userInfo = selectUserInfo.split("_");
						var id = userInfo[0];
						var name = userInfo[1];
						$("#userId").val(id);
						$("#userName").val(name);
					} else if (v == "clear"){
						$("#userId").val("");
						$("#userName").val("");
					}
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dict/">印章申请</a></li>
	</ul><br/>

		<form:form id="inputForm" modelAttribute="seal" action="${ctx}/esign/seal/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<tags:message content="${message}"/>
		<form:hidden path="id"></form:hidden>

		<c:if test="${empty seal.id}">
		<div class="control-group">
			<label class="control-label" for="sealName">印章类型:</label>
			<div class="controls">
				<form:select path="sealType">
					<form:options items="${fns:getDictList('esign_seal_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>

		<div class="control-group" id="companyDiv">
			<label class="control-label" for="sealName">印章所属公司:</label>
			<div class="controls">
				<tags:treeselect id="company" name="companyId" value="${user.company.id}" labelName="companyName" labelValue="${user.company.name}"
								 title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>

		<div class="control-group" id="userDiv" style="display: none">
			<label class="control-label" for="sealName">印章所属用户:</label>
			<div class="controls">
				<div class="input-append">
					<input id="userId" name="userId" class="${cssClass}" type="hidden" value="${value}"${disabled eq 'true' ? ' disabled=\'disabled\'' : ''}/>
					<input id="userName" name="userName" readonly="readonly" type="text" value="${labelValue}" maxlength="50"${disabled eq "true"? " disabled=\"disabled\"":""}"
					class="${cssClass}" style="${cssStyle}"/><a id="${id}Button" href="javascript:" onclick="selectUser()" class="btn${disabled eq 'true' ? ' disabled' : ''}"><i class="icon-search"></i></a>&nbsp;&nbsp;
				</div>
			</div>
		</div>
		</c:if>

		<div class="control-group">
			<label class="control-label" for="sealName">印章名称:</label>
			<div class="controls">
				<form:input path="sealName" name="sealName" htmlEscape="false" maxlength="200" class="input-xlarge required" />
				<span class="help-inline">印章名称。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sealName">印章调取码:</label>
			<div class="controls">
				<form:input path="sealToken" name="sealToken" htmlEscape="false" maxlength="200" class="input-xlarge required" />
				<span class="help-inline">印章调取码，主要用于签章端根据印章调取码调取电子印章数据进行签章。</span>
			</div>
		</div>

		<c:if test="${empty seal.id}">
		<div class="control-group">
			<label class="control-label" for="certFile">印章文件:</label>
			<div class="controls">
				<input type="file" id="sealFile" name="sealFile" class="required imgFile"/>
				<span class="help-inline">印章图片文件。</span>
			</div>
		</div>
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
		</c:if>

		<div class="control-group">
			<label class="control-label" for="signCount">允许签章次数:</label>
			<div class="controls">
				<form:input path="signCount" htmlEscape="false" maxlength="200" class="input-xlarge required number"/>
				<span class="help-inline">允许签章次数。</span>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="esign:seal:edit">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="saveData()"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>