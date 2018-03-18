<template>
  <OutContainer title="供应商注册" :showBack="true">
    <div class="row success-page">
      <div class="col-md-12">
        <div class="success-label">
          <span class="success-label-icon"><img src="../../assets/images/success.png"></span>
          <span class="success-label-title">
            <h4>注册成功</h4>
            <div>
              恭喜您注册成功，请 <a href="login.html">登录系统</a> 继续完善信息，完善信息并审核通过之后即可参与投标。{{ leftTime }}秒后自动跳转。
            </div>
          </span>
        </div>
      </div>

      <div class="col-md-3 col-md-offset-4">
        <a class="btn btn-primary btn-flat btn-block" href="login.html">
          登录并完善信息
        </a>
      </div>
    </div>
  </OutContainer>
</template>

<script>
import OperationSuccessMixn from '../common/OperationSuccessMixn.js'

export default {
  mixins: [OperationSuccessMixn]
}
</script>
