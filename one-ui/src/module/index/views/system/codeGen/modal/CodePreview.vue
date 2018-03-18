<template>
  <div class="row">
    <div class="col-md-12" v-for="codeBean of codeBeanList">
      <p class="text-muted">路径：{{ codeBean.savePath }}</p>
      <CodeView :code="codeBean.code"></CodeView>
    </div>
  </div>
</template>

<script>
import CodeView from './CodeView.vue'

export default {
  components: {
    CodeView
  },
  props: {
    tableId: {
      type: String,
      required: true
    },
    codeType: {
      type: String,
      required: true
    },
    strategy: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      codeBeanList: {}
    }
  },
  methods: {
    previewCode () {
      if (this.tableId && this.codeType) {
        this.$api.system.previewCode(this.tableId, this.codeType, this.strategy).then((response) => {
          let result = response.data
          if (result.ok && result.data) {
            this.codeBeanList = result.data
          }
        })
      }
    }
  },
  watch: {
    tableId: {
      handler: function () {
        this.previewCode()
      },
      deep: true,
      immediate: true
    },
    codeType: {
      handler: function () {
        this.previewCode()
      },
      deep: true,
      immediate: true
    }
  }
}
</script>
