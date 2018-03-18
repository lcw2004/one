<template>
  <OutContainer title="重置密码">
    <div class="row" style="margin-bottom: 20px;margin-top: 20px;">
      <div class="col-md-12">
        <el-steps :active="stepId" simple>
          <el-step title="确认账号" ></el-step>
          <el-step title="安全验证" ></el-step>
          <el-step title="重置密码" ></el-step>
        </el-steps>
      </div>
    </div>

    <div class="row ">
      <div class="col-md-12">
        <ValidAccount v-if="stepId == 1" @next="nextStep" @last="lastStep" @email="email = arguments[0]"/>
        <ValidEmail v-if="stepId == 2" @next="nextStep" @last="lastStep" :email="email"/>
        <RestPassword v-if="stepId == 3" @next="nextStep" @last="lastStep"/>
      </div>
    </div>
  </OutContainer>
</template>

<script>
import OutContainer from '../common/OutContainer.vue'
import ValidAccount from './ValidAccount'
import ValidEmail from './ValidEmail'
import RestPassword from './RestPassword'

export default {
  components: {
    OutContainer,
    ValidAccount,
    ValidEmail,
    RestPassword
  },
  data: function () {
    return {
      stepId: 1,
      maxStep: 3,
      email: ''
    }
  },
  methods: {
    lastStep () {
      if (this.stepId > 1) {
        this.stepId = this.stepId - 1
      }
    },
    nextStep () {
      if (this.stepId < this.maxStep) {
        this.stepId = this.stepId + 1
      }
    }
  }
}
</script>
