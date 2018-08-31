<template>
  <OneModal :title="config.title" :show.sync="config.show">
    <form class="form-horizontal">
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
    </form>

    <div class="modal-footer" slot="footer">
      <button type="button" class="btn btn-default " @click="close()">取消</button>
      <button type="button" class="btn btn-primary" @click="ok()">确认</button>
    </div>
  </OneModal>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [ModalMixin],
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
