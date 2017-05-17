import BiddingClarify from '../../views/bidding/BiddingClarify'
import ClarifyInfo from '../../views/bidding/clarify/ClarifyInfo'
import QuestionReply from '../../views/bidding/clarify/QuestionReply'

export default [
  { path: '/bidding/clarify', component: BiddingClarify },
  { path: '/bidding/clarify/clarify-info', component: ClarifyInfo },
  { path: '/bidding/clarify/question-reply', component: QuestionReply }
]
