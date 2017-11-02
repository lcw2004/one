import Vue from 'vue'
import validReder from './valid-render.js'
import focus from './focus.js'
import checkbox from './checkbox.js'
import numberLimit from './number-limit.js'

function initDirectives () {
  Vue.directive('render', validReder)
  Vue.directive('focus', focus)
  Vue.directive('checkbox', checkbox)
  Vue.directive('num-limit', numberLimit)
}

export default initDirectives
