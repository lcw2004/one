# one

本项目衍生自 [jeesite](https://github.com/thinkgem/jeesite "https://github.com/thinkgem/jeesite")，使用的是hibernate版本，除了基础平台的功能，其余的功能都删除了。


## 演示地址
-   [http://www.lumuxi.me/one](http://www.lumuxi.me/one/ "http://www.lumuxi.me/one/")
-   登录名：admin，密码：admin
-   服务器在国外，访问起来会有些慢


# 技术栈
-   后端
    -   Spring Boot 1.5.2
    -   Hibernate 4
    -   Shiro
-   前端
    -   webpack
    -   Vue2
        -   vue-router
        -   vuex
        -   vux-resource
        -   vee-validate
        

# 在Jeesite上的改动
-	改为 Spring Boot 工程
-	前后端分离，后端提供Rest API（同一套API可以供App以及Wap调用）
-   前端使用了Vue渲染网页，数据与展示分离。
    -   目前的前端组件（翻页组件、Tree组件、Tree Table组件）都是自定义的，易于修改。
    -   前端代码较少且更为清晰
-	前端UI改为 [AdminLTE](https://github.com/almasaeed2010/AdminLTE "https://github.com/almasaeed2010/AdminLTE")。


# 运行程序
-   数据库：目前数据库还是用的Jeesite的，还未有任何改动
-   命令行方式运行：在项目根目录运行以下命令即可运行程序：

        mvn clean package
        cd one-ui
        java -jar one-ui/target/one-ui-1.0.1.war
        
-   开发工具运行：在开发工具中运行OneUIApplication.java


# 待完成功能
1.  整体功能

	-   [X] 用 Vue2 重写前端页面
	-	[ ] 点击列表名称进行排序
	-   [ ] 添加表单验证
	    -   [ ] 前端数据校验
	    -   [ ] 后端数据校验
	-   [ ] 提供单独的建库脚本以及初始化数据脚本
	-   [ ] 需要记录每个URL的访问耗时情况，统计起来以便优化
	-	[X] 分模块，将工程根据不同的业务分为多个工程
	-	[ ] 将 jeesite 依赖的组件升级到高版本
	-	[ ] 将 jeesite 最新版的功能迁移过来
	-   [ ] 将 Hibernate 换成其他的
	-   [ ] (进行中) 进一步前后端分离，改为Single Page Application
    -   [ ] maven换成gradle
	-   [ ] 搭建工作流服务
    -   [ ] 封装参数验证以及异常处理
    -   [ ] 代码生成
    -   [ ] 拆分单点登录服务，管理多个系统
    -   [ ] 引入Swagger生成REST接口文档

2.  模块功能

	-   [X] 字典管理
	-   [X] 菜单管理
	-   [X] 菜单栏显示菜单的LOGO
	-   [X] 角色管理
	-   [X] 用户管理
	-   [X] 机构管理
	-   [X] 区域管理
	-   [X] 日志查询

# 其他

## 参数验证
在需要验证的Bean或者参数上面添加@Valid 即可。

程序会自动按以下流程处理验证结果：
1.  在 Rest 接口接口参数上面添加 @Valid
2.  用 NotValidExceptionAdvice 将异常拦截下来，包装为自定义报文，以 JSON 的形式返回给接口调用方（vue-resource），并返回400错误
3.  vue-resource 中统一用拦截器将 400 错误拦截下来，并解析报文得到错误消息，弹框提示用户

## Http状态码
1.  200 成功
2.  400
    -   常规异常
    -   异常处理：vue-resource拦截下来并提示错误信息
3.  401
    -   获取登录用户信息失败
    -   长时间不操作，自动退出登录
    -   异常处理：vue-resource拦截下来并跳转到登录界面重新登录
    