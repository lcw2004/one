import Vue from 'vue'
import focus from './focus.js'
import number from './number.js'

export default function () {
  Vue.directive('focus', focus)
  Vue.directive('number', number)
}
