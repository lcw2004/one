import BiddingOfSupplier from '../../views/bidding-of-supplier/BiddingOfSupplier'
import BiddingCanJoin from '../../views/bidding-of-supplier/BiddingCanJoin'
import BiddingInfo from '../../views/bidding-of-supplier/BiddingInfo'
import Questions from '../../views/bidding-of-supplier/clarify/Questions'
import Clarifies from '../../views/bidding-of-supplier/clarify/Clarifies'
import SubmitBidding from '../../views/bidding-of-supplier/submit/SubmitBidding'
import BiddingScence from '../../views/bidding-of-supplier/scence/BiddingScence'
import TenderInfo from '../../views/bidding-of-supplier/scence/TenderInfo'
import QuestionInfo from '../../views/bidding-of-supplier/scence/QuestionInfo'
import QuestionReply from '../../views/bidding-of-supplier/scence/QuestionReply'
import ClarifyInfo from '../../views/bidding-of-supplier/scence/ClarifyInfo'

export default [
  { path: '/bidding/bidding-of-supplier', component: BiddingOfSupplier },
  { path: '/bidding/bidding-can-join', component: BiddingCanJoin },
  { path: '/bidding/bidding-info', component: BiddingInfo },
  { path: '/bidding/s/questions', component: Questions },
  { path: '/bidding/s/clarifies', component: Clarifies },
  { path: '/bidding/s/submit-bidding', component: SubmitBidding },
  { path: '/bidding/s/scence', component: BiddingScence },
  { path: '/bidding/s/tender-info', component: TenderInfo },
  { path: '/bidding/s/question-info', component: QuestionInfo },
  { path: '/bidding/s/question-reply', component: QuestionReply },
  { path: '/bidding/s/clarify-info', component: ClarifyInfo }
]
