import bidding from './bidding.js'

import DictList from '../../views/system/dict/DictList.vue'
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

import WorkFlowList from '../../views/system/workflow/WorkFlowList.vue'
import WorkFLowForm from '../../views/system/workflow/WorkFLowForm.vue'

import TemplateList from '../../views/system/template/TemplateList.vue'
import CodeGenerate from '../../views/system/codeGen/CodeGenerate.vue'

import MsgTemplateList from '../../views/system/msgTemplate/MsgTemplateList.vue'
import MsgTemplateForm from '../../views/system/msgTemplate/MsgTemplateForm.vue'
import MsgTemplateAdd from '../../views/system/msgTemplate/MsgTemplateAdd.vue'

export default [
  ...bidding,

  {path: '/system/dict', component: DictList},
  {path: '/system/dict/add', component: DictForm},
  {path: '/system/dict/:id/form', component: DictForm},

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

  {path: '/system/workflow', component: WorkFlowList},
  {path: '/system/workflow/:id/form', component: WorkFLowForm},

  {path: '/system/template', component: TemplateList},

  {path: '/system/codeGenerate', component: CodeGenerate},

  {path: '/msg/template', component: MsgTemplateList},
  {path: '/msg/template/add', component: MsgTemplateAdd},
  {path: '/msg/template/:id/form', component: MsgTemplateForm}
]
