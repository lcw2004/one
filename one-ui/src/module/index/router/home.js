import Home from '../views/layout/Home.vue'
import Desktop from '../views/layout/Desktop.vue'
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
    {path: '/', component: Desktop},
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
