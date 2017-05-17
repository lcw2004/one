<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <h3 class="box-title">菜单信息</h3>
    </div>
    <div class="box-body">
      <form class="form-horizontal">
        <div class="form-group">
          <label class="col-sm-2 control-label">上级机构</label>
          <div class="col-sm-4">
            <div class="input-group">
              <input type="text" class="form-control" v-model="obj.parent == null ? '' : obj.parent.name" />
              <span class="input-group-btn">
								<button class="btn btn-info" type="button" @click="officeTreeModalConfig.show = true">选择</button>
							</span>
            </div>
            <SelectOfficeModal :config="officeTreeModalConfig" v-model="obj.parent"></SelectOfficeModal>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">归属区域</label>
          <div class="col-sm-4">
            <div class="input-group">
              <input type="text" class="form-control" v-model="obj.area.name" />
              <span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="areaTreeModalConfig.show = true">选择</button>
								</span>
            </div>
            <SelectAreaModal :config="areaTreeModalConfig" v-model="obj.area"></SelectAreaModal>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">机构名称</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.name" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">机构编码</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.code" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">机构类型</label>
          <div class="col-sm-4">
            <DictSelect v-model="obj.type" type="sys_office_type"></DictSelect>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">机构级别</label>
          <div class="col-sm-4">
            <DictSelect v-model="obj.type" type="sys_office_grade"></DictSelect>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">联系地址</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.address" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">邮政编码</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.zipCode" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">负责人</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.master" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">电话</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.phone" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">传真</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.fax" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">邮箱</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.email" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">备注</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" v-model="obj.remarks" />
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
import SelectOfficeModal from '../modal/SelectOfficeModal'
import SelectAreaModal from '../modal/SelectAreaModal'
import FormMixin from '../../../../../common/mixins/FormMixin.js'

export default {
  mixins: [FormMixin],
  components: {
    SelectOfficeModal,
    SelectAreaModal
  },
  data: function () {
    return {
      actions: {
        get: { method: 'get', url: '/api/sys/office{/id}' },
        save: { method: 'post', url: '/api/sys/office' },
        update: { method: 'put', url: '/api/sys/office' }
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

      officeTreeModalConfig: {
        show: false,
        title: '选择上级机构'
      },

      areaTreeModalConfig: {
        show: false,
        title: '选择归属区域'
      }
    }
  }
}
</script>
