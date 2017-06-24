import Vue from 'vue'
import limitLength from './limit-length'

let initFilters = function () {
  Vue.filter('limitLength', limitLength)
}

export default initFilters
