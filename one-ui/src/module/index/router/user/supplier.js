import SupplierManage from '../../views/user/supplier/SupplierManage'
import SupplierView from '../../views/user/supplier/SupplierView'
import SupplierEdit from '../../views/user/supplier/SupplierEdit'
import SupplierPurchaseAuditList from '../../views/user/supplier/SupplierPurchaseAuditList'
import SupplierPurchaseAuditView from '../../views/user/supplier/audit/SupplierPurchaseAuditView'
import SupplierPurchaseDetail from '../../views/user/supplier/audit/SupplierPurchaseDetail'

import SupplierBlackList from '../../views/user/supplier/SupplierBlackList'

import SupplierAudit from '../../views/user/supplier/audit/SupplierAudit'
import SupplierLevel from '../../views/user/supplier/SupplierLevel'
import Questionnaire from '../../views/user/supplier/Questionnaire'

export default [
  // Done
  { path: '/user/supplier/manage', component: SupplierManage },
  { path: '/user/supplier/:id/view', component: SupplierView },
  { path: '/user/supplier/:id/edit', component: SupplierEdit },
  { path: '/user/supplier/purchase-audit', component: SupplierPurchaseAuditList },
  { path: '/user/supplier/purchase-audit/:id', component: SupplierPurchaseAuditView },

  // Not Done
  { path: '/user/supplier/audit', component: SupplierAudit },
  { path: '/user/supplier/purchase-audit/detail', component: SupplierPurchaseDetail },
  { path: '/user/supplier/blacklist', component: SupplierBlackList },

  { path: '/user/supplier/level', component: SupplierLevel },
  { path: '/user/supplier/questionnaire', component: Questionnaire }
]
