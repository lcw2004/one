<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单导航</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<script type="text/javascript"> 
		$(document).ready(function() {
			$(".accordion-heading a").click(function(){
				$('.accordion-toggle i').removeClass('icon-chevron-down');
				$('.accordion-toggle i').addClass('icon-chevron-right');
				if(!$($(this).attr('href')).hasClass('in')){
					$(this).children('i').removeClass('icon-chevron-right');
					$(this).children('i').addClass('icon-chevron-down');
				}
			});
			$(".accordion-body a").click(function(){
				$("#menu li").removeClass("active");
				$("#menu li i").removeClass("icon-white");
				$(this).parent().addClass("active");
				$(this).children("i").addClass("icon-white");
			});
			$(".accordion-body a:first i").click();
		});
	</script>
</head>
<body>
<section class="sidebar" style="overflow: hidden; width: auto; height: 627px;">

	<!-- sidebar menu: : style can be found in sidebar.less -->
	<ul class="sidebar-menu">
		<li class="active">
			<a href="index.html">
				<i class="fa fa-dashboard"></i> <span>Dashboard</span>
			</a>
		</li>
		<li class="treeview active">
			<a href="">
				<i class="fa fa-cog"></i>
				<span>Component</span>
				<i class="fa pull-right fa-angle-down"></i>
			</a>
			<ul class="treeview-menu" style="display: block;">
				<li><a href="todo-list.html" style="margin-left: 10px;"> Todo List</a></li>
				<li><a href="draggable-portlet.html" style="margin-left: 10px;"> Draggable Portlet</a></li>
				<li><a href="nestable.html" style="margin-left: 10px;"> Nestable</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="">
				<i class="fa fa-bar-chart-o"></i>
				<span>Charts</span>
				<i class="fa pull-right fa-angle-left"></i>
			</a>
			<ul class="treeview-menu" style="display: none;">
				<li><a href="morris.html" style="margin-left: 10px;"> Morris</a></li>
				<li><a href="xchart.html" style="margin-left: 10px;"> XChart</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#">
				<i class="fa fa-laptop"></i>
				<span>UI Elements</span>
				<i class="fa pull-right fa-angle-left"></i>
			</a>
			<ul class="treeview-menu" style="display: none;">
				<li><a href="general.html" style="margin-left: 10px;"> General</a></li>
				<li><a href="icons.html" style="margin-left: 10px;"> Icons</a></li>
				<li><a href="button.html" style="margin-left: 10px;"> Buttons</a></li>
				<li><a href="slider.html" style="margin-left: 10px;"> Sliders</a></li>
				<li><a href="timeline.html" style="margin-left: 10px;"> Timeline</a></li>
				<li><a href="typography.html" style="margin-left: 10px;"> Typography</a></li>
				<li><a href="grid.html" style="margin-left: 10px;"> Grid</a></li>
				<li><a href="panel-well.html" style="margin-left: 10px;"> Panel &amp; Well</a></li>
				<li><a href="collapse.html" style="margin-left: 10px;"> Collapse</a></li>
				<li><a href="list-media.html" style="margin-left: 10px;"> List Media</a></li>
				<li><a href="popup-notif.html" style="margin-left: 10px;"> Popup &amp; Notification</a></li>
				<li><a href="jumbotron-thumbnail.html" style="margin-left: 10px;"> Jumbotron &amp; Thumbnail</a></li>
				<li><a href="breadcrumb-pagination.html" style="margin-left: 10px;"> Breadcrumb &amp; Pagination</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#">
				<i class="fa fa-edit"></i> <span>Forms</span>
				<i class="fa fa-angle-left pull-right"></i>
			</a>
			<ul class="treeview-menu">
				<li><a href="form-component.html" style="margin-left: 10px;"> Form Component</a></li>
				<li><a href="advance-component.html" style="margin-left: 10px;"> Advance Component</a></li>
				<li><a href="form-wizard.html" style="margin-left: 10px;"> Form Wizard</a></li>
				<li><a href="form-validation.html" style="margin-left: 10px;"> Form Validation</a></li>
				<li><a href="dropzone-file-upload.html" style="margin-left: 10px;"> Dropzone File Upload</a></li>
				<li><a href="multiple-file-upload.html" style="margin-left: 10px;"> Multiple File upload</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#">
				<i class="fa fa-table"></i> <span>Tables</span>
				<i class="fa fa-angle-left pull-right"></i>
			</a>
			<ul class="treeview-menu">
				<li><a href="basic-table.html" style="margin-left: 10px;"> Basic Table</a></li>
				<li><a href="responsive-table.html" style="margin-left: 10px;"> Responsive Table</a></li>
				<li><a href="dynamic-table.html" style="margin-left: 10px;"> Dynamic Tables</a></li>
				<li><a href="editable-table.html" style="margin-left: 10px;"> Editable Table</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#">
				<i class="fa fa-calendar"></i> <span>Calendar</span>
				<i class="fa fa-angle-left pull-right"></i>
			</a>
			<ul class="treeview-menu">
				<li><a href="basic-calendar.html" style="margin-left: 10px;"> Basic Calendar</a></li>
				<li><a href="language-calendar.html" style="margin-left: 10px;"> Language Calendar</a></li>
				<li><a href="drag-calendar.html" style="margin-left: 10px;"> Dragging Calendar</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#">
				<i class="fa fa-home"></i> <span>Property</span>
				<i class="fa fa-angle-left pull-right"></i>
			</a>
			<ul class="treeview-menu">
				<li><a href="property.html" style="margin-left: 10px;"> Property</a></li>
				<li><a href="property-column.html" style="margin-left: 10px;"> Property Column</a></li>
				<li><a href="property-detail.html" style="margin-left: 10px;"> Property Detail</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#">
				<i class="fa fa-shopping-cart"></i> <span>Store</span>
				<i class="fa fa-angle-left pull-right"></i>
			</a>
			<ul class="treeview-menu">
				<li><a href="store.html" style="margin-left: 10px;"> Store List</a></li>
				<li><a href="store-detail.html" style="margin-left: 10px;"> Store Detail</a></li>
				<li><a href="shopping-cart.html" style="margin-left: 10px;"> Shopping Cart</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#">
				<i class="fa fa-envelope"></i> <span>Mailbox</span>
				<i class="fa fa-angle-left pull-right"></i>
			</a>
			<ul class="treeview-menu">
				<li><a href="inbox.html" style="margin-left: 10px;"> Inbox</a></li>
				<li><a href="inbox-detail.html" style="margin-left: 10px;"> Inbox Detail</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#">
				<i class="fa fa-comments"></i> <span>Chat Room</span>
				<i class="fa fa-angle-left pull-right"></i>
			</a>
			<ul class="treeview-menu">
				<li><a href="lobby.html" style="margin-left: 10px;"> Lobby</a></li>
				<li><a href="chat-room.html" style="margin-left: 10px;"> Chat Room</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#">
				<i class="fa fa-folder"></i> <span>Extras Pages</span>
				<i class="fa fa-angle-left pull-right"></i>
			</a>
			<ul class="treeview-menu">
				<li><a href="invoice.html" style="margin-left: 10px;"> Invoice</a></li>
				<li><a href="login.html" style="margin-left: 10px;"> Login</a></li>
				<li><a href="register.html" style="margin-left: 10px;"> Register</a></li>
				<li><a href="forgot-password.html" style="margin-left: 10px;"> Forgot Password</a></li>
				<li><a href="lockscreen.html" style="margin-left: 10px;"> Lockscreen</a></li>
				<li><a href="404.html" style="margin-left: 10px;"> 404 Error</a></li>
				<li><a href="500.html" style="margin-left: 10px;"> 500 Error</a></li>
				<li><a href="blank.html" style="margin-left: 10px;"> Blank Page</a></li>
				<li><a href="faq.html" style="margin-left: 10px;"> FAQ</a></li>
			</ul>
		</li>
	</ul>
	<!-- start:project stats -->
	<div class="project-sidebar">
		<div class="main-project-sidebar">
			<p><strong>Projects</strong></p>
			<div class="progress">
				<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
					<span class="sr-only">60% Complete</span>
				</div>
			</div>
			<p>60% Complete</p>
		</div>
		<div class="main-project-sidebar">
			<p><strong>Tasks</strong></p>
			<div class="progress">
				<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
					<span class="sr-only">40% Complete</span>
				</div>
			</div>
			<p>40% Complete</p>
		</div>
		<div class="main-project-sidebar">
			<p><strong>Tasks</strong></p>
			<div class="progress">
				<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%;">
					<span class="sr-only">90% Complete</span>
				</div>
			</div>
			<p>90% Complete</p>
		</div>
	</div>
