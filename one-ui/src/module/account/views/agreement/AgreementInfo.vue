<template>
  <OutContainer title="供应商注册须知">
    <div class="row row-margin-bottom">
      <div class="col-md-12">
        <div class="context">
          <AgreementContent/>
        </div>
      </div>
    </div>
    <div class="row row-margin-bottom">
      <div class="col-md-12">
        <div class="text-center margin-20">
          <input type="checkbox" v-model="isAgree"> 我已阅读，并确认以上信息
        </div>
      </div>
    </div>
    <div class="row row-margin-bottom">
      <div class="col-md-12">
        <div class="col-md-2 col-md-offset-4">
          <button type="button" class="btn btn-block btn-flat btn-primary" :class="{ disabled: !isAgree }" @click="agree">
            继续注册
          </button>
        </div>
        <div class="col-md-2">
          <a class="btn btn-block btn-default btn-flat" href="login.html">
            取消注册
          </a>
        </div>
      </div>
    </div>
  </OutContainer>
</template>

<script>
import OutContainer from '../common/OutContainer.vue'
import AgreementContent from './AgreementContent.vue'

export default {
  components: {
    OutContainer,
    AgreementContent
  },
  data: function () {
    return {
      isAgree: false
    }
  },
  methods: {
    agree: function () {
      if (this.isAgree) {
        this.$router.push('/registry/supplier')
      }
    }
  }
}
</script>
