<template>
  <el-select v-model="innerValue" size="small" filterable>
    <el-option label="全部" value="" v-if="showEmptyValue"></el-option>
    <el-option
      v-for="dictType of dictTypeList"
      :key="dictType"
      :label="dictType"
      :value="dictType">
    </el-option>
  </el-select>
</template>

<script>
export default {
  props: {
    showEmptyValue: {
      type: Boolean,
      default: false
    },
    value: {}
  },
  data: function () {
    return {
      dictTypeList: []
    }
  },
  mounted () {
    this.loadDictType()
  },
  computed: {
    innerValue: {
      get: function () {
        return this.value === null || this.value === undefined ? '' : this.value + ''
      },
      set: function (newValue) {
        this.$emit('input', newValue)
      }
    }
  },
  methods: {
    loadDictType () {
      this.$api.system.listDictType().then((response) => {
        let result = response.data
        if (result.ok) {
          this.dictTypeList = result.data
        }
      })
    }
  }
}
</script>
