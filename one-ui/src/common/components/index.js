import Vue from 'vue'
// base
import FullScreenBtn from './base/FullScreenBtn'
import RightPanel from './base/RightPanel'
import VerifyCodeImg from './base/VerifyCodeImg'
import Checkbox from './base/Checkbox'
import FixedContainer from './container/FixedContainer'
import PopoverContainer from './container/PopoverContainer'
// dict
import DictSelect from './dict/DictSelect'
import DictRadio from './dict/DictRadio'
import DictLabel from './dict/DictLabel'

import OneTransition from './transition/OneTransition'
// 分页
import Pagination from './pagination/Pagination'
// button
import FullButton from './button/FullButton'
// Calendar
import Calendar from './calendar/Calendar'
// switch
import BootstrapSwitch from './switch/BootstrapSwitch'
// editor
import Editor from './editor/Editor'
import InlineEditor from './editor/InlineEditor'
// time
import TimePicker from './time/TimePicker'
import TimeRangePicker from './time/TimeRangePicker'
import DatePicker from './time/DatePicker'
// Timeline
import TimeLine from './timeline/TimeLine'
import TimeLabel from './timeline/TimeLabel'
import TimeItem from './timeline/TimeItem'
import TimeItemBody from './timeline/TimeItemBody'
import TimeItemFooter from './timeline/TimeItemFooter'
import TimeItemHeader from './timeline/TimeItemHeader'
// tree
import Tree from './tree/Tree'
import TreeTableColPrefix from './tree/TreeTableColPrefix'

import notify from './notify'
import confirm from './confirm'
import progress from './progress'
import preview from './img-preview'
// Form
import FormGroup from './form/FormGroup'
import FormGroupStatic from './form/FormGroupStatic'
import FormControllStatic from './form/FormControllStatic'
// File Upload
import FileUpload from './file-upload/FileUpload'

import FileDownloader from './business/FileDownloader'
import ImageView from './img-preview/ImageView'
import LongText from './business/LongText'

function initGlobalComponents () {
  // base
  Vue.component(FullScreenBtn.name, FullScreenBtn)
  Vue.component(RightPanel.name, RightPanel)
  Vue.component(VerifyCodeImg.name, VerifyCodeImg)
  Vue.component(Checkbox.name, Checkbox)
  Vue.component(FixedContainer.name, FixedContainer)
  Vue.component(PopoverContainer.name, PopoverContainer)

  // dicit
  Vue.component(DictSelect.name, DictSelect)
  Vue.component(DictRadio.name, DictRadio)
  Vue.component(DictLabel.name, DictLabel)

  Vue.component(OneTransition.name, OneTransition)

  // pagination
  Vue.component(Pagination.name, Pagination)

  // button
  Vue.component(FullButton.name, FullButton)

  // Calendar
  Vue.component(Calendar.name, Calendar)

  // switch
  Vue.component(BootstrapSwitch.name, BootstrapSwitch)

  // editor
  Vue.component(Editor.name, Editor)
  Vue.component(InlineEditor.name, InlineEditor)

  // time
  Vue.component(TimePicker.name, TimePicker)
  Vue.component(TimeRangePicker.name, TimeRangePicker)
  Vue.component(DatePicker.name, DatePicker)

  // Timeline
  Vue.component(TimeLine.name, TimeLine)
  Vue.component(TimeLabel.name, TimeLabel)
  Vue.component(TimeItem.name, TimeItem)
  Vue.component(TimeItemBody.name, TimeItemBody)
  Vue.component(TimeItemFooter.name, TimeItemFooter)
  Vue.component(TimeItemHeader.name, TimeItemHeader)

  // tree
  Vue.component(Tree.name, Tree)
  Vue.component(TreeTableColPrefix.name, TreeTableColPrefix)

  // form
  Vue.component(FormGroup.name, FormGroup)
  Vue.component(FormGroupStatic.name, FormGroupStatic)
  Vue.component(FormControllStatic.name, FormControllStatic)
  // file upload
  Vue.component(FileUpload.name, FileUpload)
  Vue.component(FileDownloader.name, FileDownloader)
  Vue.component(LongText.name, LongText)
  Vue.component(ImageView.name, ImageView)

  // message
  Vue.prototype.$notify = notify
  Vue.prototype.$confirm = confirm
  Vue.prototype.$progress = progress
  Vue.prototype.$preview = preview
}

export default initGlobalComponents
