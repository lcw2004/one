import Home from '../views/layout/Home.vue'
import Desktop from '../views/layout/Desktop'
import bidding from './bidding'
import biddingOfSupplier from './bidding-of-supplier'
import biddingOfExpert from './bidding-of-expert'
import biddingOfSupervisor from './bidding-of-supervisor'
import system from './system'
import user from './user'
import personal from './personal'

/*
* 包含菜单结构的页面
*/
export default {
  path: '/home',
  alias: '/',
  component: Home,
  children: [
    { path: '/', component: Desktop },
    ...bidding,
    ...biddingOfSupplier,
    ...biddingOfSupervisor,
    ...biddingOfExpert,
    ...system,
    ...user,
    ...personal
  ],
  beforeEnter: (to, from, next) => {
    console.log('-->Home Router Config')
    console.log(to)
    console.log(from)
    next(vm => {
      // console.log(vm.$store.state.system.userInfo)
    })
  }
}
