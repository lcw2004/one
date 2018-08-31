import UserList from '../../views/user/manager/list/UserList.vue'
import UserEdit from '../../views/user/manager/edit/UserEdit.vue'
import UserAdd from '../../views/user/manager/edit/UserAdd.vue'

export default [
  {path: '/system/user', component: UserList},
  {path: '/system/user/add', component: UserAdd},
  {path: '/system/user/:id/form', component: UserEdit}
]
