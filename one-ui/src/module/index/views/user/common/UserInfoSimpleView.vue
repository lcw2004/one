<template>
  <PopoverContainer :title="user.name">
    <span slot="outer">{{ user.name | limitLength }}</span>
    <form class="form-horizontal">
      <div class="row">
        <div class="col-md-6">
          <FormGroupStatic width="5" label="姓名">{{ user.name }}</FormGroupStatic>
        </div>
        <div class="col-md-6">
          <FormGroupStatic width="5" label="登录账号">{{ user.account }}</FormGroupStatic>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <FormGroupStatic width="5" label="手机">{{ user.userContactInfo.mobile }}</FormGroupStatic>
        </div>
        <div class="col-md-6">
          <FormGroupStatic width="5" label="电话">{{ user.userContactInfo.phone }}</FormGroupStatic>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <FormGroupStatic width="5" label="邮箱">{{ user.userContactInfo.email }}</FormGroupStatic>
        </div>
      </div>
    </form>
  </PopoverContainer>
</template>

<script>
export default {
  props: {
    user: {
      type: Object
    }
  },
  data: function () {
    return {}
  },
  methods: {}
}
</script>
