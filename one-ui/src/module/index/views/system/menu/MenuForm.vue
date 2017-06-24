<template>
  <section class="content">
    <div class="box">
      <div class="box-header">
        <h3 class="box-title">菜单信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-2 control-label">上级菜单</label>
            <div class="col-sm-4">
              <div class="input-group">
                <input type="text" class="form-control" v-model="obj.parent.name"/>
                <span class="input-group-btn">
								<button class="btn btn-info" type="button" @click="menuTreeModalConfig.show = true">选择</button>
							</span>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">名称</label>
            <div class="col-sm-4">
              <input type="text" class="form-control" v-model="obj.name"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">链接</label>
            <div class="col-sm-4">
              <input type="text" class="form-control" v-model="obj.href"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">排序</label>
            <div class="col-sm-4">
              <input type="number" class="form-control" v-model="obj.sort"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">图标</label>
            <div class="col-sm-4">
              <p class="form-control-static">
                <i v-if="obj.icon" :class="obj.icon" style="font-size: 25px"></i>
                <button class="btn btn-info btn-sm" type="button" @click="iconModalConfig.show = true">选择</button>
              </p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">可见</label>
            <div class="col-sm-4">
              <DictRadio type="show_hide" v-model="obj.isShow"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">权限标识</label>
            <div class="col-sm-4">
              <input type="text" class="form-control" v-model="obj.permission"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">备注</label>
            <div class="col-sm-4">
              <textarea class="form-control" v-model="obj.remarks"></textarea>
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

      <SelectIconModal :config="iconModalConfig" v-model="obj.icon"/>
      <SelectMenuModal :config="menuTreeModalConfig" v-model="obj.parent"/>
    </div>
  </section>
</template>

<script>
  import SelectIconModal from '../modal/SelectIconModal'
  import SelectMenuModal from '../modal/SelectMenuModal'
  import FormMixin from '../../../../../common/mixins/FormMixin.js'

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
        this.obj.sort = this.obj.sort + 1
      }
    }
  }
</script>
