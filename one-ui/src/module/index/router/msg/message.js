import MsgMessageEOList from '../../views/msg/message/MsgMessageList.vue'
import MsgMessageEOView from '../../views/msg/message/MsgMessageView.vue'
import MsgMessageEOEdit from '../../views/msg/message/MsgMessageEdit.vue'

export default [
  {path: '/msg/message/list', component: MsgMessageEOList},
  {path: '/msg/message/:id/edit', component: MsgMessageEOEdit},
  {path: '/msg/message/:id/view', component: MsgMessageEOView},
  {path: '/msg/message/add', component: MsgMessageEOEdit}
]
