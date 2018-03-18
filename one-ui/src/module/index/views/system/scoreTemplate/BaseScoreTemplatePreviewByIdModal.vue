<template>
  <OneModal :title="config.title" :show.sync="config.show" size="large">
    <BaseScoreTemplateView :template="template"></BaseScoreTemplateView>

    <div class="modal-footer" slot="footer">
      <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">关闭</button>
    </div>
  </OneModal>
</template>

<script>
import ModalMixin from '@mixins/ModalSimpleMixin.js'
import BaseScoreTemplateView from './BaseScoreTemplateView.vue'

export default {
  mixins: [ModalMixin],
  components: {
    BaseScoreTemplateView
  },
  props: {
    templateId: {
      type: Number,
      required: true
    }
  },
  data: function () {
    return {
      template: {}
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.getBaseScoreTemplate(this.templateId).then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.template = result.data
          this.config.title = this.template.name
        }
      })
    }
  }
}
</script>
