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
                <input type="text" class="form-control" v-model="supplier.bankAccount" maxlength="20">
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
        </section>

        <section>
          <h4 class="page-header">联系人</h4>

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
        </section>

        <section>
          <h4 class="page-header">资质文件</h4>

          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover">
                <tbody>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>资质类型</th>
                    <th>颁发机构</th>
                    <th>颁发日期</th>
                    <th>有效日期</th>
                    <th>影印件</th>
                    <th>备注</th>
                    <th>操作</th>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </section>

        <section>
          <h4 class="page-header">可参与投标类别</h4>

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
                    <th>审核人</th>
                    <th>审核状态</th>
                    <th>驳回理由</th>
                    <th>操作</th>
                  </tr>
                  <tr v-for="(purchaseType, index) of obj.purchaseTypeList">
                    <td>{{ index + 1}}</td>
                    <td>{{ purchaseType.basePurchaseType.name }}</td>
                    <td>{{ purchaseType.userInfoEO == null ? '' : purchaseType.userInfoEO.name }}</td>
                    <td>
                      <span v-if="purchaseType.status == 1" class="label label-primary">{{ purchaseType.statusCN }}</span>
                      <span v-if="purchaseType.status == 2" class="label label-success">{{ purchaseType.statusCN }}</span>
                      <span v-if="purchaseType.status == 3" class="label label-warning">{{ purchaseType.statusCN }}</span>
                    </td>
                    <td>{{ purchaseType.remark }}</td>
                    <td>
                      <a @click="deletePurchaseType(index)" v-if="purchaseType.status != 2">删除</a>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </section>
      </div>
    </form>
  </div>

  <div class="row">
    <div class="col-md-12">
      <div class="col-md-2 col-sm-0"></div>
      <div class="col-md-2 col-sm-2"><button class="btn btn-block btn-primary" @click="save">提 交</button></div>
    </div>
  </div>

  <SelectPurchaseTypeModal :config="purchaseTypeConfig" v-model="purchaseType" />
</section>
</template>

<script>
import SupplierAddPurchaseTypeMixin from '../../../personal/supplier/SupplierAddPurchaseTypeMixin'

export default {
  mixins: [SupplierAddPurchaseTypeMixin],
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
      actions: {
        getQualificationType: { method: 'get', url: '/api/qualificationType/supplier' },
        save: { method: 'post', url: '/api/user/supplierRegistry/updateUserSupplierEO' }
      },
      qualificationTypeList: []
    }
  },
  mounted: function () {
    this.resource = this.$resource(null, {}, this.actions)
    this.loadQualificationType()
  },
  computed: {
    supplier: function () {
      return this.obj
    }
  },
  methods: {
    loadQualificationType () {
      this.resource.getQualificationType().then(function (response) {
        var result = response.body
        if (result.ok && result.data) {
          this.qualificationTypeList = result.data
        }
      })
    },

    save () {
      this.$validator.validateAll().then(() => {
        this.resource.save(null, JSON.stringify(this.supplier)).then(function (response) {
          var result = response.body
          if (result.ok) {
            this.$notify.success('提交成功，请等待工作人员审核')
            if (this.onSave) {
              this.onSave()
            }
          }
        })
      }).catch(() => {
      })
    }
  }
}
</script>
