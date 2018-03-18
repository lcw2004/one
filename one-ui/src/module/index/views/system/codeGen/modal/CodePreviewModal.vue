<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
            <span aria-hidden="true">×</span>
          </button>
          <h4 class="modal-title" v-text="config.title"></h4>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-md-12">
              <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                  <li :class="{active : selectedCodeType == tab.codeType}" v-for="tab of tabs" v-if="tab.isShow">
                    <a @click="selectedCodeType = tab.codeType">
                      {{ tab.codeType }}
                    </a>
                  </li>
                </ul>
                <div class="tab-content">
                  <div class="tab-pane active">
                    <CodePreview :tableId="table.tableId" :codeType="selectedCodeType" :strategy="strategy"></CodePreview>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">取消</button>
          <button type="button" class="btn btn-primary" @click="ok()">确认</button>
        </div>
      </div>
    </div>
  </div>
  </OneTransition>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin.js'
import CodePreview from './CodePreview.vue'

export default {
  components: {
    CodePreview
  },
  mixins: [ModalMixin],
  props: {
    config: {
      type: Object,
      required: true
    },
    strategy: {
      type: Object,
      required: true
    },
    table: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      tabs: [
        {codeType: 'Bean', isShow: this.table.isBean === 1},
        {codeType: 'Dao', isShow: this.table.isDao === 1},
        {codeType: 'Service', isShow: this.table.isService === 1},
        {codeType: 'Controller', isShow: this.table.isRest === 1},
        {codeType: 'VueList', isShow: this.table.isPageList === 1},
        {codeType: 'VueView', isShow: this.table.isPageForm === 1},
        {codeType: 'VueEdit', isShow: this.table.isPageForm === 1},
        {codeType: 'VueModal', isShow: this.table.isPageModal === 1}
      ],
      selectedCodeType: 'Bean'
    }
  },
  methods: {
    ok () {
      this.config.show = false
      this.$notify.success('ok')
    }
  }
}
</script>
