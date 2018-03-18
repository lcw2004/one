import MsgTemplateEOList from '../../views/msg/msgTemplate/MsgTemplateList.vue'
import MsgTemplateEOView from '../../views/msg/msgTemplate/MsgTemplateView.vue'
import MsgTemplateEOEdit from '../../views/msg/msgTemplate/MsgTemplateEdit.vue'

export default [
  {path: '/msg/msgTemplate/list', component: MsgTemplateEOList},
  {path: '/msg/msgTemplate/:id/edit', component: MsgTemplateEOEdit},
  {path: '/msg/msgTemplate/:id/view', component: MsgTemplateEOView},
  {path: '/msg/msgTemplate/add', component: MsgTemplateEOEdit}
]
