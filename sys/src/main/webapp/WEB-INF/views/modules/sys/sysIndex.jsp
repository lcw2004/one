<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${fns:getConfig('productName')}</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<%@ include file="/WEB-INF/views/include/head.jsp"%>

	<script>
		var menuBus = new Vue();
		$(document).ready(function () {
			var actions = {queryMenu: {method: 'get', url: '${ctxRest}/sys/menu/user'}};
			new Vue({
				el: ".wrapper",
				data: {
					menu : {}
				},
				mounted: function () {
					var resource = this.$resource(null, {}, actions);
					resource.queryMenu().then(function (response) {
						this.menu = response.body;
					});
				}
			});

			$("#contentIframe").height($(".content-wrapper").height());
		});
	</script>
</head>

<body class="hold-transition skin-blue sidebar-mini" style="overflow-y: hidden">
	<div class="wrapper">

		<main-head :sys-menu="menu.childList"></main-head>
		<main-aside></main-aside>

		<div class="content-wrapper">
			<iframe id="contentIframe" name="contentIframe" style="width: 100%; height: 100%"></iframe>
		</div>

		<main-foot></main-foot>

		<div class="control-sidebar-bg"></div>
	</div>
</body>
</html>

<script>
	var MainHead = {
		template : "#main-head",
		props: {
			sysMenu: [Array]
		},
		data: function () {
			return {
				activeMenu: {}
			}
		},
		watch: {
			"activeMenu": {
				handler: function () {
					menuBus.$emit("toggle-menu", this.activeMenu);
				}
			},
			"sysMenu": {
				handler: function () {
					if(this.sysMenu.length > 0) {
						this.activeMenu = this.sysMenu[0];
					}
				}
			}
		}
	};
	Vue.component("main-head", MainHead);
</script>
<template id="main-head">
	<header class="main-header">
		<!-- Logo -->
		<a href="#" class="logo">
			<span class="logo-mini"><b>${fns:getConfig('productShortName')}</b></span>
			<span class="logo-lg"><b>${fns:getConfig('productName')}</b></span>
		</a>

		<!-- Header Navbar -->
		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
				<span class="sr-only">Toggle navigation</span>
			</a>

			<%--系统级别菜单--%>
			<div class="">
				<ul id="menu" class="nav navbar-nav navbar-left">
					<li v-for="system of sysMenu" :class="{'active': activeMenu.id == system.id}" @click="activeMenu = system">
						<a v-text="system.name"></a>
					</li>
				</ul>
			</div>

			<!-- Navbar Right Menu -->
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<main-head-message></main-head-message>
					<main-head-notifications></main-head-notifications>
					<main-head-tasks></main-head-tasks>
					<main-head-user-account></main-head-user-account>
					<li>
						<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
					</li>
				</ul>
			</div>
		</nav>
	</header>
</template>

<script>
	var MainHeadMessages = {
		template : "#main-head-message"
	};
	Vue.component("main-head-message", MainHeadMessages);
</script>
<template id="main-head-message">
	<li class="dropdown messages-menu">
		<!-- Menu toggle button -->
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			<i class="fa fa-envelope-o"></i>
			<span class="label label-success">4</span>
		</a>
		<ul class="dropdown-menu">
			<li class="header">You have 4 messages</li>
			<li>
				<!-- inner menu: contains the messages -->
				<ul class="menu">
					<li><!-- start message -->
						<a href="#">
							<div class="pull-left">
								<!-- User Image -->

								<img src="${ctxStatic}/lib/AdminLTE-2.3.5/img/user2-160x160.jpg" class="img-circle" alt="User Image">
							</div>
							<!-- Message title and timestamp -->
							<h4>
								Support Team
								<small><i class="fa fa-clock-o"></i> 5 mins</small>
							</h4>
							<!-- The message -->
							<p>Why not buy a new awesome theme?</p>
						</a>
					</li>
					<!-- end message -->
				</ul>
				<!-- /.menu -->
			</li>
			<li class="footer"><a href="#">See All Messages</a></li>
		</ul>
	</li>
</template>

