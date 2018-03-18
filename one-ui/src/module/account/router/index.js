import VueRouter from 'vue-router'

import AgreementInfo from '../views/agreement/AgreementInfo.vue'
import RegistrySupplier from '../views/registry/RegistrySupplier.vue'
import RegistrySuccess from '../views/registry/RegistrySuccess.vue'
import ForgetAccount from '../views/forget-account/ForgetAccount.vue'
import ForgetPassword from '../views/forget-password/ForgetPassword.vue'
import ResetPasswordSuccess from '../views/forget-password/ResetPasswordSuccess.vue'

const router = {
  routes: [
    {path: '/registry/agreement', component: AgreementInfo},
    {path: '/registry/supplier', component: RegistrySupplier},
    {path: '/registry/success', component: RegistrySuccess},

    {path: '/forget-password', component: ForgetPassword},
    {path: '/forget-password/success', component: ResetPasswordSuccess},

    {path: '/forget-account', component: ForgetAccount}
  ],
  scrollBehavior (to, from, savedPosition) {
    return { x: 0, y: 0 }
  }
}

// 构建VueRouter对象
const vueRouter = new VueRouter(router)
export default vueRouter
