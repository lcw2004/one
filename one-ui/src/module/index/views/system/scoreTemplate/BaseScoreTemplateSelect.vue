<template>
  <select class="form-control" v-model="innerValue">
    <option v-for="temp of templateList" :value="temp.templateId">{{ temp.name }}</option>
  </select>
</template>

<script>
export default {
  props: {
    value: {
      type: Number
    },
    type: {}
  },
  data: function () {
    return {
      templateList: []
    }
  },
  computed: {
    innerValue: {
      get: function () {
        return this.value
      },
      set: function (newValue) {
        this.$emit('input', newValue)
      }
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.listBaseScoreTemplate({type: this.type}).then((response) => {
        let result = response.data
        if (result.ok) {
          this.templateList = result.data.list
        }
      })
    }
  }
}
</script>