<script>
	var MainHeadNotifications = {
		template : "#main-head-notifications"
	};
	Vue.component("main-head-notifications", MainHeadNotifications);
</script>
<template id="main-head-notifications">
	<li class="dropdown notifications-menu">
		<!-- Menu toggle button -->
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			<i class="fa fa-bell-o"></i>
			<span class="label label-warning">10</span>
		</a>
		<ul class="dropdown-menu">
			<li class="header">You have 10 notifications</li>
			<li>
				<!-- Inner Menu: contains the notifications -->
				<ul class="menu">
					<li><!-- start notification -->
						<a href="#">
							<i class="fa fa-users text-aqua"></i> 5 new members joined today
						</a>
					</li>
					<!-- end notification -->
				</ul>
			</li>
			<li class="footer"><a href="#">View all</a></li>
		</ul>
	</li>
</template>

<script>
	var MainHeadTesks = {
		template : "#main-head-tasks"
	};
	Vue.component("main-head-tasks", MainHeadTesks);
</script>
<template id="main-head-tasks">
	<li class="dropdown tasks-menu">
		<!-- Menu Toggle Button -->
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			<i class="fa fa-flag-o"></i>
			<span class="label label-danger">9</span>
		</a>
		<ul class="dropdown-menu">
			<li class="header">You have 9 tasks</li>
			<li>
				<!-- Inner menu: contains the tasks -->
				<ul class="menu">
					<li><!-- Task item -->
						<a href="#">
							<!-- Task title and progress text -->
							<h3>
								Design some buttons
								<small class="pull-right">20%</small>
							</h3>
							<!-- The progress bar -->
							<div class="progress xs">
								<!-- Change the css width attribute to simulate progress -->
								<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
									<span class="sr-only">20% Complete</span>
								</div>
							</div>
						</a>
					</li>
					<!-- end task item -->
				</ul>
			</li>
			<li class="footer">
				<a href="#">View all tasks</a>
			</li>
		</ul>
	</li>
</template>

<script>
	var MainHeadUserAccount = {
		template : "#main-head-user-account"
	};
	Vue.component("main-head-user-account", MainHeadUserAccount);
</script>
<template id="main-head-user-account">
	<li class="dropdown user user-menu">
		<!-- Menu Toggle Button -->
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			<!-- The user image in the navbar-->
			<img src="${ctxStatic}/lib/AdminLTE-2.3.5/img/user2-160x160.jpg" class="user-image" alt="User Image">
			<!-- hidden-xs hides the username on small devices so only the image appears. -->
			<span class="hidden-xs"><shiro:principal property="name"/></span>
		</a>
		<ul class="dropdown-menu">
			<!-- The user image in the menu -->
			<li class="user-header">
				<img src="${ctxStatic}/lib/AdminLTE-2.3.5/img/user2-160x160.jpg" class="img-circle" alt="User Image">

				<p>
					<shiro:principal property="name"/> - Web Developer
					<small>Member since Nov. 2012</small>
				</p>
			</li>
			<!-- Menu Body -->
			<li class="user-body">
				<div class="row">
					<div class="col-xs-4 text-center">
						<a href="#">Followers</a>
					</div>
					<div class="col-xs-4 text-center">
						<a href="#">Sales</a>
					</div>
					<div class="col-xs-4 text-center">
						<a href="#">Friends</a>
					</div>
				</div>
				<!-- /.row -->
			</li>
			<!-- Menu Footer-->
			<li class="user-footer">
				<div class="pull-left">
					<a href="${ctx}/sys/user/info" target="contentIframe" class="btn btn-default btn-flat">个人信息</a>
					<a href="${ctx}/sys/user/modifyPwd" target="contentIframe" class="btn btn-default btn-flat">修改密码</a>
				</div>
				<div class="pull-right">
					<a href="${ctx}/logout" class="btn btn-default btn-flat">退出</a>
				</div>
			</li>
		</ul>
	</li>
</template>

<script>
	var MainAside = {
		template : "#main-aside"
	}
	Vue.component("main-aside", MainAside);
</script>
<template id="main-aside">
	<aside class="main-sidebar">
		<section class="sidebar">
			<main-aside-user-panel></main-aside-user-panel>
			<main-aside-search></main-aside-search>
			<main-aside-menu></main-aside-menu>
		</section>
	</aside>
