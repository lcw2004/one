<template>
  <OneModal :title="config.title" :show.sync="config.show" size="large">
    <ImportStep v-if="step == 1" :importUrl="importUrl" :templateUrl="templateUrl" @success="handlerUploadSuccess(arguments[0])"></ImportStep>
    <ImportResult v-if="step == 2" :result="importResult" @last="last"></ImportResult>

    <div class="modal-footer" slot="footer">
      <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">关闭</button>
    </div>
  </OneModal>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin'
import ImportStep from './ImportStep.vue'
import ImportResult from './ImportResult.vue'

export default {
  components: {
    ImportStep,
    ImportResult
  },
  mixins: [ModalMixin],
  data: function () {
    return {
      importUrl: '/api/user/manager/import',
      templateUrl: '/api/user/manager/template',
      importResult: {},
      step: 1
    }
  },
  methods: {
    last () {
      this.step = 1
    },
    next () {
      this.step = 2
    },
    handlerUploadSuccess (result) {
      this.importResult = result
      this.next()
      this.$emit('ok')
    }
  }
}
</script>
