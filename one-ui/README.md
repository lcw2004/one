# 项目说明
1.  这是使用vue-cli建立的项目
2.  为了支持多页面，对vue-cli默认的配置文件进行了一些改动，目前是将单个页面的配置放置moudles.js里面
  - 如果要添加新的页面，直接在moudles.js里面添加即可
  - 如果要添加的页面很多，还是把moudles.js改为根据默认约定生成模块吧
3.  UI使用的是adminlte


# 目前支持的功能

## 组件
1.  Tree
2.  TreeTable
3.  notify接口
4.  confirm接口
5.  Editor富文本编辑框
6.  时间选择器
7.  分页组件
8.  TimeLine组件

# 项目结构

## src/components
  存放公共组件，每个组件一个文件夹，所有公共组件统一在components/index.js里面注册为全局组件

## src/mixins
  存放公共的mixin

## src/filters
  存放公共过滤器

## src/utils
  存放公共的utils方法

## src/vuex
  存放store，store模块化，拆分到不同的模块下面

## src/router
  存放路由，路由模块化，根据不同模板将路由放到不同的js里面

## src/view
  存放业务相关组件，每个模块一个文件夹


---
# 开发

## 环境准备

1.	安装最新版NodeJS
2.	安装最新版npm
3.	进入vue-adminlte目录

## 运行项目

	npm install

	# 开发运行环境;运行命令后，浏览器自动打开http://localhost:8000
	npm run dev

	# 开发完成后打包命令，会生成dist文件夹，不要在本地打开，请安装类似http-server来运行
	npm run build

## 其他

-	使用eslint语法规范JS代码
-	使用淘宝镜像加速npm
-	自定义Atom模板，快速填充vue代码
	-	将doc/snippets.cson文件内容拷贝到Atom snippets里面即可（可以参考这个文件定义自己的快捷键）
	-	常用快捷键
		-	vuea
		-	vuem
		-	bform
		-	bform-*
		-	bsection
		-	bbox
		-	bbox-solid
		-	blabel-*
		-	notify
		-	confirm

# 参考

1.	[https://github.com/luchanan/vue2.0-multi-page](https://github.com/luchanan/vue2.0-multi-page)
2.	[https://github.com/jiananle/vue-multi-page](https://github.com/jiananle/vue-multi-page)
3.	[https://github.com/bluefox1688/vue-cli-multi-page](https://github.com/bluefox1688/vue-cli-multi-page)
4.	[https://github.com/jarvan4dev/vue-multi-page](https://github.com/jarvan4dev/vue-multi-page)
