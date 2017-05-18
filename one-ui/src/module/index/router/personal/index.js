import UserInfo from '../../views/personal/common/UserInfo'
import Password from '../../views/personal/common/Password'
import TaskList from '../../views/personal/task/TaskList'

export default [
  { path: '/task/todo', component: TaskList },
  { path: '/personal/user-info', component: UserInfo },
  { path: '/personal/password', component: Password }
]
