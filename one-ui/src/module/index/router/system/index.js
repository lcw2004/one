import ProfessionType from '../../views/system/profession/ProfessionType'
import NoticeManage from '../../views/system/notice/NoticeManage'
import NoticeTreeForm from '../../views/system/notice/NoticeTreeForm'
import RoomManage from '../../views/system/room/RoomManage'
import SettingManage from '../../views/system/setting/SettingManage'

import DictList from '../../views/system/dict/DictList'
import DictForm from '../../views/system/dict/DictForm'

import AreaList from '../../views/system/area/AreaList'
import AreaForm from '../../views/system/area/AreaForm'

import MenuList from '../../views/system/menu/MenuList'
import MenuForm from '../../views/system/menu/MenuForm'

import RoleList from '../../views/system/role/RoleList'
import RoleForm from '../../views/system/role/RoleForm'

import OfficeList from '../../views/system/office/OfficeList'
import OfficeForm from '../../views/system/office/OfficeForm'

import UserList from '../../views/system/user/UserList'
import UserForm from '../../views/system/user/UserForm'
import UserAdd from '../../views/system/user/UserAdd.vue'

import LogList from '../../views/system/log/LogList'

import ArchiveMenu from '../../views/system/archiveMenu/ArchiveMenu.vue'
import ArchiveMenuForm from '../../views/system/archiveMenu/ArchiveMenuForm.vue'

import PurchaseType from '../../views/system/purchase/PurchaseType'
import PyrchaseTypeForm from '../../views/system/purchase/PyrchaseTypeForm'
import MaterielList from '../../views/system/purchase/materiel/MaterielList.vue'
import PurchaseTypeUserList from '../../views/system/purchase/user/PurchaseTypeUserList.vue'

import MaterielList1 from '../../views/system/materiel/MaterielList.vue'

import WorkFlowList from '../../views/system/workflow/WorkFlowList.vue'
import WorkFLowForm from '../../views/system/workflow/WorkFLowForm.vue'

import CodeSettingList from '../../views/system/code/CodeSettingList.vue'

import TemplateList from '../../views/system/template/TemplateList.vue'

export default [
  {path: '/system/profession-type', component: ProfessionType},

  {path: '/system/notice', component: NoticeManage},
  {path: '/system/notice-tree-form', component: NoticeTreeForm},
  {path: '/system/room', component: RoomManage},
  {path: '/system/setting', component: SettingManage},

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

  {path: '/system/user', component: UserList},
  {path: '/system/user/add', component: UserAdd},
  {path: '/system/user/:id/form', component: UserForm},

  {path: '/system/archiveMenu', component: ArchiveMenu},
  {path: '/system/archiveMenu/add', component: ArchiveMenuForm},
  {path: '/system/archiveMenu/:id/form', component: ArchiveMenuForm},

  {path: '/system/purchaseType', component: PurchaseType},
  {path: '/system/purchaseType/add', component: PyrchaseTypeForm},
  {path: '/system/purchaseType/:id/form', component: PyrchaseTypeForm},
  {path: '/system/purchaseType/:id/materiel', component: MaterielList},
  {path: '/system/purchaseType/:id/user', component: PurchaseTypeUserList},

  {path: '/system/materiel', component: MaterielList1},

  {path: '/system/log', component: LogList},

  {path: '/system/workflow', component: WorkFlowList},
  {path: '/system/workflow/:id/form', component: WorkFLowForm},

  {path: '/system/code', component: CodeSettingList},

  {path: '/system/template', component: TemplateList}
]
