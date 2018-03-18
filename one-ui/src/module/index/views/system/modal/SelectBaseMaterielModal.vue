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
              <!--Query-->
              <form class="form-horizontal">
                <div class="row row-margin-bottom">
                  <div class="col-md-4">
                    <FormGroup label="物料名称">
                      <input type="text" class="form-control" placeholder="物料名称" v-model="param.name"/>
                    </FormGroup>
                  </div>
                </div>
              </form>
              <!--Query-->
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover">
                <thead>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>编码</th>
                    <th>名称</th>
                    <th>采购类别</th>
                    <th>采购策略</th>
                    <th>计量单位</th>
                    <th>标杆价格</th>
                    <th>规格型号</th>
                    <th>备注</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(obj, index) of page.list" @click="selectObj = obj">
                    <td><input type="radio" :value="obj" v-model="selectObj"></td>
                    <td>{{ obj.id }}</td>
                    <td>{{ obj.name }}</td>
                    <td>{{ obj.purchaseType.name }}</td>
                    <td>
                      <DictLabel type="base_materiel_type" :value="obj.type"></DictLabel>
                    </td>
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
        </div>
        <div class="modal-footer">
          <div class="pull-left">
            选中 <span class="label label-info">{{ selectObj.name }}</span>&nbsp;
          </div>
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
import PageMixin from '@mixins/PageMixin'

export default {
  mixins: [ModalMixin, PageMixin],
  props: {
    config: {
      type: Object,
      required: true
    },
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
