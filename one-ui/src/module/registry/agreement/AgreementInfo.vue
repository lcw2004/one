<template>
  <div class="row">
    <div class="col-md-8 col-md-offset-2">
      <div class="row row-margin-bottom">
        <div class="col-md-12">
          <div class="text-center">
            <h3>供应商注册须知</h3>
          </div>
        </div>
      </div>
      <div class="row row-margin-bottom">
        <div class="col-md-12">
          <div class="context">
            <Argeement/>
          </div>
        </div>
      </div>
      <div class="row row-margin-bottom">
        <div class="col-md-12">
          <div class="text-center margin20">
            <input type="checkbox" v-model="isAgree"> 我已阅读，并确认以上信息
          </div>
        </div>
      </div>
      <div class="row row-margin-bottom">
        <div class="col-md-12">
          <div class="col-md-2 col-md-offset-4">
            <button type="button" class="btn btn-block btn-primary" :class="{ disabled: !isAgree }" @click="agree">
              继续注册
            </button>
          </div>
          <div class="col-md-2">
            <a class="btn btn-block btn-default" href="login.html">
              取消注册
            </a>
          </div>
        </div>
      </div>
      <div class="row margin20">
      </div>
    </div>
  </div>
</template>

<script>
import Argeement from './Argeement'

export default {
  components: {
    Argeement
  },
  data: function () {
    return {
      isAgree: false
    }
  },
  methods: {
    agree: function () {
      if (this.isAgree) {
        this.$router.push('/registry')
      }
    }
  }
}
</script>
