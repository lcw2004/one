<template>
  <el-select v-model="innerValue" filterable placeholder="请选择" @change="onChange" size="small">
    <el-option label="全部" value="" v-if="isShowEmpty"></el-option>
    <el-option
      v-for="role of roleList"
      :key="role.id"
      :label="role.name"
      :value="role.id">
    </el-option>
  </el-select>
</template>

<script>
export default {
  props: {
    isShowEmpty: {
      type: Boolean,
      default: true
    },
    value: {
      type: String
    }
  },
  computed: {
    innerValue: {
      get: function () {
        return this.value === null || this.value === undefined ? '' : this.value + ''
      },
      set: function (newValue) {
        this.$emit('input', newValue)
      }
    },
    roleList: function () {
      return this.$store.state.system.roleList
    }
  },
  mounted () {
    this.$store.dispatch('initRoleList')
  },
  methods: {
    onChange () {
      this.$emit('change')
    }
  }
}
</script>
