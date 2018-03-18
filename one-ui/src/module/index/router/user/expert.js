import ExpertManage from '../../views/user/expert/ExpertManage.vue'
import ExpertView from '../../views/user/expert/ExpertView.vue'
import ExpertEdit from '../../views/user/expert/ExpertEdit.vue'

export default [
  {path: '/user/expert/manage', component: ExpertManage},
  {path: '/user/expert/:id/edit', component: ExpertEdit},
  {path: '/user/expert/:id/view', component: ExpertView},
  {path: '/user/expert/add', component: ExpertEdit}
]
