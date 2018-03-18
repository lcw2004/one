<template>
  <div class="row">
    <div class="col-md-12 row-margin-bottom">
      <div class="pull-right">
        <button type="button" class="btn btn-primary" @click="purchaseTypeConfig.show = true">
          选择采购类别
        </button>
      </div>
    </div>
    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th style="width: 10px">#</th>
            <th>投标类别</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(purchaseType, index) of value">
            <td>{{ index + 1}}</td>
            <td>{{ purchaseType.basePurchaseType.name }}</td>
            <td><a @click="deletePurchaseType(index)">删除</a></td>
          </tr>
        </tbody>
      </table>
    </div>

    <SelectPurchaseTypeModal selectType="checkbox" :config="purchaseTypeConfig" v-model="purchaseTypeList"></SelectPurchaseTypeModal>
  </div>
</template>

<script>
import SelectPurchaseTypeModal from '../../../system/modal/SelectPurchaseTypeModal'

export default {
  components: {
    SelectPurchaseTypeModal
  },
  props: {
    type: {
      type: String
    },
    value: {
      type: Array
    },
    deletable: {
      type: String,
      default: true
    },
    editable: {
      type: String,
      default: true
    }
  },
  data: function () {
    return {
      purchaseTypeConfig: {
        title: '选择投标类别',
        show: false
      }
    }
  },
  computed: {
    purchaseTypeList: {
      get: function () {
        let purchaseTypeList = []
        if (this.value) {
          for (let purchaseTypeRelation of this.value) {
            purchaseTypeList.push(purchaseTypeRelation.basePurchaseType)
          }
        }
        return purchaseTypeList
      },
      set: function (newValue) {
        if (newValue) {
          let purchaseTypeRelationList = this.value
          for (let purchaseType of newValue) {
            let purchaseTypeRelation = {
              purchaseTypeId: purchaseType.id,
              basePurchaseType: purchaseType
            }
            if (this.indexOf(purchaseType.id) < 0) {
              purchaseTypeRelationList.push(purchaseTypeRelation)
            }
          }
          this.$emit('input', purchaseTypeRelationList)
        }
      }
    },
    showDelete: function () {
      return this.deletable || (this.deletable === 'true')
    },
    showEdit: function () {
      return this.editable || (this.editable === 'true')
    }
  },
  methods: {
    indexOf (purchaseTypeId) {
      let index = -1
      for (let i = 0; i < this.value.length; i++) {
        let id = this.value[i].basePurchaseType.id
        if (id === purchaseTypeId) {
          index = i
        }
      }
      return index
    },
    deletePurchaseType (index) {
      this.value.splice(index, 1)
    }
  }
}
</script>
