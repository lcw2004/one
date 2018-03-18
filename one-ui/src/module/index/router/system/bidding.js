import ProfessionType from '../../views/system/profession/ProfessionType.vue'
import NoticeManage from '../../views/system/notice/NoticeManage.vue'
import NoticeTreeForm from '../../views/system/notice/NoticeTreeForm.vue'
import RoomManage from '../../views/system/room/RoomManage.vue'
import SettingManage from '../../views/system/setting/SettingManage.vue'

import ArchiveMenu from '../../views/system/archiveMenu/ArchiveMenu.vue'
import ArchiveMenuForm from '../../views/system/archiveMenu/ArchiveMenuForm.vue'

import PurchaseType from '../../views/system/purchase/PurchaseType.vue'
import PyrchaseTypeForm from '../../views/system/purchase/PyrchaseTypeForm.vue'
import MaterielListOfPurchaseType from '../../views/system/purchase/materiel/MaterielList.vue'
import PurchaseTypeUserList from '../../views/system/purchase/user/PurchaseTypeUserList.vue'

import MaterielList from '../../views/system/materiel/MaterielList.vue'

import CodeSettingList from '../../views/system/code/CodeSettingList.vue'

import BaseScoreTemplateEOList from '../../views/system/scoreTemplate/BaseScoreTemplateList.vue'
import BaseScoreTemplateEOView from '../../views/system/scoreTemplate/BaseScoreTemplateView.vue'
import BaseScoreTemplateEOEdit from '../../views/system/scoreTemplate/BaseScoreTemplateEdit.vue'

export default [
  {path: '/system/profession-type', component: ProfessionType},

  {path: '/system/notice', component: NoticeManage},
  {path: '/system/notice-tree-form', component: NoticeTreeForm},
  {path: '/system/room', component: RoomManage},
  {path: '/system/setting', component: SettingManage},

  {path: '/system/archiveMenu', component: ArchiveMenu},
  {path: '/system/archiveMenu/add', component: ArchiveMenuForm},
  {path: '/system/archiveMenu/:id/form', component: ArchiveMenuForm},

  {path: '/system/purchaseType', component: PurchaseType},
  {path: '/system/purchaseType/add', component: PyrchaseTypeForm},
  {path: '/system/purchaseType/:id/form', component: PyrchaseTypeForm},
  {path: '/system/purchaseType/:id/materiel', component: MaterielListOfPurchaseType},
  {path: '/system/purchaseType/:id/user', component: PurchaseTypeUserList},

  {path: '/system/materiel', component: MaterielList},

  {path: '/system/code', component: CodeSettingList},

  {path: '/system/scoreTemplate', component: BaseScoreTemplateEOList},
  {path: '/system/scoreTemplate/:id/edit', component: BaseScoreTemplateEOEdit},
  {path: '/system/scoreTemplate/:id/view', component: BaseScoreTemplateEOView},
  {path: '/system/scoreTemplate/add', component: BaseScoreTemplateEOEdit}

]
