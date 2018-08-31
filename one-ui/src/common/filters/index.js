import Vue from 'vue'
import limitLength from './limit-length'
import fixed from './fixed'
import money from './money'

export default function () {
  Vue.filter('limitLength', limitLength)
  Vue.filter('fixed', fixed)
  Vue.filter('money', money)
}
