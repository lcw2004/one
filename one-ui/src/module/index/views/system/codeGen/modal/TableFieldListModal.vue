<template>
  <OneModal :title="config.title" :show.sync="config.show" size="large">
    <div class="row">
      <div class="col-md-12">
        <div class="callout callout-default">
          <p>
            可以拖动字段对其显示顺序进行排序
          </p>
        </div>
      </div>
    </div>
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th style="width: 10px">#</th>
          <th>字段ID</th>
          <th>字段名称</th>
          <template v-if="showDatail">
            <th>必填</th>
            <th>Java字段名称</th>
            <th>Java类型</th>
          </template>
          <th>必填</th>
          <th>可编辑</th>
          <!-- <th>可添加</th> -->
          <th>在列表中显示</th>
          <th>作为查询条件</th>
          <th>查询类型</th>
          <th>元素类型</th>
          <th>字典类型</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(obj, index) of fieldList" draggable="true"
            @dragenter="allowDrag($event)"
            @dragover="allowDrag($event)"
            @drop="drop($event, index)"
            @dragstart="dragstart($event, index)">
          <td>
            {{ obj.orderIndex }}
          </td>
          <td>{{ obj.fieldId }}
            <template v-if="obj.isPrimaryKey">
              <span class="label label-success">主键</span>
            </template>
          </td>
          <td><input type="text" class="form-control input-sm" style="width: 100px" v-model="obj.fieldName"></td>
          <template v-if="showDatail">
            <td>
              <template v-if="obj.isRequired == 1"><span class="label label-success">是</span></template>
              <template v-if="obj.isRequired == 0">否</template>
            </td>
            <td>{{ obj.javaFieldName }}</td>
            <td>{{ obj.javaType }}</td>
          </template>
          <td><OneSwitch v-model="obj.isRequired" :showText="false" v-if="!obj.isPrimaryKey"></OneSwitch></td>
          <td><OneSwitch v-model="obj.isEdit" :showText="false"></OneSwitch></td>
          <!-- <td><OneSwitch v-model="obj.isInsert" :showText="false"></OneSwitch></td> -->
          <td><OneSwitch v-model="obj.isList" :showText="false"></OneSwitch></td>
          <td><OneSwitch v-model="obj.isQuery" :showText="false"></OneSwitch></td>
          <td><DictSelect v-model="obj.queryType" type="code_gen_query_type" v-if="obj.isQuery == 1"></DictSelect></td>
          <td><DictSelect v-model="obj.showType" type="code_gen_form_type"></DictSelect></td>
          <td>
            <select class="form-control" v-model="obj.dictType" v-if="isDictType(obj.showType)">
              <option v-for="dictType of dictTypeList" :value="dictType">{{ dictType }}</option>
            </select>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="modal-footer" slot="footer">
      <div class="row">
        <div class="col-md-6">
          <div class="pull-left" style="margin-top: 6px;">
            显示字段详情：<OneSwitch v-model="showDatail" activeText="开启" inactiveText="关闭"></OneSwitch>
          </div>
        </div>
        <div class="col-md-6">
          <div class="pull-right">
            <button type="button" class="btn btn-default " @click="close()">取消</button>
            <button type="button" class="btn btn-primary" @click="ok()">确认</button>
          </div>
        </div>
      </div>
    </div>
  </OneModal>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin'
import { isInList } from '@utils/common'

export default {
  mixins: [ModalMixin],
  props: {
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
      showDatail: false,
      fieldList: [],
      dictTypeList: []
    }
  },
  mounted () {
    this.loadData()
    this.loadDictType()
  },
  methods: {
    loadDictType () {
      this.$api.system.listDictType().then((response) => {
        let result = response.data
        if (result.ok) {
          this.dictTypeList = result.data
        }
      })
    },
    loadData () {
      this.$api.system.listTableField(this.table.tableId).then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.fieldList = result.data
        }
      })
    },
    isDictType (dictType) {
      return isInList(parseInt(dictType), [4, 5, 6])
    },
    ok () {
      this.$api.system.updateTableFieldList(this.table.tableId, this.fieldList).then((response) => {
        let result = response.data
        if (result.ok) {
          this.config.show = false
          this.$notify.success('保存成功')
        }
      })
    },

    insertObj (index, targetIndex) {
      let targetObj = this.fieldList.splice(targetIndex, 1)[0]
      this.fieldList.splice(index, 0, targetObj)
      this.reOrder()
    },
    switchObj (index, targetIndex) {
      let thisObj = this.fieldList[index]
      let targetObj = this.fieldList[targetIndex]
      this.fieldList[index] = targetObj
      this.fieldList[targetIndex] = thisObj
      this.reOrder()
    },
    reOrder () {
      if (this.fieldList) {
        let index = 1
        for (let field of this.fieldList) {
          this.$set(field, 'orderIndex', index++)
        }
      }
    },
    allowDrag (e) {
      e.preventDefault()
      return true
    },
    drop (e, objIndex) {
      e.preventDefault()
      let index = e.dataTransfer.getData('index')
      // this.insertObj(index, objIndex)
      this.insertObj(objIndex, index)
    },
    dragstart (e, objIndex) {
      e.dataTransfer.setData('index', objIndex)
    }
  }
}
</script>
