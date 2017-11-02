import UserInfo from '../../views/personal/common/UserInfo.vue'
import Password from '../../views/personal/common/Password.vue'
import TaskList from '../../views/personal/task/TaskList.vue'
import TaskAuditView from '../../views/personal/task/TaskAuditView.vue'
import AuditProgress from '../../views/personal/task/progress/AuditProgress.vue'

export default [
  {path: '/task/todo', component: TaskList},
  {path: '/task/todo/:id', component: TaskAuditView},
  {path: '/task/progress/:businessId/:businessType', component: AuditProgress},
  {path: '/personal/user-info', component: UserInfo},
  {path: '/personal/password', component: Password}
]
