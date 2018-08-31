import UserInfo from '../../views/personal/user-info/UserInfo.vue'
import Password from '../../views/personal/common/Password.vue'
import OfficeInfo from '../../views/personal/common/OfficeInfo.vue'

import MessageList from '../../views/personal/message/MessageList.vue'

import TaskList from '../../views/personal/task/list/TaskList.vue'
import TaskAuditView from '../../views/personal/task/view/TaskAuditView.vue'
import TaskSimpleView from '../../views/personal/task/view/TaskSimpleView.vue'
import AuditProgress from '../../views/personal/task/progress/AuditProgress.vue'

export default [
  {path: '/task/todo', component: TaskList},
  {path: '/task/todo/:id', component: TaskAuditView},
  {path: '/task/todo/:id/view', component: TaskSimpleView},
  {path: '/task/progress/:businessId/:businessType', component: AuditProgress},
  {path: '/personal/user-info', component: UserInfo},
  {path: '/personal/password', component: Password},
  {path: '/personal/office-info', component: OfficeInfo},
  {path: '/personal/message', component: MessageList}
]
