<!--
投标项目查询条件：
输入：
  如果父组件里面传入了默认查询参数，在组件加载的时候会用父组件传入的默认查询参数覆盖组件的默认值。

输出：
  合并了父组件和查询条件组件的所有参数
 -->

<template>
  <div>
    <form class="form-horizontal query">
      <div class="row">
        <div class="col-md-4">
          <FormGroup label="物料编号">
            <input type="text" class="form-control" placeholder="物料编号" v-model="param.code"/>
          </FormGroup>
        </div>
        <div class="col-md-4">
          <FormGroup label="物料名称">
            <input type="text" class="form-control" placeholder="物料名称" v-model="param.name"/>
          </FormGroup>
        </div>
        <div class="col-md-4">
          <div class="pull-right">
            <a @click="isShow = true" v-if="!isShow">
              <p class="form-control-static">高级查询 <i class="fa fa-fw fa-chevron-down"></i></p>
            </a>
            <a @click="isShow = false" v-if="isShow">
              <p class="form-control-static">基本查询 <i class="fa fa-fw fa-chevron-up"></i></p>
            </a>
          </div>
        </div>
      </div>

      <div class="row" v-if="isShow">
        <div class="col-md-4">
          <FormGroup label="规格型号">
            <input type="text" class="form-control" placeholder="规格型号" v-model="param.model"/>
          </FormGroup>
        </div>
        <div class="col-md-4">
          <FormGroup label="采购策略">
            <DictSelect v-model="param.type" type="base_materiel_type" :showEmptyValue="true"></DictSelect>
          </FormGroup>
        </div>
        <div class="col-md-4">
          <FormGroup label="采购类别">
            <div class="input-group">
              <input type="text" class="form-control" v-model="purchaseTypeName" readonly @click="purchaseTypeConfig.show = true">
              <span class="input-group-btn">
                <button type="button" class="btn btn-primary btn-flat" v-if="!purchaseType.id" @click="purchaseTypeConfig.show = true">选择</button>
                <button type="button" class="btn btn-primary btn-flat" v-if="purchaseType.id" @click="purchaseType = {}">清空</button>
              </span>
            </div>
          </FormGroup>
        </div>

      </div>
    </form>

    <SelectPurchaseTypeModal :config="purchaseTypeConfig" v-model="purchaseType"></SelectPurchaseTypeModal>
  </div>
</template>

<script>
import QueryConditionMixin from '@mixins/QueryConditionMixin'
import SelectPurchaseTypeModal from './SelectPurchaseTypeModal.vue'

export default {
  mixins: [QueryConditionMixin],
  components: {
    SelectPurchaseTypeModal
  },
  data: function () {
    return {
      purchaseTypeConfig: {
        title: '选择投标类别',
        show: false
      },
      param: {
        code: '',
        name: '',
        model: '',
        type: '',
        purchaseTypeId: ''
      },
      purchaseType: {}
    }
  },
  computed: {
    purchaseTypeName: function () {
      return this.purchaseType ? this.purchaseType.name : ''
    }
  },
  watch: {
    'purchaseTypeName': function () {
      this.param.purchaseTypeId = this.purchaseType.id
    }
  }
}
</script>
