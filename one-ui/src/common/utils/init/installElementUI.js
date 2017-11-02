// import ElementUI from 'element-ui'
// Vue.use(ElementUI)

import Vue from 'vue'
import { DatePicker, Step, Steps, Switch } from 'element-ui'

/**
 * 因为只用到了部分组件，固单独注册
 */
function install () {
  Vue.component(Switch.name, Switch)
  Vue.component(DatePicker.name, DatePicker)
  Vue.component(Steps.name, Steps)
  Vue.component(Step.name, Step)
}

export default install
