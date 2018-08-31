/**
 * 验证的时候只能在本组件内验证，在其他组件内验证的时候会验证不通过
 */
<template>
  <select class="form-control" v-model="dictValue" @change="onChange" @click="onClick()" :disabled="disabled">
    <option value="" v-if="showEmptyValue">全部</option>
    <option v-for="dict of dictList" :value="dict.value" >{{ dict.label }}</option>
  </select>
</template>

<script>
import DictMixin from './DictMixin'

export default {
  mixins: [DictMixin],
  name: 'DictSelect'
}
</script>
