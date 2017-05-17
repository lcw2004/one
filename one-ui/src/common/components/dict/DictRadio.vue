<template>
  <div>
    <label v-for="dict of dictList" class="radio-inline">
      <input type="radio" :value="dict.value" v-model="dictValue"> {{ dict.label }}
    </label>
  </div>
</template>

<script>
import DictMixin from './DictMixin'
export default {
  mixins: [DictMixin],
  name: 'DictRadio'
}
</script>
