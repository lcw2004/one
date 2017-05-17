<template>
  <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
      <div class="box box-solid">
        <div class="box-header">
          <div class="text-center registry-title">
            <h3 class="box-title">信息完善</h3>
          </div>
        </div>

        <div class="box-body">
          <div class="row row-margin-bottom">
            <div class="col-md-12">
              <div class="callout callout-warning">
                <h4>提示!</h4>
                <p>请先完善供应商信息，审核通过之后方可参与投标。请尽可能完善您的信息。</p>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <form class="form-horizontal">

                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h3 class="panel-title">公司信息</h3>
                  </div>
                  <div class="panel-body">
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="公司名称">
                          <input type="text" class="form-control" v-model="supplier.name" maxlength="50" disabled>
                        </FormGroup>
                      </div>
                      <div class="col-md-6" v-render.r="'统一社会信用代码'">
                        <FormGroup label="统一社会信用代码">
                          <input type="text" class="form-control" v-model="supplier.officeCode" v-validate="'required'" name="统一社会信用代码" maxlength="20">
                          <p class="help-block"></p>
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
                      <div class="col-md-6" v-render.r="'法人姓名'">
                        <FormGroup label="法人姓名">
                          <input type="text" class="form-control" v-model="supplier.legalPersonName" v-validate="'required'" name="法人姓名" maxlength="50">
                          <p class="help-block"></p>
                        </FormGroup>
                      </div>
                      <div class="col-md-6" v-render.r="'法人身份证'">
                        <FormGroup label="法人身份证">
                          <input type="text" class="form-control" v-model="supplier.legalPersonNumber" v-validate="'required'" name="法人身份证" maxlength="20">
                          <p class="help-block"></p>
                        </FormGroup>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="开户银行">
                          <DictSelect v-model="supplier.bankName" type="supplier_bank"></DictSelect>
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="银行账户">
                          <input type="text" class="form-control" v-model="supplier.bankAccount"  maxlength="20">
                        </FormGroup>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="所属地区">
                          <input type="text" class="form-control">
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
                        <FormGroup label="公司电话">
                          <input type="text" class="form-control" v-model="supplier.telephone" maxlength="12">
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="公司邮箱">
                          <input type="text" class="form-control" v-model="supplier.email" maxlength="50">
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
                        <FormGroup label="注册资金">
                          <div class="input-group">
                            <div class="input-group-addon">$</div>
                            <input type="number" class="form-control" v-model="supplier.registerCapital" maxlength="10">
                            <div class="input-group-addon">万元</div>
                          </div>
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="从业人数">
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

                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h3 class="panel-title">资质文件</h3>
                  </div>
                  <div class="panel-body">
                    <QualificationListEdit :supplier="supplier" :qualificationList="supplier.qualificationList"/>
                  </div>
                </div>

                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h3 class="panel-title">联系人信息</h3>
                  </div>
                  <div class="panel-body">
                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="姓名">
                          <input type="text" class="form-control" v-model="supplier.principalUser.name" disabled>
                        </FormGroup>
                      </div>
                      <div class="col-md-6">
                        <FormGroup label="电子邮箱">
                          <input type="text" class="form-control" v-model="supplier.principalUser.userContactInfo.email" disabled>
                        </FormGroup>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-md-6">
                        <FormGroup label="身份证号">
                          <input type="text" class="form-control" v-model="supplier.principalUser.identityNumber" maxlength="20">
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

                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h3 class="panel-title">投标类别</h3>
                  </div>
                  <div class="panel-body">
                    <div class="row">
                      <div class="col-md-12">
                        <div class="pull-right" style="margin-bottom: 10px;">
                          <button type="button" class="btn btn-primary" @click="purchaseTypeConfig.show = true">
                            添加投标类别
                          </button>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-12">
                        <table class="table table-bordered table-hover">
                          <tbody>
                            <tr>
                              <th style="width: 10px">#</th>
                              <th>投标类别</th>
                              <th>操作</th>
                            </tr>
                            <tr v-for="(purchaseType, index) of supplier.purchaseTypeList">
                              <td>{{ index + 1}}</td>
                              <td>{{ purchaseType.basePurchaseType.name }}</td>
                              <td>
                                <a @click="deletePurchaseType(index)">删除</a>
                              </td>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>

        <div class="box-footer">
          <div class="row">
            <div class="col-md-12">
              <div class="col-md-5"></div>
              <div class="col-md-2">
                <button type="button" class="btn btn-block btn-primary" @click="save">
                  提 交
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <SelectPurchaseTypeModal :config="purchaseTypeConfig" v-model="purchaseType"/>
  </div>
</template>

<script>
  import SupplierAddPurchaseTypeMixin from './SupplierAddPurchaseTypeMixin'
  import SupplierFormMixin from './SupplierFormMixin'
  import QualificationListEdit from './QualificationListEdit'

  export default {
    components: {
      QualificationListEdit
    },
    mixins: [SupplierAddPurchaseTypeMixin, SupplierFormMixin],
    data: function () {
      return {
        actions: {
          get: { method: 'get', url: '/api/supplierInfo' },
          save: { method: 'post', url: '/api/user/supplierRegistry/updateUserSupplierEO' }
        }
      }
    },
    mounted: function () {
      this.resource = this.$resource(null, {}, this.actions)
      this.load()
    }
  }
</script>
