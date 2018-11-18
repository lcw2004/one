# one

本项目衍生自 [jeesite](https://github.com/thinkgem/jeesite "https://github.com/thinkgem/jeesite")，使用的是hibernate版本，除了基础平台的功能，其余的功能都删除了。
另：v2.0版本基本上重新开发了，数据库也有较大改动

## 演示地址
-   [http://104.128.81.94:8080](http://104.128.81.94:8080 "http://104.128.81.94:8080")
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
-   Spring Boot 2.0.5.RELEASE
-   Hibernate 5
-   Shiro 1.4.0
-   activiti 5.22.0
-   swagger 2.8.0

## 前端
-   vue2
-   vuex
-   vue-router
-   axios
-   element-ui
-   webpack
-   [AdminLTE](https://github.com/almasaeed2010/AdminLTE "https://github.com/almasaeed2010/AdminLTE")

# 文档
更多项目的文档请参考 [项目wiki](https://github.com/lcw2004/one/wiki)

* [工程结构说明](https://github.com/lcw2004/one/wiki/工程结构说明)
* 后端开发
  * [基础数据初始化](https://github.com/lcw2004/one/wiki/后端开发-基础数据初始化)
  * [启动后端工程](https://github.com/lcw2004/one/wiki/后端开发-启动后端工程)
  * [异常处理](https://github.com/lcw2004/one/wiki/后端开发-异常处理)
  * [后端参数验证](https://github.com/lcw2004/one/wiki/后端开发-后端参数验证)
  * [Excel导入导出](https://github.com/lcw2004/one/wiki/后端开发-excel导入导出)
  * [获取当前用户信息](https://github.com/lcw2004/one/wiki/后端开发-获取当前用户信息)
* 前端开发
  * [前端工程说明](https://github.com/lcw2004/one/wiki/前端开发-前端工程说明)
  * [启动前端工程](https://github.com/lcw2004/one/wiki/前端开发-启动前端工程)
  * [前端打包优化](https://github.com/lcw2004/one/wiki/前端开发-前端打包优化)
  * [开发技巧](https://github.com/lcw2004/one/wiki/前端开发-开发技巧)
  * 常用VUE组件
    * [Form表单](https://github.com/lcw2004/one/wiki/前端开发-vue组件-form表单)
    * [表单验证](https://github.com/lcw2004/one/wiki/前端开发-vue组件-表单验证)
* 生产环境打包
  * [打包步骤](https://github.com/lcw2004/one/wiki/生产环境打包-打包步骤)
  * [打包脚本](https://github.com/lcw2004/one/wiki/生产环境打包-打包脚本)
  * [持续集成](https://github.com/lcw2004/one/wiki/生产环境打包-持续集成)
* [开发新模块示例](https://github.com/lcw2004/one/wiki/开发新模块示例)
* [优化](https://github.com/lcw2004/one/wiki/优化)
* [常见问题](https://github.com/lcw2004/one/wiki/常见问题)


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
    -   [ ] Demo页面
        -   [ ] 表单以及表单验证以及自定义验证
        -   [ ] 分页组件的使用
        -   [ ] 模态框组件的使用
        -   [ ] 弹出框组件的使用
        -   [ ] 确认框组件的使用
        -   [ ] overlay组件的使用
        -   [ ] Element组件的使用
        -   [ ] 图片预览组件的使用
        -   [ ] 文件上传下载组件的使用
        -   [ ] 字典组件的使用
        -   [ ] 按钮权限组件的使用
        -   [ ] WebSocket的使用
        -   [ ] 后端错误的展现
        -   [ ] 消息的发送以及显示
        -   [ ] 工作流的使用
        -   [ ] 日志功能的结束以及查询
        -   [ ] 代码生成工具的使用
    -   [X] 持续集成
    -	[ ] 点击列表名称进行排序
    -   [X] 添加表单验证
        -   [X] 前端数据校验
        -   [X] 后端数据校验
    -   [ ] 提供单独的建库脚本以及初始化数据脚本
    -   [X] 需要记录每个URL的访问耗时情况，统计起来以便优化
    -	[ ] 将 jeesite 最新版的功能迁移过来
    -   [ ] maven换成gradle
    -   [X] 封装参数验证以及异常处理
    -   [X] 代码生成
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
	-   [X] 角色管理
	-   [X] 用户管理
	-   [X] 机构管理
	-   [X] 区域管理
	-   [X] 日志查询
