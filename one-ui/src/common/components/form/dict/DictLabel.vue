<template>
  <span>{{ label }}</span>
</template>

<script>
import DictMixin from './DictMixin'

export default {
  mixins: [DictMixin],
  name: 'DictLabel',
  computed: {
    label: function () {
      if (!this.dictList) {
        return '--'
      }
      let label = '--'
      for (let dict of this.dictList) {
        if (dict.value === this.value + '') {
          label = dict.label
          break
        }
      }
      return label
    }
  }
}
</script>
