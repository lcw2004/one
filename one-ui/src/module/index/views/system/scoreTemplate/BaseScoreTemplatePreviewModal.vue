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
    template: {
      type: Object,
      required: true
    }
  }
}
</script>
