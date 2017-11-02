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
              <FormGroup label="上级栏目">
                <div class="input-group">
                  <input type="text" class="form-control" v-model="obj.parent == null ? '' : obj.parent.name"/>
                  <span class="input-group-btn">
    								<button class="btn btn-info" type="button" @click="folderTreeModalConfig.show = true">选择</button>
    							</span>
                </div>
                <SelectGroupModal :config="folderTreeModalConfig" v-model="obj.parent"></SelectGroupModal>
              </FormGroup>
            </div>
          </div>

          <div class="row">
            <div class="col-md-6">
              <FormGroup label="栏目名称">
                <input type="text" class="form-control" v-model="obj.name"/>
              </FormGroup>
            </div>
          </div>

          <div class="row">
            <div class="col-md-6">
              <FormGroup label="URL地址">
                <input type="text" class="form-control" v-model="obj.url"/>
              </FormGroup>
            </div>
          </div>

          <div class="row">
            <div class="col-md-6">
              <FormGroup label="排序号">
                <input type="text" class="form-control" v-model="obj.orderIndex"/>
              </FormGroup>
            </div>
          </div>

          <div class="row">
            <div class="col-md-6">
              <FormGroup label="关键字">
                <input type="text" class="form-control" v-model="obj.keyword"/>
              </FormGroup>
            </div>
          </div>

          <div class="row">
            <div class="col-md-6">
              <FormGroup label="备注">
                <input type="text" class="form-control" v-model="obj.remark"/>
              </FormGroup>
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
  import FormMixin from 'mixins/FormMixin.js'
  import SelectGroupModal from '../common/SelectGroupModal.vue'

  export default {
    mixins: [FormMixin],
    components: {
      SelectGroupModal
    },
    data: () => {
      return {
        actions: {
          get: {method: 'get', url: '/api/cms/group{/id}'},
          save: {method: 'post', url: '/api/cms/group'},
          update: {method: 'put', url: '/api/cms/group'}
        },

        obj: {
          parent: {},
          area: {},
          name: '',
          code: '',
          type: 1,
          grade: 1,
          address: '',
          zipCode: '',
          master: '',
          phone: '',
          fax: '',
          email: '',
          remarks: ''
        },

        folderTreeModalConfig: {
          show: false,
          title: '选择上级栏目'
        }
      }
    }
  }
</script>
