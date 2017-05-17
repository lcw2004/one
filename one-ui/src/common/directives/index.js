import Vue from 'vue'
import validReder from './valid-render'
import focus from './focus'
import checkbox from './checkbox'

function initDirectives () {
  Vue.directive('render', validReder)
  Vue.directive('focus', focus)
  Vue.directive('checkbox', checkbox)
}

export default initDirectives
