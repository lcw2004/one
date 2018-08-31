<template>
  <section class="content">
    <div class="nav-tabs-custom">
      <ul class="nav nav-tabs">
        <li :class="{active : step == 1}" @click="step = 1"><a>审批日志</a></li>
        <li :class="{active : step == 2}" @click="step = 2"><a>审批流程图</a></li>
      </ul>
      <div class="tab-content">
        <div class="tab-pane active">
          <div class="row" v-show="step == 1">
            <div class="col-md-12" v-if="taskInfo.variables && taskInfo.variables.businessId">
              <AuditLog :businessType="businessType" :businessId="taskInfo.variables.businessId" :secondBusinessId="secondBusinessId"></AuditLog>
            </div>
          </div>
          <div class="row" v-show="step == 2">
            <div class="col-md-12">
              <div class="process-img">
                <img v-if="taskInfo.taskId" :src="progressImageUrl" @click="$preview.openImg(progressImageUrl)"></img>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import AuditMixin from './AuditMixin'
import AuditLog from './AuditLog.vue'

export default {
  mixins: [AuditMixin],
  components: {
    AuditLog
  },
  data: function () {
    return {
      step: 1
    }
  }
}
</script>
