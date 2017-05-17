<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <h3 class="box-title">角色信息</h3>
    </div>
    <div class="box-body">
      <form class="form-horizontal">
        <div class="form-group">
          <label class="col-sm-2 control-label">角色名称</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.name" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">数据范围</label>
          <div class="col-sm-4">
            <DictSelect v-model="obj.dataScope" type="sys_data_scope"></DictSelect>
            <p class="help-block">特殊情况下，设置为“按明细设置”，可进行跨机构授权</p>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">备注</label>
          <div class="col-sm-4">
            <textarea v-model="obj.remarks" class="form-control" rows="3"></textarea>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">角色授权</label>
          <div class="col-sm-4">
            <Tree :element="topMenu" v-model="obj.sysMenuEOIdList" select-type="checkbox"></Tree>
          </div>
        </div>
      </form>
    </div>
    <div class="box-footer">
      <div class="row">
        <div class="col-md-2 col-md-offset-2">
          <a class="btn btn-block btn-primary" @click="save()">保存</a>
        </div>
        <div class="col-md-2">
          <a class="btn btn-block btn-default" @click="$router.go(-1)">返回</a>
        </div>
      </div>
    </div>
  </div>
</section>
</template>

<script>
import FormMixin from '../../../../../common/mixins/FormMixin.js'
export default {
  mixins: [FormMixin],
  data: function () {
    return {
      actions: {
        getMenuTree: {method: 'get', url: '/api/sys/menu/tree'},
        get: {method: 'get', url: '/api/sys/role{/id}'},
        save: {method: 'post', url: '/api/sys/role'},
        update: {method: 'put', url: '/api/sys/role'}
      },

      obj: {
        office: {},
        name: '',
        dataScope: '',
        remarks: '',
        isDefault: 0,
        sysMenuEOIdList: []
      },

      topMenu: {},

      companyTreeModalConfig: {
        show: false,
        title: '选择所属机构'
      }
    }
  },
  mounted () {
    this.loadMenu()
  },
  methods: {
    loadMenu () {
      this.resource.getMenuTree().then(function (response) {
        let result = response.body
        if (result.ok) {
          this.topMenu = result.data
        }
      })
    }
  }
}
</script>
