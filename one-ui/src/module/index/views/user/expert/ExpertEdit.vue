<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box box-primary">
          <div class="box-header">
            <h3 class="box-title">专家个人信息</h3>
          </div>
          <div class="box-body">
            <form class="form-horizontal">
              <div class="row">
                <div class="col-md-12">

                  <section class="content">
                    <h4 class="page-header">基本信息</h4>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="姓名">
                          <input type="text" class="form-control" v-model="obj.userInfo.name" v-validate="'required'" name="姓名" maxlength="20">
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="登录账号">
                          <input type="text" class="form-control" v-model="obj.userInfo.account" v-validate="'required'" name="登录账号" maxlength="20">
                        </FormGroup>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="身份证号码">
                          <input type="text" class="form-control" v-model="obj.userInfo.identityNumber" v-validate="'required|identityCode'" name="身份证号码"
                                 maxlength="18">
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="出生年月">
                          <DatePicker v-model="obj.userInfo.birthday" :disabledDate="afterToday" v-validate="'required'"
                                      data-vv-value-path="innerValue" data-vv-name="出生年月"></DatePicker>
                        </FormGroup>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="性别">
                          <DictRadio v-model="obj.userInfo.gender" type="user_gender"></DictRadio>
                        </FormGroup>
                      </div>
                    </div>
                  </section>

                  <section class="content">
                    <h4 class="page-header">联系方式</h4>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="手机号码">
                          <input type="text" class="form-control" v-model="obj.userInfo.userContactInfo.mobile" v-validate="'required|mobile'" name="手机号码" maxlength="11">
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="座机号码">
                          <input type="text" class="form-control" v-model="obj.userInfo.userContactInfo.phone"  maxlength="20">
                        </FormGroup>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="电子邮箱">
                          <input type="text" class="form-control" v-model="obj.userInfo.userContactInfo.email" v-validate="'required|email'" name="电子邮箱" maxlength="20">
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="用户地址">
                          <input type="text" class="form-control" v-model="obj.userInfo.userContactInfo.address" v-validate="'required'" name="用户地址" maxlength="50">
                        </FormGroup>
                      </div>
                    </div>
                  </section>

                  <section class="content">
                    <h4 class="page-header">学历</h4>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="最高学历">
                          <DictSelect v-model="obj.educationInfo.lastEducation" type="user_expert_education"></DictSelect>
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="最后毕业院校">
                          <input type="text" class="form-control" v-model="obj.educationInfo.lastSchool" v-validate="'required'" name="最后毕业院校"
                                 maxlength="20">
                        </FormGroup>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="政治面貌">
                          <DictSelect v-model="obj.educationInfo.politicalStatus" type="user_expert_political_status"></DictSelect>
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="专业">
                          <input type="text" class="form-control" v-model="obj.educationInfo.studyProfession" v-validate="'required'" name="专业"
                                 maxlength="20">
                        </FormGroup>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="毕业时间">
                          <DatePicker :disabledDate="afterToday" v-model="obj.educationInfo.graduationTime" v-validate="'required'"
                                      data-vv-value-path="innerValue" data-vv-name="毕业时间"></DatePicker>
                        </FormGroup>
                      </div>
                    </div>
                  </section>

                  <section class="content">
                    <h4 class="page-header">工作经历</h4>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="是否内部专家">
                          <DictRadio v-model="obj.isOrgUser" type="yes_no"></DictRadio>
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="所在单位名称" v-if="obj.isOrgUser == 0">
                          <input type="text" class="form-control" v-model="obj.orgName" v-validate="'required'" name="所在单位名称" maxlength="20">
                        </FormGroup>
                        <FormGroup label="归属公司" v-if="obj.isOrgUser == 1">
                          <div class="input-group">
                            <input type="text" class="form-control" v-model="officeName" v-validate="'required'" name="归属公司" />
                            <span class="input-group-btn">
                                <button class="btn btn-info" type="button" @click="companyTreeModalConfig.show = true">选择</button>
                              </span>
                          </div>
                          <SelectOfficeModal :config="companyTreeModalConfig" v-model="obj.sysOffice"></SelectOfficeModal>
                        </FormGroup>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="职务">
                          <input type="text" class="form-control" v-model="obj.position" v-validate="'required'" name="职务" maxlength="20">
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="简历">
                          <FileUpload type="doc,docx,pdf" :callback="uploadSuccess" :errorCallback="uploadError"></FileUpload>
                        </FormGroup>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="职业资格等级">
                          <input type="text" class="form-control" v-model="obj.professionLevel" v-validate="'required'" name="职业资格等级" maxlength="20">
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="技术职称">
                          <input type="text" class="form-control" name="技术职称" maxlength="20">
                        </FormGroup>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="评定日期">
                          <DatePicker :disabledDate="afterToday" v-model="obj.professionDate" v-validate="'required'" data-vv-value-path="innerValue"
                                      data-vv-name="评定日期"></DatePicker>
                        </FormGroup>
                      </div>
                    </div>
                  </section>

                  <section>
                    <h4 class="page-header">资质文件</h4>
                    <QualificationListEdit type="2" v-model="obj.qualificationList" deletable="true" editable="true"></QualificationListEdit>
                  </section>

                  <section>
                    <h4 class="page-header">可评标类别</h4>
                    <PurchaseTypeListEdit v-model="obj.purchaseTypeList" :userId="obj.userId" deletable="true" editable="true"></PurchaseTypeListEdit>
                  </section>
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
      </div>
    </div>
  </section>
