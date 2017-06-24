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

import LogList from '../../views/system/log/LogList'

export default [
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
  {path: '/system/user/add', component: UserForm},
  {path: '/system/user/:id/form', component: UserForm},

  {path: '/system/log', component: LogList}
]
