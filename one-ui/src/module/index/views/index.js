import Vue from 'vue'
import SupplierQueryCondition from './user/common/SupplierQueryCondition'
import ExpertQueryCondition from './user/common/ExpertQueryCondition'

function initViewComponents () {
  Vue.component(ExpertQueryCondition.name, ExpertQueryCondition)
  Vue.component(SupplierQueryCondition.name, SupplierQueryCondition)
}

export default initViewComponents
