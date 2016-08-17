# one

本项目衍生自 [jeesite](https://github.com/thinkgem/jeesite "https://github.com/thinkgem/jeesite")，使用的是hibernate版本，除了基础平台的功能，其余的功能都删除了。

## 演示地址
-   [http://104.128.81.94:8080/one/a](http://104.128.81.94:8080/one/a "http://104.128.81.94:8080/one/a")
-   登录名：admin，密码：admin
-   服务器在国外，访问起来会有些慢

# 在Jeesite上的改动 #
-	支持Spring Boot
-	前后端分离，前端通过Rest API调用后端数据（主要是为了做App以及手机端应用）
-	前端UI改为 [AdminLTE](https://github.com/almasaeed2010/AdminLTE "https://github.com/almasaeed2010/AdminLTE")，目前正在改动中，前端大部分样式错乱。

# 待完成功能
1.  整体功能

	-   [ ] 用 Vue 重写前端页面
	-	[ ] 点击列表名称进行排序
	-	[ ] 将 jeesite 依赖的组件升级到高版本
	-	[ ] 将 jeesite 最新版的功能迁移过来
	-	[ ] 分模块，将工程根据不同的业务分为多个工程

2.  模块功能

	-   [X] 字典管理
	-   [X] 菜单管理
	-   [X] 菜单栏显示菜单的LOGO	
	-   [ ] 角色管理
	-   [ ] 用户管理
	-   [ ] 机构管理
	-   [ ] 区域管理
	-   [ ] 日志查询
