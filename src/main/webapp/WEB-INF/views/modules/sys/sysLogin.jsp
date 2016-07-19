<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>${fns:getConfig('productName')} 登录</title>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>

	<script>
		$(document).ready(function () {
			$('body').bind('keypress', function (e) {
				if (e.keyCode == 13) {
					login();
				}
			});
		});

		function login() {
			// TODO 首页的提示信息需要优化
			var validateCode = $("#validateCode").val();
			var username = $("#username").val();
			var password = $("#password").val();
			if(username != null && username.trim().length == 0) {
				alert("请输入用户名");
				return false;
			}
			if(password != null && password.trim().length == 0) {
				alert("请输入密码");
				return false;
			}
//			if(validateCode != null && validateCode.trim().length == 0) {
//				alert("请输入验证码");
//				return false;
//			}
			$("#loginForm").submit();
		}
	</script>
</head>

<body>
<div class="login-container">

	<!-- BACKGROUND IMAGE -->
	<!--===================================================-->
	<div class="login-bg-img login-bg-img-balloon"></div>

	<!-- HEADER -->
	<!--===================================================-->
	<div class="login-header">
		<div class="login-header-title">
			<a>
				<span>${fns:getConfig('productName')}</span>
			</a>
		</div>
	</div>
	<!--===================================================-->

	<!-- LOGIN FORM -->
	<!--===================================================-->
	<div class="login-body">
		<div class="login-body-panel">
			<div class="panel-body">
				<p class="login-body-panel-head">Sign In to your account</p>
				<form id="loginForm" class="form login-form" action="${ctx}/login" method="post">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon login-icon"><i class="fa fa-user"></i></div>
							<input type="text" id="username" name="username" class="form-control" style="height: 38px" placeholder="登录名">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon login-icon"><i class="fa fa-asterisk"></i></div>
							<input type="password" id="password" name="password" class="form-control" style="height: 38px" placeholder="密码">
						</div>
					</div>
					<div class="row">
						<div class="col-xs-8 text-left checkbox">
							<label class="form-checkbox form-icon">
								<input type="checkbox" id="rememberMe" name="rememberMe"> 记住我
							</label>
						</div>
						<div class="col-xs-4">
						</div>
					</div>
					<c:if test="${isValidateCodeLogin}">
						<div class="text-left" style="margin-bottom: 10px;">
							<label>验证码：</label>
							<input type="text" id="validateCode" name="validateCode" style="min-width: 20px;width: 35%;max-width: 150px;height: 28px;font-size: 15px" maxlength="4">
						<span >
							<img id="validateCodeImg" src="${pageContext.request.contextPath}/validateCode" onclick="$('#validateCodeImg').attr('src','/one/validateCode?' + new Date().getTime());"  style="margin-bottom: 4px">
						</span>
							<a onclick="$('#validateCodeImg').attr('src','/one/validateCode?' + new Date().getTime());" class="form-icon" ><i class="fa fa-refresh" aria-hidden="true"></i></a>
						</div>
					</c:if>

					<a class="btn btn-primary btn-lg btn-block" onclick="login()">
						登     录
					</a>
				</form>
			</div>
		</div>
		<div class="login-body-panel1">
			<a href="pages-password-reminder.html" class="btn-link login-mar-rgt">忘记密码 ?</a>
			<a href="pages-register.html" class="btn-link login-mar-lft">注册帐号</a>
		</div>
	</div>
	<!--===================================================-->

	<footer class="navbar-fixed-bottom login-footer">
		Copyright &copy; 2012-${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="https://github.com/thinkgem/jeesite" target="_blank">JeeSite</a> ${fns:getConfig('version')}
	</footer>
</div>

</body>
</html>
