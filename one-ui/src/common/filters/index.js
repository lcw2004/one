import Vue from 'vue'
import limitLength from './limit-length'
import fixed from './fixed'

export default function () {
  Vue.filter('limitLength', limitLength)
  Vue.filter('fixed', fixed)
}
