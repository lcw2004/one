<template>
  <div class="box box-primary">
    <div class="box-body">
      <section>
        <h4 class="page-header">基本信息</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="上级机构">{{ obj.parent.name }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="归属区域">{{ obj.area.name }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="机构名称">{{ officeInfo.name }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="机构编码">{{ officeInfo.code }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="机构类型"><DictLabel type="sys_office_type" :value="officeInfo.type"></DictLabel></FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="机构级别"><DictLabel type="sys_office_grade" :value="officeInfo.type"></DictLabel></FormGroupStatic>
            </div>
          </div>
        </form>
        <h4 class="page-header">联系方式</h4>
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="负责人">{{ officeInfo.masterUserInfo.name }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="联系地址">{{ officeInfo.address }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="邮政编码">{{ officeInfo.zipCode }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="电话">{{ officeInfo.phone }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="传真">{{ officeInfo.fax }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="邮箱">{{ officeInfo.email }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="备注">{{ officeInfo.remarks }}</FormGroupStatic>
            </div>
          </div>
        </form>
      </section>
    </div>
  </div>
</template>

<script>

export default {
  data: function () {
    return {
      obj: {}
    }
  },
  computed: {
    officeInfo: function () {
      return this.$store.state.system.userOffice
    }
  },
  created () {
    this.load()
  },
  methods: {
    load () {
      if (this.officeInfo.parentId) {
        this.$api.system.getOffice(this.officeInfo.parentId).then((response) => {
          const result = response.data
          if (result.ok && result.data) {
            this.obj = result.data
          }
        })
      }
    }
  }
}
</script>
