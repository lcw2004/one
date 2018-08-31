<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title"></h3>
      </div>
      <div class="box-body">
        <div class="row">
          <div class="col-md-12">
            <div class="process-img">
              <img :src="progressImageUrl" @click="$preview.openImg(progressImageUrl)"></img>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <hr class="divider">
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <AuditLog v-if="businessId" :businessType="businessType" :businessId="businessId"></AuditLog>
          </div>
        </div>
      </div>
      <div class="box-footer">
      </div>
    </div>
  </section>
</template>

<script>
import AuditLog from '../view/form/AuditLog.vue'

export default {
  components: {
    AuditLog
  },
  computed: {
    businessId: function () {
      return this.$route.params.businessId
    },
    businessType: function () {
      return this.$route.params.businessType
    },
    progressImageUrl: function () {
      return '/api/flow/progressImage/' + this.businessId + '/' + this.businessType + '?t=' + Math.random()
    }
  }
}
</script>
