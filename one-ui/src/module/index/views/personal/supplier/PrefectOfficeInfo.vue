<template>
  <OutContainer title="完善信息" :showBack="false">
    <form class="form-horizontal">
      <div class="row row-margin-bottom">
        <div class="col-md-12">
          <div class="callout callout-warning" style="border-radius: 0">
            <h4>提示!</h4>
            <p>请先完善供应商信息，审核通过之后方可参与投标。请尽可能完善您的信息。</p>
          </div>
        </div>
      </div>
  
      <div class="row">
        <div class="col-md-12">
          <h2>公司信息</h2>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="公司名称">
                <input type="text" class="form-control" v-model="supplier.name" maxlength="50" readonly>
              </FormGroup>
            </div>
            <div class="col-md-6">
              <FormGroup label="统一社会信用代码" required="true">
                <input type="text" class="form-control" v-model="supplier.officeCode" v-validate="'required|officeCode'" name="统一社会信用代码" maxlength="20">
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
                <input type="text" class="form-control" v-model="supplier.legalPersonName" v-validate="'required'" name="法人姓名" maxlength="50">
              </FormGroup>
            </div>
            <div class="col-md-6">
              <FormGroup label="法人身份证" required="true">
                <input type="text" class="form-control" v-model="supplier.legalPersonNumber" v-validate="'required|identityCode'" name="法人身份证" maxlength="20">
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
                <AreaSelect></AreaSelect>
              </FormGroup>
            </div>
            <div class="col-md-6">
              <FormGroup label="供货区域">
                <input type="text" class="form-control" v-model="supplier.supplyArea" maxlength="100">
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
                <input type="text" class="form-control" v-model="supplier.telephone" v-validate="'required'" name="公司电话" maxlength="15">
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
        </div>
      </div>
  
      <div class="row">
        <div class="col-md-12">
          <h2>资质文件</h2>
          <QualificationListEdit type="3" :supplier="supplier" v-model="supplier.qualificationList" deletable="true" editable="true"></QualificationListEdit>
        </div>
      </div>
  
      <div class="row">
        <div class="col-md-12">
          <h2>联系人信息</h2>
  
          <div class="panel-body" v-if="supplier.principalUser">
            <div class="row">
              <div class="col-md-6">
                <FormGroup label="姓名">
                  <input type="text" class="form-control" v-model="supplier.principalUser.name" readonly>
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="电子邮箱">
                  <input type="text" class="form-control" v-model="supplier.principalUser.userContactInfo.email" readonly>
                </FormGroup>
              </div>
            </div>
  
            <div class="row">
              <div class="col-md-6">
                <FormGroup label="身份证号" name="联系人身份证号" required="true">
                  <input type="text" class="form-control" v-model="supplier.principalUser.identityNumber" v-validate="'required|identityCode'" name="联系人身份证号" maxlength="20">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="性别">
                  <div>
                    <label class="radio-inline"><input type="radio" value="1" v-model="supplier.principalUser.gender"> 男</label>
                    <label class="radio-inline"><input type="radio" value="2" v-model="supplier.principalUser.gender"> 女</label>
                  </div>
                </FormGroup>
              </div>
            </div>
  
            <div class="row">
              <div class="col-md-6">
                <FormGroup label="所属部门">
                  <input type="text" class="form-control" v-model="supplier.principalUserInfo.department" maxlength="50">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="职位">
                  <input type="text" class="form-control" v-model="supplier.principalUserInfo.position" maxlength="50">
                </FormGroup>
              </div>
            </div>
  
            <div class="row">
              <div class="col-md-6">
                <FormGroup label="手机号码">
                  <input type="text" class="form-control" v-model="supplier.principalUser.userContactInfo.mobile" maxlength="11">
                </FormGroup>
              </div>
              <div class="col-md-6">
                <FormGroup label="电话号码">
                  <input type="text" class="form-control" v-model="supplier.principalUser.userContactInfo.phone" maxlength="12">
                </FormGroup>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <div class="row">
        <div class="col-md-12">
          <h2>采购类别</h2>
          <PurchaseTypeListEdit :purchaseTypeList="supplier.purchaseTypeList"></PurchaseTypeListEdit>
        </div>
      </div>
  
      <div class="row">
        <div class="col-md-12">
          <div class="col-md-2 col-md-offset-4">
            <button type="button" class="btn btn-block btn-primary btn-flat" @click="save()">
              提 交
            </button>
          </div>
          <div class="col-md-2">
            <button type="button" class="btn btn-block btn-default btn-flat" @click="logout()">
              退 出
            </button>
          </div>
        </div>
      </div>
    </form>
  </OutContainer>
</template>

<script>
import OutContainer from '../../../../account/views/common/OutContainer.vue'
import SupplierFormMixin from './SupplierFormMixin'
import QualificationListEdit from '../../user/common/QualificationListEdit'
import PurchaseTypeListEdit from '../../user/supplier/edit/PurchaseTypeListEditSimple'

export default {
  components: {
    OutContainer,
    QualificationListEdit,
    PurchaseTypeListEdit
  },
  mixins: [SupplierFormMixin],
  mounted: function () {
    this.load()
  },
  methods: {
    logout: function () {
      this.$api.system.logout().then((response) => {
        this.$store.dispatch('removeUserInfo')
        window.location.href = 'login.html'
      })
    }
  }
}
</script>
