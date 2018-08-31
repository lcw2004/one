<template>
  <el-select v-model="dictValue" filterable placeholder="请选择" @change="onChange" size="small">
    <el-option label="全部" value="" v-if="showEmptyValue"></el-option>
    <el-option
      v-for="dict of dictList"
      :key="dict.id"
      :label="dict.label"
      :value="dict.value">
    </el-option>
  </el-select>
</template>

<script>
import DictMixin from './DictMixin'

export default {
  mixins: [DictMixin],
  name: 'DictElSelect'
}
</script>

<style lang="less" type="text/less">
.el-select {
  width: 100%;

  .el-input__inner {
    border-radius: 0;
  }
}
</style>
