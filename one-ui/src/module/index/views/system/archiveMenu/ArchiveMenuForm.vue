<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">归档目录</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-2 control-label">上级目录</label>
            <div class="col-sm-4">
              <div class="input-group">
                <input type="text" class="form-control" v-model="obj.parent.name"/>
                <span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="archiveMenuModalConfig.show = true">选择</button>
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
            <label class="col-sm-2 control-label">视图</label>
            <div class="col-sm-4">
              <input type="text" class="form-control" v-model="obj.view"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">备注</label>
            <div class="col-sm-4">
              <textarea class="form-control" v-model="obj.remark"></textarea>
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

    <SelectArchiveMenuModal :config="archiveMenuModalConfig" v-model="obj.parent"/>
  </section>
</template>

<script>
import FormMixin from '@mixins/FormMixin'
import SelectArchiveMenuModal from '../modal/SelectArchiveMenuModal.vue'

export default {
  mixins: [FormMixin],
  components: {
    SelectArchiveMenuModal
  },
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getArchiveMenu,
          save: this.$api.system.saveArchiveMenu,
          update: this.$api.system.updateArchiveMenu
        },
        continue: false
      },
      obj: {
        parent: {},
        name: '',
        view: '',
        remark: ''
      },
      archiveMenuModalConfig: {
        show: false,
        title: '选择上级'
      }
    }
  }
}
</script>
