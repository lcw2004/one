<template>
  <div>
    <div class="box-body">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th style="width: 10px">#</th>
            <th>表名</th>
            <th>表中文名</th>
            <th>Rest简称</th>
            <th>Bean</th>
            <th>Dao</th>
            <th>Service</th>
            <th>Rest</th>
            <th>List页面</th>
            <th>Form页面</th>
            <th>Modal页面</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(table, index) of tableList">
            <td>{{ index + 1}}</td>
            <td>{{ table.tableId }}</td>
            <td><input type="text" class="form-control input-sm" style="width: 100px" v-model.trim="table.tableName" @blur="saveCodeGenTable(table)"></td>
            <td><input type="text" class="form-control input-sm" style="width: 100px" v-model.trim="table.restPath" @blur="saveCodeGenTable(table)"></td>
            <td><OneSwitch v-model="table.isBean" :showText="false" @change="saveCodeGenTable(table)"></OneSwitch></td>
            <td><OneSwitch v-model="table.isDao" :showText="false" @change="saveCodeGenTable(table)"></OneSwitch></td>
            <td><OneSwitch v-model="table.isService" :showText="false" @change="saveCodeGenTable(table)"></OneSwitch></td>
            <td><OneSwitch v-model="table.isRest" :showText="false" @change="saveCodeGenTable(table)"></OneSwitch></td>
            <td><OneSwitch v-model="table.isPageList" :showText="false" @change="saveCodeGenTable(table)"></OneSwitch></td>
            <td><OneSwitch v-model="table.isPageForm" :showText="false" @change="saveCodeGenTable(table)"></OneSwitch></td>
            <td><OneSwitch v-model="table.isPageModal" :showText="false" @change="saveCodeGenTable(table)"></OneSwitch></td>
            <td>
              <a @click="showField(table)">字段</a>
              <a @click="previewCode(table)">代码预览</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  
    <div class="box-footer">
      <div class="col-md-4 col-sm-0"></div>
      <div class="col-md-2 col-sm-2">
        <button type="button" class="btn btn-block btn-default" @click="lastStep()">上一步</button>
      </div>
      <div class="col-md-2 col-sm-2">
        <button type="button" class="btn btn-block btn-primary" @click="generate()">生成代码</button>
      </div>
    </div>
  
    <CodePreviewModal v-if="codePreviewModalconfig.show" :config="codePreviewModalconfig" :table="table" :strategy="strategy"></CodePreviewModal>
    <TableFieldListModal v-if="tableFieldListModalconfig.show" :config="tableFieldListModalconfig" :table="table" :strategy="strategy"></TableFieldListModal>
  </div>
</template>

<script>
import CodePreviewModal from './modal/CodePreviewModal.vue'
import TableFieldListModal from './modal/TableFieldListModal.vue'

export default {
  components: {
    CodePreviewModal,
    TableFieldListModal
  },
  props: {
    strategy: {}
  },
  data: function () {
    return {
      table: {},
      tableList: [],
      codePreviewModalconfig: {
        show: false,
        title: '代码预览'
      },
      tableFieldListModalconfig: {
        show: false,
        title: '字段'
      }
    }
  },
  mounted () {
    this.listCodeGenTable()
  },
  methods: {
    listCodeGenTable () {
      this.$api.system.listCodeGenTable(this.strategy.tableList).then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.tableList = result.data
        }
      })
    },
    saveCodeGenTable (table) {
      this.$api.system.saveCodeGenTable(table).then((response) => {
        let result = response.data
        if (result.ok) {
          table = result.data
        }
      })
    },
    generate () {
      this.$api.system.generate(this.strategy).then((response) => {
        let result = response.data
        if (result.ok) {
          this.$notify.success('生成成功')
        }
      })
    },
    lastStep: function () {
      this.$emit('last')
    },
    previewCode (table) {
      this.table = table
      this.codePreviewModalconfig.show = true
    },
    showField (table) {
      this.table = table
      this.tableFieldListModalconfig.show = true
    }
  }
}
</script>
