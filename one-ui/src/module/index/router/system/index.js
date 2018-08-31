import ConfigList from '../../views/system/config/ConfigList.vue'
import ConfigForm from '../../views/system/config/ConfigForm.vue'

import DictList from '../../views/system/dict/DictList.vue'
import DictDataList from '../../views/system/dict/DictDataList.vue'
import DictForm from '../../views/system/dict/DictForm.vue'

import AreaList from '../../views/system/area/AreaList.vue'
import AreaForm from '../../views/system/area/AreaForm.vue'

import MenuList from '../../views/system/menu/MenuList.vue'
import MenuForm from '../../views/system/menu/MenuForm.vue'

import RoleList from '../../views/system/role/RoleList.vue'
import RoleForm from '../../views/system/role/RoleForm.vue'

import OfficeList from '../../views/system/office/OfficeList.vue'
import OfficeForm from '../../views/system/office/OfficeForm.vue'

import LogList from '../../views/system/log/LogList.vue'
import LogView from '../../views/system/log/LogView.vue'

import WorkFlowList from '../../views/system/workflow/WorkFlowList.vue'
import WorkFLowForm from '../../views/system/workflow/WorkFLowForm.vue'

import TemplateList from '../../views/system/htmlTemplate/TemplateList.vue'
import CodeGenerate from '../../views/system/codeGen/CodeGenerate.vue'

import MsgTemplateList from '../../views/system/msgTemplate/MsgTemplateList.vue'
import MsgTemplateForm from '../../views/system/msgTemplate/MsgTemplateForm.vue'
import MsgTemplateAdd from '../../views/system/msgTemplate/MsgTemplateAdd.vue'

import MsgAnnounceList from '../../views/system/announce/MsgAnnounceList.vue'
import MsgAnnounceListRead from '../../views/system/announce/MsgAnnounceListRead.vue'
import MsgAnnounceView from '../../views/system/announce/MsgAnnounceView.vue'
import MsgAnnounceEdit from '../../views/system/announce/MsgAnnounceEdit.vue'

import SysScheduleList from '../../views/system/schedule/SysScheduleList.vue'
import SysScheduleView from '../../views/system/schedule/SysScheduleView.vue'
import SysScheduleEdit from '../../views/system/schedule/SysScheduleEdit.vue'
import SysScheduleLogList from '../../views/system/schedule/SysScheduleLogList.vue'

export default [
  {path: '/system/config', component: ConfigList},
  {path: '/system/config/:id/form', component: ConfigForm},

  {path: '/system/dict', component: DictList},
  {path: '/system/dict/add', component: DictForm},
  {path: '/system/dict/:id/form', component: DictForm},
  {path: '/system/dict/:id/data', component: DictDataList},

  {path: '/system/area', component: AreaList},
  {path: '/system/area/add', component: AreaForm},
  {path: '/system/area/:id/form', component: AreaForm},

  {path: '/system/menu', component: MenuList},
  {path: '/system/menu/add', component: MenuForm},
  {path: '/system/menu/:id/form', component: MenuForm},

  {path: '/system/role', component: RoleList},
  {path: '/system/role/add', component: RoleForm},
  {path: '/system/role/:id/form', component: RoleForm},

  {path: '/system/office', component: OfficeList},
  {path: '/system/office/add', component: OfficeForm},
  {path: '/system/office/:id/form', component: OfficeForm},

  {path: '/system/log', component: LogList},
  {path: '/system/log/:id/view', component: LogView},

  {path: '/system/workflow', component: WorkFlowList},
  {path: '/system/workflow/:id/form', component: WorkFLowForm},

  {path: '/system/template', component: TemplateList},

  {path: '/system/codeGenerate', component: CodeGenerate},

  {path: '/msg/template', component: MsgTemplateList},
  {path: '/msg/template/add', component: MsgTemplateAdd},
  {path: '/msg/template/:id/form', component: MsgTemplateForm},

  {path: '/msg/announce/list', component: MsgAnnounceList},
  {path: '/msg/announce', component: MsgAnnounceListRead},
  {path: '/msg/announce/:id/edit', component: MsgAnnounceEdit},
  {path: '/msg/announce/:id/view', component: MsgAnnounceView},
  {path: '/msg/announce/add', component: MsgAnnounceEdit},

  {path: '/system/schedule', component: SysScheduleList},
  {path: '/system/schedule/:id/edit', component: SysScheduleEdit},
  {path: '/system/schedule/:id/view', component: SysScheduleView},
  {path: '/system/schedule/add', component: SysScheduleEdit},
  {path: '/system/schedule/:id/log', component: SysScheduleLogList}
]
