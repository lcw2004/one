import VueRouter from 'vue-router'

import ForgetAccount from '../views/forget-account/ForgetAccount.vue'
import ForgetPassword from '../views/forget-password/ForgetPassword.vue'

const router = {
  routes: [
    {path: '/forget-account', component: ForgetAccount},
    {path: '/forget-password', component: ForgetPassword}
  ]
}

// 构建VueRouter对象
let vueRouter = new VueRouter(router)
export default vueRouter
