<template>
  <section class="content">
    <div class="box">
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
  import FormMixin from 'mixins/FormMixin.js'
  export default {
    mixins: [FormMixin],
    data: () => {
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
        this.resource.getMenuTree().then((response) => {
          let result = response.body
          if (result.ok) {
            this.topMenu = result.data
          }
        })
      }
    }
  }
</script>
