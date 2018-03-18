<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">采购类别信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-2 control-label">上级类别</label>
            <div class="col-sm-4">
              <div class="input-group">
                <input type="text" class="form-control" v-model="obj.parent.name"/>
                <span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="purchaseTreeModalConfig.show = true">选择</button>
								</span>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">类别名称</label>
            <div class="col-sm-4">
              <input type="text" class="form-control" v-model="obj.name"/>
            </div>
          </div>
          <!-- <div class="form-group">
            <label class="col-sm-2 control-label">类别编码</label>
            <div class="col-sm-4">
              <input type="text" class="form-control" v-model="obj.code"/>
            </div>
          </div> -->
          <div class="form-group">
            <label class="col-sm-2 control-label">备注</label>
            <div class="col-sm-4">
              <textarea class="form-control" v-model="obj.remark"></textarea>
            </div>
          </div>
        </form>
      </div>
      <div class="box-footer">
        <div class="row">
          <div class="col-md-2 col-md-offset-2">
            <a class="btn btn-block btn-primary" @click="save()">保存</a>
          </div>
          <div class="col-md-2">
            <a class="btn btn-block btn-default" @click="$router.go(-1)">返回</a>
          </div>
        </div>
      </div>
    </div>

    <SelectPurchaseTypeModal :config="purchaseTreeModalConfig" v-model="obj.parent"/>
  </section>
</template>

<script>
import FormMixin from '@mixins/FormMixin'
import SelectPurchaseTypeModal from '../modal/SelectPurchaseTypeModal'

export default {
  mixins: [FormMixin],
  components: {
    SelectPurchaseTypeModal
  },
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getPurchaseType,
          save: this.$api.system.savePurchaseType,
          update: this.$api.system.updatePurchaseType
        },
        continue: false
      },
      obj: {
        parent: {},
        area: {},
        name: '',
        code: '',
        remark: ''
      },
      purchaseTreeModalConfig: {
        show: false,
        title: '选择上级'
      }
    }
  }
}
</script>
