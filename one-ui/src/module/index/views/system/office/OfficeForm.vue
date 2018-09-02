<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">机构信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <section>
            <h4 class="page-header">基本信息</h4>

            <div class="row">
              <div class="col-md-5">
                <FormGroup label="上级机构" :required="true">
                  <div class="input-group">
                    <input type="text" class="form-control" v-model="parentName" v-validate="'required'" name="上级机构" readonly/>
                    <span class="input-group-btn">
                      <button class="btn btn-info" type="button" @click="officeTreeModalConfig.show = true">选择</button>
                    </span>
                  </div>
                  <SelectOfficeModal :config="officeTreeModalConfig" v-model="obj.parent"></SelectOfficeModal>
                </FormGroup>
              </div>
              <div class="col-md-5">
                <FormGroup label="归属区域" :required="true">
                  <div class="input-group">
                    <input type="text" class="form-control" v-model="areaName" v-validate="'required'" name="归属区域" readonly/>
                    <span class="input-group-btn">
                      <button class="btn btn-info" type="button" @click="areaTreeModalConfig.show = true">选择</button>
                    </span>
                  </div>
                  <SelectAreaModal :config="areaTreeModalConfig" v-model="obj.area"></SelectAreaModal>
                </FormGroup>
              </div>
            </div>
            <div class="row">
              <div class="col-md-5">
                <FormGroup label="机构名称" :required="true">
                  <input type="text" class="form-control" v-model="obj.name" v-validate="'required'" name="机构名称"/>
                </FormGroup>
              </div>
              <div class="col-md-5">
                <FormGroup label="机构编码" :required="true">
                  <input type="text" class="form-control" v-model="obj.code" v-validate="'required'" name="机构编码"/>
                </FormGroup>
              </div>
            </div>
            <div class="row">
              <div class="col-md-5">
                <FormGroup label="机构类型">
                  <DictSelect v-model="obj.type" type="sys_office_type"></DictSelect>
                </FormGroup>
              </div>
              <div class="col-md-5">
                <FormGroup label="机构级别">
                  <DictSelect v-model="obj.type" type="sys_office_grade"></DictSelect>
                </FormGroup>
              </div>
            </div>
          </section>

          <section>
            <h4 class="page-header">联系方式</h4>
            <div class="row">
              <div class="col-md-5">
                <FormGroup label="负责人">
                  <div class="input-group">
                    <input type="text" class="form-control" v-model="userName" readonly/>
                    <span class="input-group-btn">
                      <button class="btn btn-info" type="button" @click="managerUserModalConfig.show = true">选择</button>
                    </span>
                  </div>
                  <SelectSingleManageUserModal :config="managerUserModalConfig" v-model="obj.masterUserInfo"></SelectSingleManageUserModal>
                </FormGroup>
              </div>
              <div class="col-md-5">
                <FormGroup label="联系地址">
                  <input type="text" class="form-control" v-model="obj.address" />
                </FormGroup>
              </div>
            </div>
            <div class="row">
              <div class="col-md-5">
                <FormGroup label="邮政编码">
                  <input type="text" class="form-control" v-model="obj.zipCode" v-validate="'zipCode'" name="邮政编码"/>
                </FormGroup>
              </div>
              <div class="col-md-5">
                <FormGroup label="电话">
                  <input type="text" class="form-control" v-model="obj.phone" v-validate="'phone'" name="电话"/>
                </FormGroup>
              </div>
            </div>
            <div class="row">
              <div class="col-md-5">
                <FormGroup label="传真">
                  <input type="text" class="form-control" v-model="obj.fax" v-validate="'fax'" name="传真"/>
                </FormGroup>
              </div>
              <div class="col-md-5">
                <FormGroup label="邮箱">
                  <input type="text" class="form-control" v-model="obj.email" v-validate="'email'" name="邮箱"/>
                </FormGroup>
              </div>
            </div>
          </section>
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
import SelectOfficeModal from '../modal/SelectOfficeModal'
import SelectSingleManageUserModal from '../../user/modal/SelectSingleManageUserModal.vue'
import SelectAreaModal from '../modal/SelectAreaModal'
import FormMixin from '@mixins/FormMixin'

export default {
  mixins: [FormMixin],
  components: {
    SelectSingleManageUserModal,
    SelectOfficeModal,
    SelectAreaModal
  },
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getOffice,
          save: this.$api.system.saveOffice,
          update: this.$api.system.updateOffice
        },
        continue: false
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
        remarks: '',
        otherInfoMap: {
        }
      },
      officeTreeModalConfig: {
        show: false,
        title: '选择上级机构'
      },
      areaTreeModalConfig: {
        show: false,
        title: '选择归属区域'
      },
      managerUserModalConfig: {
        show: false,
        title: '选择负责人'
      }
    }
  },
  computed: {
    parentName: function () {
      return this.obj.parent ? this.obj.parent.name : ''
    },
    areaName: function () {
      return this.obj.area ? this.obj.area.name : ''
    },
    userName: function () {
      return this.obj.masterUserInfo ? this.obj.masterUserInfo.name : ''
    }
  },
  watch: {
    'parentName': function () {
      this.$validator.validate('上级机构', this.parentName)
    },
    'areaName': function () {
      this.$validator.validate('归属区域', this.areaName)
    },
    'userName': function () {
      this.$validator.validate('负责人', this.userName)
    }
  }
}
</script>
