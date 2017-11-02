<template>
  <select class="form-control" v-model="dictValue">
    <option value="" v-if="showEmptyValue">全部</option>
    <option v-for="dict of dictList" :value="dict.value">{{ dict.label }}</option>
  </select>
</template>

<script>
  import DictMixin from './DictMixin'
  export default {
    mixins: [DictMixin],
    name: 'DictSelect'
  }
</script>
