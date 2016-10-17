# one

本项目衍生自 [jeesite](https://github.com/thinkgem/jeesite "https://github.com/thinkgem/jeesite")，使用的是hibernate版本，除了基础平台的功能，其余的功能都删除了。

## 演示地址
-   [http://www.lumuxi.me/one](http://www.lumuxi.me/one/ "http://www.lumuxi.me/one/")
-   登录名：admin，密码：admin
-   服务器在国外，访问起来会有些慢

# 在Jeesite上的改动
-	支持Spring Boot
-	前后端分离，后端提供Rest API（同一套API可以供App以及Wap调用）
-   前端使用了Vue渲染网页，数据与展示分离。
    -   目前的前端组件（翻页组件、Tree组件、Tree Table组件）都是自定义的，易于修改。
    -   前端代码较少且更为清晰
-	前端UI改为 [AdminLTE](https://github.com/almasaeed2010/AdminLTE "https://github.com/almasaeed2010/AdminLTE")，目前正在改动中，前端大部分样式错乱。

# 部署
-   数据库：目前数据库还是用的Jeesite的，还未有任何改动
-   签出之后在项目根目录运行以下命令即可运行程序：

        mvn spring-boot:run

# 待完成功能
1.  整体功能

	-   [X] 用 Vue 重写前端页面
	-	[ ] 点击列表名称进行排序
	-   [ ] 提供单独的建库脚本以及初始化数据脚本
	-   [ ] 需要记录每个URL的访问耗时情况，统计起来以便优化
	-	[ ] 分模块，将工程根据不同的业务分为多个工程
	-	[ ] 将 jeesite 依赖的组件升级到高版本
	-	[ ] 将 jeesite 最新版的功能迁移过来
	-   [ ] 进一步前后端分离，改为Single Page Application

2.  模块功能

	-   [X] 字典管理
	-   [X] 菜单管理
	-   [X] 菜单栏显示菜单的LOGO	
	-   [X] 角色管理
	-   [X] 用户管理
	-   [X] 机构管理
	-   [X] 区域管理
	-   [X] 日志查询
