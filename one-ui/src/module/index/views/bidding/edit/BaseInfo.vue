<template>
<div class="box-body">
  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">采购类别</label>
        <div class="col-md-7">
          <div class="input-group">
            <input type="text" class="form-control">
            <span class="input-group-btn">
              <button type="button" class="btn btn-primary btn-flat">选择</button>
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-3">项目类型</label>
        <div class="col-md-9">
          <div class="controls">
            <div class="radio inline">
              <label><input type="radio" value="1">邀请招标</label>
            </div>
            <div class="radio inline">
              <label><input type="radio" value="2">公开招标</label>
            </div>
            <div class="radio inline">
              <label><input type="radio" value="3">定向开标</label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group" v-render.r="项目编号">
        <label class="control-label col-md-4">项目编号</label>
        <div class="col-md-7">
          <input type="text" class="form-control" v-validate="'required|email'" name="项目编号" v-model="projectNumber">
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-3">决标方式</label>
        <div class="col-md-9">
          <div class="controls">
            <div class="radio inline">
              <label><input type="radio" value="1">专家决标</label>
            </div>
            <div class="radio inline">
              <label><input type="radio" value="2">价格决标</label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group has-feedback" v-render="项目名称">
        <label class="control-label col-md-4">项目名称</label>
        <div class="col-md-7">
          <input type="text" class="form-control" v-validate="'required|email'" name="项目名称" v-model="projectName">
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-3">执行策略</label>
        <div class="col-md-9">
          <div class="controls">
            <div class="radio inline">
              <label><input type="radio" value="1">线下招标</label>
            </div>
            <div class="radio inline">
              <label><input type="radio" value="2">网上招标</label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">采购部门</label>
        <div class="col-md-7">
          <div class="input-group">
            <input type="text" class="form-control">
            <span class="input-group-btn">
              <button type="button" class="btn btn-primary btn-flat">选择</button>
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-3">是否唱标</label>
        <div class="col-md-9">
          <div class="controls">
            <div class="radio inline">
              <label><input type="radio" value="1">是</label>
            </div>
            <div class="radio inline">
              <label><input type="radio" value="2">否</label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">报名联系人</label>
        <div class="col-md-7">
          <div class="input-group">
            <input type="text" class="form-control">
            <span class="input-group-btn">
              <button type="button" class="btn btn-primary btn-flat">选择</button>
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-3">报价类型</label>
        <div class="col-md-9">
          <div class="controls">
            <div class="radio inline">
              <label><input type="radio" value="1">含税价</label>
            </div>
            <div class="radio inline">
              <label><input type="radio" value="2">去税价</label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <div class="form-group">
        <label class="control-label col-md-2">投标资格要求</label>
        <div class="col-md-9">
          <Editor height="80"></Editor>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <div class="form-group">
        <label class="control-label col-md-2">项目概况</label>
        <div class="col-md-9">
          <Editor height="80"></Editor>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <div class="form-group">
        <label class="control-label col-md-2">备注</label>
        <div class="col-md-9">
          <Editor height="80"></Editor>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  components: {
  },
  data: function () {
    return {
      projectNumber: '',
      projectName: '',
      projectName1: ''
    }
  }
}
</script>
