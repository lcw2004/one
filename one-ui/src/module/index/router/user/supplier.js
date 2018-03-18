import SupplierManage from '../../views/user/supplier/list/SupplierManage.vue'
import SupplierView from '../../views/user/supplier/SupplierView.vue'
import SupplierEdit from '../../views/user/supplier/SupplierEdit.vue'
import SupplierPurchaseAuditList from '../../views/user/supplier/list/SupplierPurchaseAuditList.vue'
import SupplierPurchaseAuditView from '../../views/user/supplier/audit/SupplierPurchaseAuditView.vue'
import SupplierPurchaseDetail from '../../views/user/supplier/audit/SupplierPurchaseDetail.vue'

import SupplierBlackList from '../../views/user/supplier/list/SupplierBlackList.vue'

import SupplierAudit from '../../views/user/supplier/audit/SupplierAudit.vue'
import SupplierLevel from '../../views/user/supplier/list/SupplierLevel.vue'
import SupplierInquireList from '../../views/user/supplier/list/SupplierInquireList.vue'
import SupplierAppraisalList from '../../views/user/supplier/list/SupplierAppraisalList.vue'
import SupplierAppraisalView from '../../views/user/supplier/appraisal/SupplierAppraisalView.vue'
import SupplierAppraisalProjectList from '../../views/user/supplier/appraisal/SupplierAppraisalProjectList.vue'

export default [
  // Done
  {path: '/user/supplier/manage', component: SupplierManage},
  {path: '/user/supplier/:id/view', component: SupplierView},
  {path: '/user/supplier/:id/edit', component: SupplierEdit},
  {path: '/user/supplier/purchase-audit', component: SupplierPurchaseAuditList},
  {path: '/user/supplier/purchase-audit/:id', component: SupplierPurchaseAuditView},

  // Not Done
  {path: '/user/supplier/audit', component: SupplierAudit},
  {path: '/user/supplier/purchase-audit/detail', component: SupplierPurchaseDetail},
  {path: '/user/supplier/blacklist', component: SupplierBlackList},

  {path: '/user/supplier/level', component: SupplierLevel},
  {path: '/user/supplier/inquire', component: SupplierInquireList},
  {path: '/user/supplier/appraisal', component: SupplierAppraisalList},
  {path: '/user/supplier/appraisal/:id/view', component: SupplierAppraisalView},
  {path: '/user/supplier/appraisal/:id/appraisal', component: SupplierAppraisalProjectList}
]
