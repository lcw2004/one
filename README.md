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
-   依赖的组件升级为最新版，且会定期升级


# 技术栈
## 后端
-   Spring Boot 1.5.3
-   Hibernate 5
-   Shiro 1.4.0
-   activiti 5.22.0
-   swagger 2.6.1

## 前端
-   vue2
-   vuex
-   vue-router
-   vue-resource
-   webpack
-   [AdminLTE](https://github.com/almasaeed2010/AdminLTE "https://github.com/almasaeed2010/AdminLTE")


# 开发
## 工程结构说明

-   one-main：

    Spring Boot入口工程，包含：Spring Boot启动类、整个系统的configuration、全局异常处理
    
-   one-util

    一些公共的方法以及公共的类

-   one-entity

    数据库实体类、实体类的枚举（目前实体类暂时放在一起，不同模块的实体类放到不同的包结构下面）

-   one-user

    用户相关工程

-   one-sys

    系统管理工程

-   one-notify

    通知类工程（邮件、短信、微信、系统消息等）

-   one-login

    登录工程

-   one-code-gen

    代码生成，目前代码生成只能由实体类生成Dao、Service、RestController，还没做到由数据库生成Bean。
    目前我的代码生成流程是：
    1.  用idea由数据库生成Bean
    2.  在CodeGen.java指定Bean所在的包名，扫描包下面的所有实体类，生成Dao、Service、Controller

-   one-workflow
    
    工作流

-   one-file-server

    文件存储服务，目前只实现了本地存储实现，云存储目前还没做
    
-   one-ui
    
    UI工程，放置前端代码的工程
    
## 后端接口开发

-   启动：

    运行one-main工程的OneMainApplication即可启动
    
-   开发流程：
    
    1.  建业务表
    2.  用idea由数据库生成实体bean
    3.  将document/one-new.rar解压作为一个业务工程模板，并放置在one文件夹下面，在one/pom.xml的module中添加业务工程
    4.  在one-code-gen中的CodeGen类中，传入第2步中生成的bean所在的包名以及第3步中的模块名
    5.  在生成的代码上二次开发
    
-   测试：
    
    访问http://localhost:8080/swagger-ui.html对接口进行测试
    
## 前端接口开发

注：需要安装nodejs

-   启动：

    1.  命令行进入one-ui文件夹
    2.  运行`npm install`
    3.  运行`npm run dev`启动前端工程
    
更多细节参考one-ui的readme 

## 数据库

目前还没有整理数据库初始化脚本，暂时只有一个navicat导出的mysql数据，在document下面

# 打包

## 前端打包

1.  命令行进入one-ui文件夹
2.  运行`npm run build`编译前端工程
3.  编译出来的文件在one-ui/dist文件夹下面

## 后端打包

1.  将one-ui/dist文件夹下面的编译出来的文件拷贝到one-main/src/main/resources/static文件夹下面（打包完成就删掉拷贝过去的内容，没必要提交）
2.  **将所有的html文件中的/static改为./static**，一定要替换，否则找不到静态资源文件
3.  进入one文件夹
4.  执行命令编译：`mvn clean pakcage -Dmaven.test.skip=true`
5.  执行命令启动程序：`java -jar one-main\target\one-main-0.0.1-SNAPSHOT.jar`
6.  访问`http://localhost:8080/login.html`登录系统

# 运行程序
-   数据库：目前数据库还是用的Jeesite的，还未有任何改动
-   命令行方式运行：在项目根目录运行以下命令即可运行程序：

        mvn clean package
        cd one-ui
        java -jar one-ui/target/one-ui-1.0.1.war
        
-   开发工具运行：在开发工具中运行OneUIApplication.java


# 待完成功能
1.  整体功能
    
    待完成：

        -	[ ] 点击列表名称进行排序
        -   [ ] 添加表单验证
            -   [ ] 前端数据校验
            -   [ ] 后端数据校验
        -   [ ] 提供单独的建库脚本以及初始化数据脚本
        -   [ ] 需要记录每个URL的访问耗时情况，统计起来以便优化
        -	[ ] 将 jeesite 最新版的功能迁移过来
        -   [ ] maven换成gradle
        -   [ ] 封装参数验证以及异常处理
        -   [ ] 代码生成
        -   [ ] 将 Hibernate 换成其他的

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
    