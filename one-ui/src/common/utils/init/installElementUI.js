// import ElementUI from 'element-ui'
// Vue.use(ElementUI)

require('element-ui/lib/theme-chalk/index.css')
import Vue from 'vue'
import { DatePicker, TimeSelect, Step, Steps, Switch, Progress, InputNumber, RadioGroup, RadioButton, Cascader, CheckboxGroup, Checkbox, Radio, Select, Option } from 'element-ui'

/**
 * 因为只用到了部分组件，固单独注册
 */
function install () {
  Vue.component(Switch.name, Switch)
  Vue.component(DatePicker.name, DatePicker)
  Vue.component(TimeSelect.name, TimeSelect)
  Vue.component(Steps.name, Steps)
  Vue.component(Step.name, Step)
  Vue.component(Progress.name, Progress)
  Vue.component(InputNumber.name, InputNumber)
  Vue.component(RadioGroup.name, RadioGroup)
  Vue.component(RadioButton.name, RadioButton)
  Vue.component(Cascader.name, Cascader)
  Vue.component(CheckboxGroup.name, CheckboxGroup)
  Vue.component(Checkbox.name, Checkbox)
  Vue.component(Radio.name, Radio)
  Vue.component(Select.name, Select)
  Vue.component(Option.name, Option)
}

export default install