</template>

<script>
import * as dateUtil from '@utils/date-util'
import { parseBirthday } from '@utils/common'
import FormMixin from '@mixins/FormMixin'
import moment from 'moment'
import BaseInfo from './info/BaseInfo'
import Biddings from './info/Biddings'
import Qualifications from './info/Qualifications'
import QualificationListEdit from '../common/QualificationListEdit'
import PurchaseTypeListEdit from './info/PurchaseTypeListEdit'
import SelectOfficeModal from '../../system/modal/SelectOfficeModal.vue'

export default {
  mixins: [FormMixin],
  components: {
    BaseInfo,
    Biddings,
    Qualifications,
    QualificationListEdit,
    PurchaseTypeListEdit,
    SelectOfficeModal
  },
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.user.getExpert,
          save: this.$api.user.saveExpert,
          update: this.$api.user.updateExpert
        },
        continue: false
      },
      obj: {
        userId: '',
        orgName: '',
        isOrgUser: 1,
        type: 2,
        position: '',
        resumeFileId: '',
        professionLevel: '',
        professionDate: '',
        remark: '',
        officeId: '',
        sysOffice: {},
        userInfo: {
          userId: '',
          account: '',
          name: '',
          gender: 1,
          birthday: '',
          identityNumber: '',
          type: 1,
          status: 1,
          userContactInfo: {
            userId: '',
            areaCode: '',
            mobile: '',
            email: '',
            phone: '',
            wechatId: '',
            address: '',
            zipCode: ''
          }
        },
        educationInfo: {
          userId: '',
          lastEducation: 1,
          lastSchool: '',
          studyProfession: '',
          graduationTime: '',
          workStartTime: '',
          workProfession: '',
          workResume: '',
          graduationCertificate: '',
          politicalStatus: 1
        },
        purchaseTypeList: [],
        qualificationList: []
      },
      companyTreeModalConfig: {
        show: false,
        title: '选择所属机构'
      },
      step: 1
    }
  },
  computed: {
    officeName: function () {
      if (this.obj.sysOffice) {
        return this.obj.sysOffice.name
      } else {
        return ''
      }
    }
  },
  methods: {
    afterToday (time) {
      return dateUtil.isAfter(moment(time), this.systemTime)
    },
    uploadSuccess (fileObj) {
      this.obj.resumeFileId = fileObj.fileId
      this.errors.remove('简历')
      this.$validator.validateAll({'简历': this.resumeFileId})
    },
    uploadError (error) {
      this.errors.remove('简历')
      this.errors.add('简历', error, 'maxFileSize')
    }
  },
  watch: {
    'obj.userInfo.identityNumber': {
      handler: function () {
        if (this.obj.userInfo.identityNumber && (!this.obj.userInfo.birthday || this.obj.userInfo.birthday === '')) {
          this.obj.userInfo.birthday = parseBirthday(this.obj.userInfo.identityNumber)
        }
      },
      deep: true
    }
  }
}
</script>
