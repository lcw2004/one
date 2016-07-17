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
        <li><a><i class="fa fa-dashboard"></i>个人信息</a></li>
        <li class="active">修改密码</li>
    </ol>
</section>

<section class="content">
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
            <div class="box-footer">
                <div class="col-sm-offset-2 col-sm-2">
                    <button type="submit" class="btn btn-primary pull-left">保存</button>
                </div>
            </div>
        </form>
    </div>

</section>
</body>
</html>