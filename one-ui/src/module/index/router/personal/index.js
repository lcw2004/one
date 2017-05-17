import UserInfo from '../../views/personal/common/UserInfo'
import Password from '../../views/personal/common/Password'
import SupplierOfficeInfo from '../../views/personal/supplier/OfficeInfo'
import TaskList from '../../views/personal/task/TaskList'

export default [
  { path: '/task/todo', component: TaskList },
  { path: '/personal/user-info', component: UserInfo },
  { path: '/personal/password', component: Password },
  { path: '/personal/office-info', component: SupplierOfficeInfo }
]
