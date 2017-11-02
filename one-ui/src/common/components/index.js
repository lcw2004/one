import Vue from 'vue'
// base
import FullScreenBtn from 'components/base/FullScreenBtn'
import RightPanel from 'components/base/RightPanel'
import VerifyCodeImg from 'components/base/VerifyCodeImg'
import VerifyCode from 'components/base/VerifyCode'
import Checkbox from 'components/base/Checkbox'
import FixedContainer from 'components/container/FixedContainer'
import PopoverContainer from 'components/container/PopoverContainer'
import Popover from 'components/container/Popover.vue'
import Tooltip from 'components/container/Tooltip.vue'
import TableNoData from 'components/base/TableNoData.vue'

// dict
import DictSelect from 'components/dict/DictSelect'
import DictRadio from 'components/dict/DictRadio'
import DictLabel from 'components/dict/DictLabel'

import OneTransition from 'components/transition/OneTransition'
// 分页
import Pagination from 'components/pagination/Pagination'
// button
import FullButton from 'components/button/FullButton'
import OneButton from 'components/button/OneButton'
// Calendar
import Calendar from 'components/calendar/Calendar'

// editor
import InlineEditor from 'components/editor/InlineEditor'
import KindEditor from 'components/editor/KindEditor'
// time
import TimePicker from 'components/time/TimePicker'
import TimeRangePicker from 'components/time/TimeRangePicker'
import DatePicker from 'components/time/DatePicker'
// Timeline
import TimeLine from 'components/timeline/TimeLine'
import TimeLabel from 'components/timeline/TimeLabel'
import TimeItem from 'components/timeline/TimeItem'
import TimeItemBody from 'components/timeline/TimeItemBody'
import TimeItemFooter from 'components/timeline/TimeItemFooter'
import TimeItemHeader from 'components/timeline/TimeItemHeader'
// tree
import Tree from 'components/tree/Tree'
import TreeTableColPrefix from 'components/tree/TreeTableColPrefix'

// Form
import FormGroup from 'components/form/FormGroup'
import FormGroupStatic from 'components/form/FormGroupStatic'
import FormControllStatic from 'components/form/FormControllStatic'
// File Upload
import FileUpload from 'components/file-upload/FileUpload'

import FileDownloader from 'components/business/FileDownloader'
import ImageView from 'components/img-preview/ImageView'
import LongText from 'components/business/LongText'

// QuestionTooltip
import QuestionTooltip from 'components/questionTooltip/QuestionTooltip.vue'

// echart
import ECharts from 'components/echart/ECharts.vue'

import OneSwitch from 'components/switch/OneSwitch.vue'

import notify from 'components/notify'
import confirm from 'components/confirm'
import progress from 'components/progress'
import preview from 'components/img-preview'
import overlay from 'components/overlay'

function initGlobalComponents () {
  // base
  Vue.component(FullScreenBtn.name, FullScreenBtn)
  Vue.component(RightPanel.name, RightPanel)
  Vue.component(VerifyCodeImg.name, VerifyCodeImg)
  Vue.component(VerifyCode.name, VerifyCode)
  Vue.component(Checkbox.name, Checkbox)
  Vue.component(FixedContainer.name, FixedContainer)
  Vue.component(PopoverContainer.name, PopoverContainer)
  Vue.component(Popover.name, Popover)
  Vue.component(Tooltip.name, Tooltip)
  Vue.component(TableNoData.name, TableNoData)

  // dicit
  Vue.component(DictSelect.name, DictSelect)
  Vue.component(DictRadio.name, DictRadio)
  Vue.component(DictLabel.name, DictLabel)

  Vue.component(OneTransition.name, OneTransition)

  // pagination
  Vue.component(Pagination.name, Pagination)

  // button
  Vue.component(FullButton.name, FullButton)
  Vue.component(OneButton.name, OneButton)

  // Calendar
  Vue.component(Calendar.name, Calendar)

  // editor
  Vue.component(InlineEditor.name, InlineEditor)
  Vue.component(KindEditor.name, KindEditor)

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

  // QuestionTooltip
  Vue.component(QuestionTooltip.name, QuestionTooltip)

  // form
  Vue.component(FormGroup.name, FormGroup)
  Vue.component(FormGroupStatic.name, FormGroupStatic)
  Vue.component(FormControllStatic.name, FormControllStatic)
  // file upload
  Vue.component(FileUpload.name, FileUpload)
  Vue.component(FileDownloader.name, FileDownloader)
  Vue.component(LongText.name, LongText)
  Vue.component(ImageView.name, ImageView)

  Vue.component(ECharts.name, ECharts)

  Vue.component(OneSwitch.name, OneSwitch)

  // message
  Vue.prototype.$notify = notify
  Vue.prototype.$confirm = confirm
  Vue.prototype.$progress = progress
  Vue.prototype.$preview = preview
  Vue.prototype.$overlay = overlay
}

export default initGlobalComponents
