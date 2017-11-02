<template>
  <form class="form-horizontal">
    <div class="row">
      <div class="col-md-6">
        <FormGroupStatic label="公司名称">
          {{ obj.name }}
          <UserStatusLabel :status="obj.status" :desc="obj.statusCn"/>
        </FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="统一社会信用代码">{{ obj.officeCode }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="公司曾用户名称">{{ obj.usedName }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="公司简称">{{ obj.shortName }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="法人姓名">{{ obj.legalPersonName }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="法人身份证">{{ obj.legalPersonNumber }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="开户银行">
          <DictLabel type="obj_bank" :value="obj.legalPersonName"></DictLabel>
        </FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="银行账户">{{ obj.bankAccount }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="所属地区">TODO</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="供货区域">{{ obj.supplyArea }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="通讯地址">{{ obj.officeAddress }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="邮政编码">{{ obj.zipCode }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="公司电话">{{ obj.telephone }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="公司邮箱">{{ obj.email }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="单位类型">{{ obj.officeType }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="单位性质">
          <DictLabel type="obj_office_nature" :value="obj.officeNature"></DictLabel>
        </FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="所属行业">
          <DictLabel type="obj_business_type" :value="obj.businessType"></DictLabel>
        </FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="注册类型">{{ obj.registerType }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="注册资金">{{ obj.registerCapital }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="从业人数">
          <DictLabel type="obj_emploee_count" :value="obj.emploeeCount"></DictLabel>
        </FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="企业规模">{{ obj.enterpriseScale }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="主营业务">{{ obj.mainBusiness }}</FormGroupStatic>
      </div>
      <div class="col-md-12">
        <FormGroupStatic label="业绩简介" width="2">{{ obj.businessProfile }}</FormGroupStatic>
      </div>
    </div>
  </form>
</template>

<script>
  import UserStatusLabel from '../../../common/UserStatusLabel'

  export default {
    components: {
      UserStatusLabel
    },
    props: {
      obj: {
        type: Object,
        required: true
      }
    }
  }
</script>
