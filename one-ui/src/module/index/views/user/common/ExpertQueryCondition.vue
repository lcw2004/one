<template>
  <div>
    <form class="form-horizontal query">
      <div class="row">
        <div class="col-md-4">
          <label class="control-label col-md-4">专家姓名</label>
          <div class="col-md-8">
            <input type="text" class="form-control" placeholder="专家姓名" v-model="param.userLikeName"/>
          </div>
        </div>
        <div class="col-md-4">
          <label class="control-label col-md-4">采购类别</label>
          <div class="col-md-8">
            <div class="input-group">
              <input type="text" class="form-control" v-model="selectPurchaseType.name" readonly @click="purchaseTypeConfig.show = true">
              <span class="input-group-btn">
                <button type="button" class="btn btn-primary btn-flat" style="height: 30px" v-if="!selectPurchaseType.name"
                        @click="purchaseTypeConfig.show = true">选择</button>
                <button type="button" class="btn btn-primary btn-flat" style="height: 30px" v-if="selectPurchaseType.name" @click="clearPurchaseType">清空</button>
              </span>
            </div>
          </div>
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
          <label class="control-label col-md-4">登录账户</label>
          <div class="col-md-8">
            <input type="text" class="form-control" placeholder="登录账户" v-model="param.userAccount"/>
          </div>
        </div>
        <div class="col-md-4">
          <label class="control-label col-md-4">手机号码</label>
          <div class="col-md-8">
            <input type="text" class="form-control" placeholder="手机号码" v-model="param.userMobile"/>
          </div>
        </div>
        <div class="col-md-4">
          <label class="control-label col-md-4">邮箱</label>
          <div class="col-md-8">
            <input type="text" class="form-control" placeholder="邮箱" v-model="param.userEmail"/>
          </div>
        </div>
      </div>
    </form>

    <SelectPurchaseTypeModal :config="purchaseTypeConfig" v-model="selectPurchaseType"></SelectPurchaseTypeModal>
  </div>
</template>

<script>
import SelectPurchaseTypeModal from '../../system/modal/SelectPurchaseTypeModal'

export default {
  name: 'ExpertQueryCondition',
  components: {
    SelectPurchaseTypeModal
  },
  props: {
    purchaseType: {
      type: Object,
      required: false
    },
    value: {
      type: Object,
      required: true
    }
  },
  created () {
    if (this.purchaseType) {
      this.param.purchaseTypeId = this.purchaseType.id
      this.selectPurchaseType = this.purchaseType
    }
    this.mergeParam()
  },
  data: function () {
    return {
      isShow: false,
      param: {
        userLikeName: '',
        userAccount: '',
        userMobile: '',
        userEmail: '',
        purchaseTypeId: ''
      },
      purchaseTypeConfig: {
        show: false,
        title: '选择投标类别'
      },
      selectPurchaseType: {}
    }
  },
  watch: {
    'param': {
      handler: function () {
        this.$emit('input', this.param)
      },
      deep: true
    },
    'selectPurchaseType': {
      handler: function () {
        this.param.purchaseTypeId = this.selectPurchaseType.id
      },
      deep: true
    }
  },
  methods: {
    clearPurchaseType () {
      this.param.purchaseTypeId = ''
      this.selectPurchaseType = {}
    },
    mergeParam () {
      for (let key in this.value) {
        this.$set(this.param, key, this.value[key])
      }
    }
  }
}
</script>
