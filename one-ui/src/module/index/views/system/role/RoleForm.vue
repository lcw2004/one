<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">角色信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="角色名称">
                <input type="text" class="form-control" v-model="obj.name" v-validate="'required'" name="角色名称" />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="数据范围">
                <DictSelect v-model="obj.dataScope" type="sys_data_scope"></DictSelect>
              </FormGroup>
            </div>
            <div class="col-md-6">
              <p class="help-block">特殊情况下，设置为“按明细设置”，可进行跨机构授权</p>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="备注">
                <textarea v-model="obj.remarks" class="form-control" rows="3" maxlength="200"></textarea>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="角色授权">
                <Tree :element="topMenu" v-model="obj.sysMenuEOIdList" select-type="checkbox"></Tree>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="首页组件">
                <template v-for="component of homeComponentList">
                  <div class="checkbox">
                    <label>
                      <input type="checkbox" v-model="obj.componentIdList" :key="component.componentId" :value="component.componentId">{{ component.componentTitle}}
                    </label>
                  </div>
                </template>
              </FormGroup>
            </div>
          </div>
        </form>
      </div>
      <div class="box-footer">
        <div class="row">
          <div class="col-md-2 col-md-offset-2">
            <a class="btn btn-block btn-primary" @click="validAndSave()">保存</a>
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
import FormMixin from '@mixins/FormMixin'

export default {
  mixins: [FormMixin],
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getRole,
          save: this.$api.system.saveRole,
          update: this.$api.system.updateRole
        },
        continue: false
      },
      obj: {
        office: {},
        name: '',
        dataScope: '',
        remarks: '',
        isDefault: 0,
        sysMenuEOIdList: [],
        componentIdList: []
      },
      topMenu: {},
      homeComponentList: [],
      companyTreeModalConfig: {
        show: false,
        title: '选择所属机构'
      }
    }
  },
  mounted () {
    this.loadMenu()
    this.loadHomeComponent()
  },
  methods: {
    loadHomeComponent () {
      this.$api.system.listHomeComponent().then((response) => {
        let result = response.data
        if (result.ok) {
          this.homeComponentList = result.data
        }
      })
    },
    loadMenu () {
      this.$api.system.getMenuTree().then((response) => {
        let result = response.data
        if (result.ok) {
          this.topMenu = result.data
        }
      })
    }
  }
}
</script>
