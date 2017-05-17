import AddBidding from '../../views/bidding/AddBidding'
import BiddingAudit from '../../views/bidding/BiddingAudit'
import BiddingManage from '../../views/bidding/BiddingManage'
import BiddingFeeManage from '../../views/bidding/BiddingFeeManage'
import BiddingOfManager from '../../views/bidding/BiddingOfManager'

import BiddingPermissionInfo from '../../views/bidding/BiddingPermissionInfo'
import AuditProgress from '../../views/bidding/AuditProgress'

import BiddingArchives from '../../views/bidding/archives/BiddingArchives'
import BiddingPreAward from '../../views/bidding/award/BiddingPreAward'
import BiddingTimeManage from '../../views/bidding/BiddingTimeManage'
import BiddingFloorPrice from '../../views/bidding/floor-price/BiddingFloorPrice'
import SetFloorPrice from '../../views/bidding/floor-price/SetFloorPrice'
import BiddingAbolish from '../../views/bidding/BiddingAbolish'
import BiddingCalendar from '../../views/bidding/BiddingCalendar'

import scene from './scene'
import expert from './expert'
import clarify from './clarify'
export default [
  { path: '/bidding/create', component: AddBidding },
  { path: '/bidding/audit', component: BiddingAudit },
  { path: '/bidding/manage', component: BiddingManage },
  { path: '/bidding/fee-manage', component: BiddingFeeManage },
  { path: '/bidding/of-manager', component: BiddingOfManager },
  { path: '/bidding/permission-info', component: BiddingPermissionInfo },
  { path: '/bidding/audit-progress', component: AuditProgress },
  { path: '/bidding/archives', component: BiddingArchives },
  { path: '/bidding/pre-award', component: BiddingPreAward },
  { path: '/bidding/time-manage', component: BiddingTimeManage },
  { path: '/bidding/floor-price', component: BiddingFloorPrice },
  { path: '/bidding/set-floor-price', component: SetFloorPrice },
  { path: '/bidding/abolish', component: BiddingAbolish },
  { path: '/bidding/calendar', component: BiddingCalendar },

  ...scene,
  ...expert,
  ...clarify
]
