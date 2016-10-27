<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>修改密码</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
    <script type="text/javascript">
        $(document).ready(function() {
            var actions = {
                putPassword: {method: 'put', url: '${ctxRest}/sys/user/password'}
            };
            var resource;
            new Vue({
                el:"body",
                data : {
                    obj : {
                        oldPassword: "",
                        newPassword: "",
                        confirmNewPassword: ""
                    }
                },
                mounted: function () {
                    resource = this.$resource(null, {}, actions, {emulateJSON: true});
                },
                methods: {
                    save : function () {
                        resource.putPassword(null, this.obj).then(function (response) {
                            var result = response.body;
                            if(result.code != "0000") {
                                Vue.$alert(result.desc);
                            } else {
                                Vue.$alert(result.desc, function () {
                                    // 修改密码成功，退出重新登录
                                    if(window.parent) {
                                        window.parent.location.href = "${ctx}/logout";
                                    } else {
                                        window.location.href = "${ctx}/logout";
                                    }
                                });
                            }
                        })
                    }
                }
            })
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
                    <label class="col-sm-2 control-label">旧密码</label>
                    <div class="col-sm-4">
                        <input class="form-control" v-model="obj.oldPassword" type="password" placeholder="旧密码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">新密码</label>
                    <div class="col-sm-4">
                        <input class="form-control" v-model="obj.newPassword" type="password" placeholder="新密码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-4">
                        <input class="form-control" v-model="obj.confirmNewPassword" type="password" placeholder="确认密码">
                    </div>
                </div>
            </div>
            <div class="box-footer">
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-primary" @click="save()">保存</a>
                    </div>
                </div>
            </div>
        </form>
    </div>

</section>
</body>
</html>