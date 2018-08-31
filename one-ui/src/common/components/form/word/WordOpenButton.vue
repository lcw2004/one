<template>
  <span>
    <!-- 需要在隐藏域中设置基础路径供PageOffice调用 -->
    <input type="hidden" id="office_root_path" :value="basePath"/>
    <a @click="openWord()" :class="btnClass">{{ btnText }}</a>
  </span>
</template>

<script>
import { openOfficeParams } from '@utils/word-utils'

export default {
  name: 'WordOpenButton',
  props: {
    businessId: {
      type: String
    },
    businessType: {
      type: String
    },
    btnText: {
      type: String,
      default: '查看'
    },
    params: {
      type: Object
    },
    btnClass: {
      type: String,
      default: ''
    }
  },
  computed: {
    basePath: function () {
      return this.$store.state.system.settings.basePath
    },
    token: function () {
      return this.$store.state.system.token
    },
    wordPagePath: function () {
      // ***********************************
      // 注意！注意！注意！以下配置不要提交
      // ***********************************
      // return 'http://127.0.0.1:8700/word'
      return this.basePath + '/word'
    }
  },
  methods: {
    openWord () {
      let params = {
        businessType: this.businessType,
        businessId: this.businessId,
        authCode: this.token
      }
      openOfficeParams(this.wordPagePath, Object.assign(params, this.params))
    }
  }
}
</script>