</template>

<script>
	var MainAsideUserPanel = {
		template : "#main-aside-user-panel"
	};
	Vue.component("main-aside-user-panel", MainAsideUserPanel);
</script>
<template id="main-aside-user-panel">
	<div class="user-panel">
		<div class="pull-left image">
			<img src="${ctxStatic}/lib/AdminLTE-2.3.5/img/user2-160x160.jpg" class="img-circle" alt="User Image">
		</div>
		<div class="pull-left info">
			<p>Alexander Pierce</p>
			<!-- Status -->
			<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
		</div>
	</div>
</template>

<script>
	var MainAsideSearch = {
		template : "#main-aside-search"
	};
	Vue.component("main-aside-search", MainAsideSearch);
</script>
<template id="main-aside-search">
	<form action="#" method="get" class="sidebar-form">
		<div class="input-group">
			<input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
		</div>
	</form>
</template>

<script>
	var MainAsideMenu = {
		template : "#main-aside-menu",
		data: function() {
			return {
				leftMenu: []
			}
		},
		mounted: function() {
			var self = this;
			menuBus.$on("toggle-menu", function(activeMenu) {
				self.leftMenu = activeMenu;
			});
		}
	};
	Vue.component("main-aside-menu", MainAsideMenu);
</script>
<template id="main-aside-menu">
	<ul class="sidebar-menu">
		<li class="header">HEADER</li>
		<li class="treeview" v-for="menuLevel1 of leftMenu.childList">
			<a>
				<%-- 一级菜单 --%>
				<i :class="menuLevel1.icon"></i><span>{{ menuLevel1.name }}</span>
				<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
			</a>
			<ul class="treeview-menu">
				<%-- 二级菜单 --%>
				<li v-for="menuLevel2 of menuLevel1.childList">
					<a target="contentIframe" :href="'${ctx}' + menuLevel2.href"><i :class="menuLevel2.icon"></i>{{ menuLevel2.name }}</a>
				</li>
			</ul>
		</li>
	</ul>
</template>

<script>
	var MainFoot = {
		template : "#main-foot"
	}
	Vue.component("main-foot", MainFoot);
</script>
<template id="main-foot">
	<aside class="control-sidebar control-sidebar-dark">
		<!-- Create the tabs -->
		<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
			<li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
			<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<!-- Home tab content -->
			<div class="tab-pane active" id="control-sidebar-home-tab">
				<h3 class="control-sidebar-heading">Recent Activity</h3>
				<ul class="control-sidebar-menu">
					<li>
						<a>
							<i class="menu-icon fa fa-birthday-cake bg-red"></i>

							<div class="menu-info">
								<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

								<p>Will be 23 on April 24th</p>
							</div>
						</a>
					</li>
				</ul>
				<!-- /.control-sidebar-menu -->

				<h3 class="control-sidebar-heading">Tasks Progress</h3>
				<ul class="control-sidebar-menu">
					<li>
						<a>
							<h4 class="control-sidebar-subheading">
								Custom Template Design
								<span class="pull-right-container">
								  <span class="label label-danger pull-right">70%</span>
								</span>
							</h4>

							<div class="progress progress-xxs">
								<div class="progress-bar progress-bar-danger" style="width: 70%"></div>
							</div>
						</a>
					</li>
				</ul>
				<!-- /.control-sidebar-menu -->

			</div>
			<!-- /.tab-pane -->
			<!-- Stats tab content -->
			<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
			<!-- /.tab-pane -->
			<!-- Settings tab content -->
			<div class="tab-pane" id="control-sidebar-settings-tab">
				<form method="post">
					<h3 class="control-sidebar-heading">General Settings</h3>

					<div class="form-group">
						<label class="control-sidebar-subheading">
							Report panel usage
							<input type="checkbox" class="pull-right" checked>
						</label>

						<p>
							Some information about this general settings option
						</p>
					</div>
					<!-- /.form-group -->
				</form>
			</div>
			<!-- /.tab-pane -->
		</div>
	</aside>
</template>