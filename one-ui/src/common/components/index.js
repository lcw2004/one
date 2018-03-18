import Vue from 'vue'

// area
import AreaSelect from './form/area/AreaSelect.vue'

// button
import FullButton from './form/button/FullButton.vue'
import OneButton from './form/button/OneButton.vue'
import FullScreenBtn from './form/button/FullScreenBtn.vue'

// calendar
import Calendar from './form/calendar/Calendar.vue'

// dict
import DictSelect from './form/dict/DictSelect.vue'
import DictElSelect from './form/dict/DictElSelect.vue'
import DictRadio from './form/dict/DictRadio.vue'
import DictLabel from './form/dict/DictLabel.vue'
import DictTab from './form/dict/DictTab.vue'

// echart
import ECharts from './form/echart/ECharts.vue'

// editor
import InlineEditor from './form/editor/InlineEditor.vue'
import KindEditor from './form/editor/KindEditor.vue'

// File Upload
import FileUpload from './form/file-upload/FileUpload.vue'
import BatchFileUpload from './form/file-upload/BatchFileUpload.vue'
import PictureCard from './form/file-upload/picture/PictureCard.vue'
import FileDownloader from './form/file-download/FileDownloader.vue'

// Image
import ImageView from './form/img-preview/ImageView.vue'

// Form
import FormGroup from './form/form/FormGroup.vue'
import FormGroupStatic from './form/form/FormGroupStatic.vue'
import FormControllStatic from './form/form/FormControllStatic.vue'

// 分页
import Pagination from './form/pagination/Pagination.vue'

// switch
import OneSwitch from './form/switch/OneSwitch.vue'

// table
import TableNoData from './form/table/TableNoData.vue'
import TreeTableColPrefix from './form/table/TreeTableColPrefix.vue'

// text
import LongText from './form/text/LongText.vue'

// time
import TimePicker from './form/time/TimePicker.vue'
import TimeRangePicker from './form/time/TimeRangePicker.vue'
import DatePicker from './form/time/DatePicker.vue'

// Timeline
import TimeLine from './form/timeline/TimeLine.vue'
import TimeLabel from './form/timeline/TimeLabel.vue'
import TimeItem from './form/timeline/TimeItem.vue'
import TimeItemBody from './form/timeline/TimeItemBody.vue'
import TimeItemFooter from './form/timeline/TimeItemFooter.vue'
import TimeItemHeader from './form/timeline/TimeItemHeader.vue'

// tree
import Tree from './form/tree/Tree.vue'

// verify-code
import VerifyCodeImg from './form/verify-code/VerifyCodeImg.vue'
import VerifyCode from './form/verify-code/VerifyCode.vue'

// container
import FixedContainer from './container/container/FixedContainer.vue'
import PopoverContainer from './container/container/PopoverContainer.vue'
import Popover from './container/container/Popover.vue'
import Tooltip from './container/container/Tooltip.vue'
import RightPanel from './container/container/RightPanel.vue'

// modal
import OneModal from './container/modal/OneModal.vue'

// transition
import OneTransition from './container/transition/OneTransition.vue'

// QuestionTooltip
import QuestionTooltip from './container/questionTooltip/QuestionTooltip.vue'

import notify from './form/notify'
import confirm from './form/confirm'
import progress from './form/progress'
import preview from './form/img-preview'
import overlay from './form/overlay'
import sockjs from './other/sockjs'

function initGlobalComponents () {
  // area
  Vue.component(AreaSelect.name, AreaSelect)

  // button
  Vue.component(FullScreenBtn.name, FullScreenBtn)
  Vue.component(FullButton.name, FullButton)
  Vue.component(OneButton.name, OneButton)

  // calendar
  Vue.component(Calendar.name, Calendar)

  // dict
  Vue.component(DictSelect.name, DictSelect)
  Vue.component(DictElSelect.name, DictElSelect)
  Vue.component(DictRadio.name, DictRadio)
  Vue.component(DictLabel.name, DictLabel)
  Vue.component(DictTab.name, DictTab)

  // echat
  Vue.component(ECharts.name, ECharts)

  // editor
  Vue.component(InlineEditor.name, InlineEditor)
  Vue.component(KindEditor.name, KindEditor)

  // file upload
  Vue.component(FileUpload.name, FileUpload)
  Vue.component(BatchFileUpload.name, BatchFileUpload)
  Vue.component(PictureCard.name, PictureCard)
  Vue.component(FileDownloader.name, FileDownloader)
  Vue.component(ImageView.name, ImageView)

  // form
  Vue.component(FormGroup.name, FormGroup)
  Vue.component(FormGroupStatic.name, FormGroupStatic)
  Vue.component(FormControllStatic.name, FormControllStatic)

  // pagination
  Vue.component(Pagination.name, Pagination)

  // switch
  Vue.component(OneSwitch.name, OneSwitch)

  // table
  Vue.component(TableNoData.name, TableNoData)
  Vue.component(TreeTableColPrefix.name, TreeTableColPrefix)

  // text
  Vue.component(LongText.name, LongText)

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

  // verify-code
  Vue.component(VerifyCodeImg.name, VerifyCodeImg)
  Vue.component(VerifyCode.name, VerifyCode)

  // container ----------------------------------
  // container
  Vue.component(FixedContainer.name, FixedContainer)
  Vue.component(PopoverContainer.name, PopoverContainer)
  Vue.component(Popover.name, Popover)
  Vue.component(Tooltip.name, Tooltip)
  Vue.component(RightPanel.name, RightPanel)

  // modal
  Vue.component(OneModal.name, OneModal)

  // transition
  Vue.component(OneTransition.name, OneTransition)

  // QuestionTooltip
  Vue.component(QuestionTooltip.name, QuestionTooltip)

  // message
  Vue.prototype.$notify = notify
  Vue.prototype.$confirm = confirm
  Vue.prototype.$progress = progress
  Vue.prototype.$preview = preview
  Vue.prototype.$overlay = overlay
  Vue.prototype.$sockjs = sockjs
}

export default initGlobalComponents
