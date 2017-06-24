<template>
  <form class="form-horizontal">
    <div class="row">
      <div class="col-md-6">
        <FormGroupStatic label="姓名">{{ obj.principalUser.name }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="电子邮箱">{{ obj.principalUser.userContactInfo.email }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="身份证号">{{ obj.principalUser.identityNumber }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="性别">
          <template v-if="obj.principalUser.gender == 1">男</template>
          <template v-if="obj.principalUser.gender == 2">女</template>
        </FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="所属部门">{{ obj.principalUser.department }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="职位">{{ obj.principalUser.position }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="手机号码">{{ obj.principalUser.userContactInfo.mobile }}</FormGroupStatic>
      </div>
      <div class="col-md-6">
        <FormGroupStatic label="电话号码">{{ obj.principalUser.userContactInfo.phone }}</FormGroupStatic>
      </div>
    </div>
  </form>
</template>

<script>
  export default {
    props: {
      obj: {
        type: Object,
        required: true
      }
    }
  }
</script>
