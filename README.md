# one

本项目衍生自 [jeesite](https://github.com/thinkgem/jeesite "https://github.com/thinkgem/jeesite")，使用的是hibernate版本，除了基础平台的功能，其余的功能都删除了。

**这段时间工作上有些忙，暂时没继续开发，年后会接着按计划进行开发**

## 演示地址
-   [http://www.lumuxi.me/one](http://www.lumuxi.me/one/ "http://www.lumuxi.me/one/")
-   登录名：admin，密码：admin
-   服务器在国外，访问起来会有些慢

# 技术栈
-   Spring Boot
-   Vue2
    -   vue-router
    -   vuex
    -   vux-resource
    -   vee-validate
-   Hibernate 4
-   Shiro

# 在Jeesite上的改动
-	支持Spring Boot
-	前后端分离，后端提供Rest API（同一套API可以供App以及Wap调用）
-   前端使用了Vue渲染网页，数据与展示分离。
    -   目前的前端组件（翻页组件、Tree组件、Tree Table组件）都是自定义的，易于修改。
    -   前端代码较少且更为清晰
-	前端UI改为 [AdminLTE](https://github.com/almasaeed2010/AdminLTE "https://github.com/almasaeed2010/AdminLTE")。

# 部署
-   数据库：目前数据库还是用的Jeesite的，还未有任何改动
-   签出之后在项目根目录运行以下命令即可运行程序：

        mvn spring-boot:run

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
	-	[X] 将 jeesite 依赖的组件升级到高版本
	-	[ ] 将 jeesite 最新版的功能迁移过来
	-   [ ] 将 Hibernate 换成其他的
	-   [ ] (进行中) 进一步前后端分离，改为Single Page Application
    -   [ ] maven换成gradle
	-   [ ] 搭建工作流服务
    -   [ ] 封装参数验证以及异常处理
    -   [ ] 代码生成
    -   [ ] 搭建单点登录服务，管理多个系统
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
