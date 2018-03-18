import Vue from 'vue'
import ScrollToErrorMixin from '@mixins/ScrollToErrorMixin'
import SystemTimeMixin from '@mixins/SystemTimeMixin'

export default function () {
  Vue.mixin(ScrollToErrorMixin)
  Vue.mixin(SystemTimeMixin)
}
