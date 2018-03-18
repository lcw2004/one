<template>
  <div class="password" v-if="password && password.length > 0">
    <ul>
      <li :class="{'danger' : strength == 0}">&nbsp;</li>
      <li :class="{'warning' : strength == 1}">&nbsp;</li>
      <li :class="{'success' : strength == 2}">&nbsp;</li>
    </ul>
    <span :style="strengthLabelStyle">{{ strengthLabel }}</span>
  </div>
</template>

<script>
import { hasNumber, hasCase, hasLowerCase, hasUpperCase, hasSpecilChar } from '@utils/common'

export default {
  props: {
    password: {
      type: String
    }
  },
  data: function () {
    return {
      label: ['弱', '中', '强'],
      labelClass: ['danger', 'warning', 'success']
    }
  },
  computed: {
    strength: function () {
      if (this.password.length < 6) {
        return 0
      }
      if (hasNumber(this.password) && hasLowerCase(this.password) && hasUpperCase(this.password) && hasSpecilChar(this.password) && this.password.length > 10) {
        // 包含大小写、数字、特殊字符且10个字符以上
        return 2
      } else if (hasNumber(this.password) && (hasCase(this.password) || hasSpecilChar(this.password))) {
        // 包含数字且包含任意字符
        return 1
      }
      return 0
    },
    strengthLabel: function () {
      return this.label[this.strength]
    },
    strengthLabelStyle: function () {
      let color = ''
      if (this.strength === 0) {
        color = '#f56954'
      } else if (this.strength === 1) {
        color = '#f39c12'
      } else if (this.strength === 2) {
        color = '#00a65a'
      }
      return {
        color: color
      }
    }
  }
}
</script>

<style scoped lang="less">
  @danger: #f56954;
  @warning: #f39c12;
  @success: #00a65a;

  .password {
    width: 100%;
    margin-top: 10px;

    ul {
      list-style: none;
      display: block;
      float: left;
      height: 20px;
      width: 90%;

      li {
        float: left;
        height: 7px;
        width: 33%;
        position: relative;
        top: 50%;
        transform: translateY(-50%);
        background-color: #d2d6de;
      }

      .danger {
        background-color: @danger;
      }
      .warning {
        background-color: @warning;
      }
      .success {
        background-color: @success;
      }
    }

    span {
      width: 10%;
      margin-left: 10px;
    }
  }
</style>
