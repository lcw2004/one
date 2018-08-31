<template>
  <TreeSelect v-model="value" :topElement="topElement" @change="onChange(arguments[0])" placeholder="请选择采购类别"></TreeSelect>
</template>

<script>
import TreeSelect from '@components/form/tree/TreeSelect'

export default {
  components: {
    TreeSelect
  },
  props: {
    value: {
      type: Object
    }
  },
  mounted () {
    if (!this.topElement.id) {
      this.$store.dispatch('initPurchaseType')
    }
  },
  computed: {
    topElement: function () {
      return this.$store.state.system.topPurchaseType
    }
  },
  methods: {
    onChange (value) {
      this.$emit('input', value)
    }
  }
}
</script>
