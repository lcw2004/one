<template>
  <OneModal :title="config.title" :show.sync="config.show" size="large">
    <BaseMaterielQueryCondition v-model="param"></BaseMaterielQueryCondition>

    <div class="row">
      <div class="col-md-12">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th style="width: 10px">序号</th>
              <th>编码</th>
              <th>名称</th>
              <th>采购类别</th>
              <th>采购策略</th>
              <th>计量单位</th>
              <th>标杆价格</th>
              <th>规格型号</th>
              <th>采购备注</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(obj, index) of page.list" @click="selectObj = obj">
              <td><input type="radio" :value="obj" v-model="selectObj"></td>
              <td>{{ obj.id }}</td>
              <td>{{ obj.name }}</td>
              <td>{{ obj.purchaseType.name }}</td>
              <td><DictLabel type="base_materiel_type" :value="obj.type"></DictLabel></td>
              <td>{{ obj.unit }}</td>
              <td>{{ obj.price }}</td>
              <td>{{ obj.model }}</td>
              <td>
                <LongText :text="obj.remark"></LongText>
              </td>
            </tr>
          </tbody>
        </table>

        <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
      </div>
    </div>

    <div class="modal-footer" slot="footer">
      <div class="pull-left">
        选中 <span class="label label-info">{{ selectObj.name }}</span>&nbsp;
      </div>
      <button type="button" class="btn btn-default " @click="close()">取消</button>
      <button type="button" class="btn btn-primary" @click="ok()">确认</button>
    </div>
  </OneModal>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin'
import PageMixin from '@mixins/PageMixin'
import BaseMaterielQueryCondition from './BaseMaterielQueryCondition.vue'

export default {
  mixins: [ModalMixin, PageMixin],
  components: {
    BaseMaterielQueryCondition
  },
  props: {
    purchaseTypeId: {
      type: String,
      required: false
    }
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/base/materiel'}
      },
      param: {
        purchaseTypeId: '',
        name: ''
      },
      selectObj: {}
    }
  },
  created () {
    this.param.purchaseTypeId = this.purchaseTypeId
  },
  methods: {
    ok () {
      this.$emit('input', this.selectObj)
      this.config.show = false
    }
  }
}
</script>
