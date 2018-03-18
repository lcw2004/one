<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
            <span aria-hidden="true">×</span>
          </button>
          <h4 class="modal-title" v-text="config.title"></h4>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-md-6">
                <input type="text" class="form-control" v-model="tableName" maxlength="100" placeholder="请输入表名">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="checkbox" v-for="table of tableList">
                <label>
                  <input type="checkbox" :value="table" v-model="selectedTableList"> {{ table }}
                </label>
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

export default {
  mixins: [ModalMixin],
  props: {
    config: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      tableName: '',
      tableList: [],
      selectedTableList: []
    }
  },
  mounted () {
    this.loadTables()
  },
  methods: {
    loadTables () {
      this.$api.system.listTable(this.tableName).then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.tableList = result.data
        }
      })
    },
    ok () {
      this.config.show = false
      this.$emit('input', this.selectedTableList)
    }
  },
  watch: {
    'tableName': function () {
      this.loadTables()
    }
  }
}
</script>
