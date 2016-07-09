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
	<div class="bg-img img-balloon"></div>

	<!-- HEADER -->
	<!--===================================================-->
	<div class="cls-header cls-header-lg">
		<div class="cls-brand">
			<a>
				<span>${fns:getConfig('productName')}</span>
			</a>
		</div>
	</div>
	<!--===================================================-->

	<!-- LOGIN FORM -->
	<!--===================================================-->
	<div class="cls-body">
		<div class="cls-login-panel">
			<div class="panel-body">
				<p class="pad-btm">Sign In to your account</p>
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
						<div class="validateCode">
							<label for="validateCode">验证码：</label>
							<tags:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
						</div>
					</c:if>
					<a class="btn btn-primary btn-lg btn-block" onclick="login()">
						登     录
					</a>
				</form>
			</div>
		</div>
		<div class="pad-ver">
			<a href="pages-password-reminder.html" class="btn-link mar-rgt">忘记密码 ?</a>
			<a href="pages-register.html" class="btn-link mar-lft">注册帐号</a>
		</div>
	</div>
	<!--===================================================-->

	<footer class="navbar-fixed-bottom login-footer">
		Copyright &copy; 2012-${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="https://github.com/thinkgem/jeesite" target="_blank">JeeSite</a> ${fns:getConfig('version')}
	</footer>
</div>

<script src="${ctxStatic}/lib/nifty/js/nifty.min.js"></script>
</body>
</html>
