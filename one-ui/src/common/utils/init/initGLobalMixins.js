import Vue from 'vue'
import ScrollToErrorMixin from 'mixins/ScrollToErrorMixin.js'

export default function initGLobalMixins () {
  Vue.mixin(ScrollToErrorMixin)
}
