<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>修改密码</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#oldPassword").focus();
            $("#inputForm").validate({
                messages: {
                    confirmNewPassword: {equalTo: "输入与上面相同的密码"}
                }
            });
        });
    </script>
</head>
<body>
<section class="content-header">
    <h1>修改密码
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>TODO</a></li>
        <li><a href="#">TODOa></li>
        <li class="active">TODO</li>
    </ol>
</section>

<section class="content">
    <div class="row">
        <div class="col-md-12">
            <div class="box box-info">
                <form class="form-horizontal">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="oldPassword" class="col-sm-2 control-label">旧密码</label>
                            <div class="col-sm-4">
                                <input class="form-control" id="oldPassword" name="oldPassword" type="password" placeholder="旧密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="newPassword" class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-4">
                                <input class="form-control" id="newPassword" name="newPassword" type="password" placeholder="新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="newPassword" class="col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-4">
                                <input class="form-control" id="confirmNewPassword" name="confirmNewPassword" type="password" placeholder="确认密码">
                            </div>
                        </div>
                    </div>
                    <div class="box-footer col-sm-offset-2 col-sm-2">
                        <button type="submit" class="btn btn-primary">保 存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>