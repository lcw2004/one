<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <h3 class="box-title">菜单信息</h3>
    </div>
    <div class="box-body">
      <form class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="上级菜单">
              <div class="input-group">
                <input type="text" class="form-control" v-model="obj.parent.name" v-validate="'required'" name="上级菜单"/>
                <span class="input-group-btn">
                  <button class="btn btn-info" type="button" @click="menuTreeModalConfig.show = true">选择</button>
                </span>
              </div>
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="菜单名称">
              <input type="text" class="form-control" v-model="obj.name" v-validate="'required'" name="菜单名称"/>
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="菜单链接">
              <input type="text" class="form-control" v-model="obj.href" />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="排序">
              <input type="number" class="form-control" v-model="obj.sort" />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="菜单LOGO">
              <p class="form-control-static">
                <i v-if="obj.icon" :class="obj.icon" style="font-size: 25px"></i>
                <button class="btn btn-info btn-sm" type="button" @click="iconModalConfig.show = true">选择</button>
              </p>
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="是否可见">
              <DictRadio type="show_hide" v-model="obj.isShow" />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="权限标识">
              <input type="text" class="form-control" v-model="obj.permission" />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="备注">
              <textarea class="form-control" v-model="obj.remarks" maxlength="200" rows="3"></textarea>
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

    <SelectIconModal :config="iconModalConfig" v-model="obj.icon" />
    <SelectMenuModal :config="menuTreeModalConfig" v-model="obj.parent" />
  </div>
</section>
</template>

<script>
  import SelectIconModal from '../modal/SelectIconModal'
  import SelectMenuModal from '../modal/SelectMenuModal'
  import FormMixin from 'mixins/FormMixin.js'

  export default {
    mixins: [FormMixin],
    components: {
      SelectIconModal,
      SelectMenuModal
    },
    data: () => {
      return {
        actions: {
          get: {method: 'get', url: '/api/sys/menu{/id}'},
          save: {method: 'post', url: '/api/sys/menu'},
          update: {method: 'put', url: '/api/sys/menu'}
        },

        obj: {
          isShow: 1,
          parent: {},
          name: '',
          href: '',
          sort: 1,
          delFlag: 0,
          icon: '',
          permission: '',
          remarks: ''
        },

        menuTreeModalConfig: {
          show: false,
          title: '选择上级菜单'
        },

        iconModalConfig: {
          show: false,
          title: '选择图标'
        }
      }
    },
    methods: {
      clear () {
        this.obj.name = ''
        this.obj.href = ''
        this.obj.icon = ''
        this.obj.permission = ''
        this.obj.remarks = ''
        this.obj.sort = Number.parseInt(this.obj.sort) + 1
      }
    }
  }
</script>
