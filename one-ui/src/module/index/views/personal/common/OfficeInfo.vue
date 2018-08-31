<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box box-solid">
          <div class="box-header">
            <h3 class="box-title">机构信息</h3>
          </div>

          <div class="box-body">
            <UserOfficeInfo :obj="officeInfo"/>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import UserOfficeInfo from '../../user/manager/office/UserOfficeInfo'

export default {
  components: {
    UserOfficeInfo
  },
  computed: {
    user: function () {
      return this.$store.state.system.userInfo
    },
    officeInfo: function () {
      return this.$store.state.system.userOffice
    }
  }
}
</script>
