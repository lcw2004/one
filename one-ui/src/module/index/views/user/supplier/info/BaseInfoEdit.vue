<!--
描述：修改供应商信息（在管理员修改界面以及供应商自己的修改界面调用）
入参：
  obj:  供应商对象
  onSave: 保存成功之后的回掉
-->
<template>
  <section class="content">
    <div class="row">
      <form class="form-horizontal">
        <div class="col-md-12">
          <section>
            <h4 class="page-header">基本信息</h4>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="公司名称">
                  <input type="text" class="form-control" v-model="supplier.name" maxlength="50" v-validate="'required'" name="公司名称">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="统一社会信用代码" required="true">
                  <input type="text" class="form-control" v-model="supplier.officeCode" v-validate="'required'" name="统一社会信用代码"
                         maxlength="20">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="公司曾用户名称">
                  <input type="text" class="form-control" v-model="supplier.usedName" maxlength="50">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="公司简称">
                  <input type="text" class="form-control" v-model="supplier.shortName" maxlength="50">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="法人姓名" required="true">
                  <input type="text" class="form-control" v-model="supplier.legalPersonName" v-validate="'required'" name="法人姓名"
                         maxlength="50">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="法人身份证号" required="true">
                  <input type="text" class="form-control" v-model="supplier.legalPersonNumber" v-validate="'required|identityCode'" name="法人身份证号"
                         maxlength="20">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="开户银行">
                  <input type="text" class="form-control" v-model="supplier.bankName" maxlength="50">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="银行账户">
                  <input type="text" class="form-control" v-model="supplier.bankAccount" maxlength="20">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="所属地区">
                  <AreaSelect v-model="supplier.areaId"></AreaSelect>
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="供货区域">
                  <input type="text" class="form-control" v-model="supplier.supplyArea">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="通讯地址">
                  <input type="text" class="form-control" v-model="supplier.officeAddress" maxlength="100">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="邮政编码">
                  <input type="text" class="form-control" v-model="supplier.zipCode" maxlength="10">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="公司电话" required="true">
                  <input type="text" class="form-control" v-model="supplier.telephone" v-validate="'required'" name="公司电话" maxlength="12">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="公司邮箱" required="true">
                  <input type="text" class="form-control" v-model="supplier.email" v-validate="'required|email'" name="公司邮箱" maxlength="50">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="单位类型">
                  <input type="text" class="form-control" v-model="supplier.officeType">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="单位性质">
                  <DictSelect v-model="supplier.officeNature" type="supplier_office_nature"></DictSelect>
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="所属行业">
                  <DictSelect v-model="supplier.businessType" type="supplier_business_type"></DictSelect>
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="注册类型">
                  <input type="text" class="form-control" v-model="supplier.registerType">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="注册资金" required="true">
                  <div class="input-group">
                    <div class="input-group-addon">￥</div>
                    <input type="number" class="form-control" v-model="supplier.registerCapital" v-validate="'required'" name="注册资金" maxlength="10">
                    <div class="input-group-addon">万元</div>
                  </div>
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="从业人数" required="true">
                  <DictSelect v-model="supplier.emploeeCount" type="supplier_emploee_count"></DictSelect>
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="企业规模">
                  <input type="text" class="form-control" v-model="supplier.enterpriseScale" maxlength="10">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="主营业务">
                  <input type="text" class="form-control" v-model="supplier.mainBusiness" maxlength="100">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <FormGroup label="业绩简介" width="2">
                  <textarea class="form-control" rows="3" v-model="supplier.businessProfile" maxlength="500"></textarea>
                </FormGroup>
              </div>
            </div>
          </section>

          <section>
            <h4 class="page-header">联系人</h4>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="登录账户">
                  <input type="text" class="form-control" v-model="supplier.principalUser.account" readonly>
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="联系人姓名">
                  <input type="text" class="form-control" v-model="supplier.principalUser.name" v-validate="'required'" name="联系人姓名">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="联系人电子邮箱">
                  <input type="text" class="form-control" v-model="supplier.principalUser.userContactInfo.email" v-validate="'required|email'" name="联系人电子邮箱">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="联系人身份证号">
                  <input type="text" class="form-control" v-model="supplier.principalUser.identityNumber" maxlength="20" v-validate="'identityCode'" name="联系人身份证号">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="联系人性别">
                  <div>
                    <label class="radio-inline"><input type="radio" value="1" v-model="supplier.principalUser.gender"> 男</label>
                    <label class="radio-inline"><input type="radio" value="2" v-model="supplier.principalUser.gender"> 女</label>
                  </div>
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="联系人所属部门">
                  <input type="text" class="form-control" v-model="supplier.principalUserInfo.department" maxlength="50">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="联系人职位">
                  <input type="text" class="form-control" v-model="supplier.principalUserInfo.position" maxlength="50">
                </FormGroup>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6">
                <FormGroup label="联系人手机号码">
                  <input type="text" class="form-control" v-model="supplier.principalUser.userContactInfo.mobile" maxlength="11" v-validate="'mobile'" name="联系人手机号码">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="联系人电话号码">
                  <input type="text" class="form-control" v-model="supplier.principalUser.userContactInfo.phone" maxlength="12">
                </FormGroup>
              </div>
            </div>
          </section>

          <section>
            <h4 class="page-header">资质文件</h4>

            <QualificationListEdit type="3" deletable="false" editable="true"
                                   :supplier="supplier" v-model="supplier.qualificationList"></QualificationListEdit>
          </section>

          <section>
            <h4 class="page-header">可参与投标类别</h4>

            <PurchaseTypeListEdit :purchaseTypeList="supplier.purchaseTypeList"></PurchaseTypeListEdit>
          </section>
        </div>
      </form>
    </div>

    <div class="row">
      <div class="col-md-12">
        <div class="col-md-2 col-sm-0"></div>
        <div class="col-md-2 col-sm-2">
          <button class="btn btn-block btn-primary" @click="save">提 交</button>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import QualificationListEdit from '../../../user/common/QualificationListEdit'
import PurchaseTypeListEdit from '../../../user/supplier/edit/PurchaseTypeListEdit'

export default {
  components: {
    QualificationListEdit,
    PurchaseTypeListEdit
  },
  props: {
    obj: {
      type: Object,
      required: true
    },
    onSave: {
      type: Function,
      required: true
    }
  },
  data: function () {
    return {
      qualificationTypeList: [],
      areaId: '030b2ed66b'
    }
  },
  mounted: function () {
  },
  computed: {
    supplier: function () {
      return this.obj
    }
  },
  methods: {
    save () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.$api.system.updateUserSupplier(this.supplier).then((response) => {
            let result = response.data
            if (result.ok) {
              this.$notify.success('提交成功，请等待工作人员审核')
              if (this.onSave) {
                this.onSave()
              }
            }
          })
        } else {
          this.scrollToError()
        }
      })
    }
  }
}
</script>
