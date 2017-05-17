import BiddingExpertExtract from '../../views/bidding/expert/BiddingExpertExtract'
import ExtractExpert from '../../views/bidding/expert/ExtractExpert'
import BiddingExpertApply from '../../views/bidding/expert/BiddingExpertApply'
import ExtractApply from '../../views/bidding/expert/ExtractApply'

export default [
  { path: '/bidding/expert-extract', component: BiddingExpertExtract },
  { path: '/bidding/expert-extract/project', component: ExtractExpert }, // project 以项目ID替换
  { path: '/bidding/expert-apply', component: BiddingExpertApply },
  { path: '/bidding/expert-apply/project', component: ExtractApply } // project 以项目ID替换
]
