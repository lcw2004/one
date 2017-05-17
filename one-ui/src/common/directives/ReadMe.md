# v-render指令

## 作用
- 用于自动渲染vee-validate的验证结果
- 按照bootstrap的错误规则来渲染结果
  - 错误的时候追加错误信息，并标红
  - 错误的时候在追加错误图标
  - 验证通过的时候显示正确图标
  - 正确的时候将字体颜色改为绿色（颜色不协调，代码注释了）
- 用于控制control-label前面是否显示红色星星表示必填项  

## 用法
注意事项：
- **v-render必须与name保持一致**
- **必须配合vee-validate使用**

修饰符：
- 修饰符r：required的首字母，表示该字段是必填选项，加上该修饰符之后，会自动在control-label前面追加一个红色*表示是必填选项

示例如下：

    <div class="form-group" v-render.r="projectNumber">
      <label class="control-label col-md-4">编号</label>
      <div class="col-md-7">
        <input type="text" class="form-control" v-validate="'required|email'" name="projectNumber" v-model="projectNumber">
      </div>
    </div>

# 其他想法
- 可以将渲染错误信息以及正确信息的方法提取出来，在系统初始化时候传入渲染的方法，即可灵活的修改渲染方式
- 将一些常用的渲染方式集成进来，可以通过指令传入修饰符来控制
- 适配多种需要验证的form表单，目前只适配了input表单的