</section>

	<div class="accordion" id="menu"><c:set var="menuList" value="${fns:getMenuList()}"/><c:set var="firstMenu" value="true"/><c:forEach items="${menuList}" var="menu" varStatus="idxStatus"><c:if test="${menu.parent.id eq (not empty param.parentId?param.parentId:'1')&&menu.isShow eq '1'}">
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapse${menu.id}" title="${menu.remarks}"><i class="icon-chevron-${firstMenu?'down':'right'}"></i>&nbsp;${menu.name}</a>
		    </div>
		    <div id="collapse${menu.id}" class="accordion-body collapse ${firstMenu?'in':''}">
				<div class="accordion-inner">
					<ul class="nav nav-list"><c:forEach items="${menuList}" var="menuChild"><c:if test="${menuChild.parent.id eq menu.id&&menuChild.isShow eq '1'}">
						<li><a href="${fn:indexOf(menuChild.href, '://') eq -1?ctx:''}${not empty menuChild.href?menuChild.href:'/404'}" target="${not empty menuChild.target?menuChild.target:'mainFrame'}" ><i class="icon-${not empty menuChild.icon?menuChild.icon:'circle-arrow-right'}"></i>&nbsp;${menuChild.name}</a></li>
					<c:set var="firstMenu" value="false"/></c:if></c:forEach></ul>
				</div>
		    </div>
		</div>
	</c:if></c:forEach></div>
</body>
</html>
