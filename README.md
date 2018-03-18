# one

本项目衍生自 [jeesite](https://github.com/thinkgem/jeesite "https://github.com/thinkgem/jeesite")，使用的是hibernate版本，除了基础平台的功能，其余的功能都删除了。
另：v2.0版本基本上重新开发了，数据库也有较大改动

## 演示地址
-   [http://one.lumuxi.me](http://one.lumuxi.me "http://one.lumuxi.me")
-   登录名：admin，密码：admin
-   服务器在国外，访问起来会有些慢，另初次加载会慢一些。

## 在Jeesite上的改动
-	改为Spring Boot工程
-	前后端完全分离，后端只提供Rest API，
-   前端使用了Vue渲染网页，数据与展示分离。
    -   目前的前端组件（翻页组件、Tree组件、Tree Table组件）都是自定义的，易于修改。
    -   前端代码较少且更为清晰
-   所有的依赖都已经升级为最新版，且会定期升级
-   用redis存储登录用户信息


# 技术栈
## 后端
-   Spring Boot 1.5.10
-   Hibernate 5
-   Shiro 1.4.0
-   activiti 5.22.0
-   swagger 2.6.1

## 前端
-   vue2
-   vuex
-   vue-router
-   axios
-   webpack
-   [AdminLTE](https://github.com/almasaeed2010/AdminLTE "https://github.com/almasaeed2010/AdminLTE")

# 文档
更多项目的文档请参考 [项目wiki](https://github.com/lcw2004/one/wiki)

[项目结构说明](https://github.com/lcw2004/one/wiki/%25E5%25B7%25A5%25E7%25A8%258B%25E7%25BB%2593%25E6%259E%2584%25E8%25AF%25B4%25E6%2598%258E)

[后端接口开发](https://github.com/lcw2004/one/wiki/%E5%BC%80%E5%8F%91%EF%BC%88%E5%90%8E%E7%AB%AF%E6%8E%A5%E5%8F%A3%EF%BC%89)

[前端界面开发](https://github.com/lcw2004/one/wiki/%E5%BC%80%E5%8F%91%EF%BC%88%E5%89%8D%E7%AB%AF%E7%95%8C%E9%9D%A2%EF%BC%89)

[生产环境打包](https://github.com/lcw2004/one/wiki/%E6%89%93%E5%8C%85)

[优化](https://github.com/lcw2004/one/wiki/%E4%BC%98%E5%8C%96)

# 数据库

-   数据库：暂时未提供数据库初始化代码（后期加上），只能提供一个navicat导出的SQL文件，document/one - mysql(navicat).sql

# 运行程序

-   redis：需要配置redis
-   命令行方式运行：在项目根目录运行以下命令即可运行程序：

        mvn clean package
        cd one-ui
        java -jar one-ui/target/one-ui-1.0.1.war
        
-   开发工具运行：在开发工具中运行OneUIApplication.java
-   代码热更新:
    -   IDEA: Ctrl + F9编译完成自动更新
    -   Eclipse: 修改完成保存就会自动更新

# 待完成功能
1.  整体功能
    
    待完成：

    -   [ ] 持续集成
    -	[ ] 点击列表名称进行排序
    -   [ ] 添加表单验证
        -   [X] 前端数据校验
        -   [ ] 后端数据校验
    -   [ ] 提供单独的建库脚本以及初始化数据脚本
    -   [ ] 需要记录每个URL的访问耗时情况，统计起来以便优化
    -	[ ] 将 jeesite 最新版的功能迁移过来
    -   [ ] maven换成gradle
    -   [ ] 封装参数验证以及异常处理
    -   [ ] 代码生成
    -   [ ] 将 Hibernate 换成其他的（目前暂不考虑更换）

    已完成：
    
    -   [X] 用 Vue2 重写前端页面
    -	[X] 分模块，将工程根据不同的业务分为多个工程
    -	[X] 将 jeesite 依赖的组件升级到高版本
    -   [X] 进一步前后端分离，改为Single Page Application
    -   [X] 搭建工作流服务
    -   [X] 拆分单点登录服务，管理多个系统
    -   [X] 引入Swagger生成REST接口文档

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
    