import UserList from '../../views/user/manager/UserList.vue'
import UserForm from '../../views/user/manager/UserForm.vue'
import UserAdd from '../../views/user/manager/UserAdd.vue'

export default [
  {path: '/system/user', component: UserList},
  {path: '/system/user/add', component: UserAdd},
  {path: '/system/user/:id/form', component: UserForm}
]
