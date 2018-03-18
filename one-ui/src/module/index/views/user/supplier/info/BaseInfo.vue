<!--
这个页面有以下几个地方用了，根据类型来区分开
1.  详情
2.  编辑
3.  审核
 -->

<template>
  <section class="content" v-if="obj.supplierId">
    <div class="row">
      <div class="col-md-12">
        <section>
          <h4 class="page-header">基本信息</h4>
          <SupplierBaseInfo :obj="obj"/>
        </section>

        <section>
          <h4 class="page-header">联系人</h4>
          <PrincipalUserInfo :obj="obj"/>
        </section>

        <section>
          <h4 class="page-header">资质文件</h4>
          <QualificationInfo :obj="obj"/>
        </section>

        <section>
          <h4 class="page-header">可参与投标类别</h4>
          <PurchaseTypeInfo :obj="obj" v-if="type != '3'"/>
          <PurchaseTypeInfoAudit :obj="obj" v-if="type == '3'" @reload="$emit('reload')"/>
        </section>
      </div>
    </div>
  </section>
</template>

<script>
import SupplierBaseInfo from './baseInfo/SupplierBaseInfo'
import PrincipalUserInfo from './baseInfo/PrincipalUserInfo'
import PurchaseTypeInfo from './baseInfo/PurchaseTypeInfo'
import PurchaseTypeInfoAudit from './baseInfo/PurchaseTypeInfoAudit'
import QualificationInfo from './baseInfo/QualificationInfo'

export default {
  components: {
    SupplierBaseInfo,
    PrincipalUserInfo,
    PurchaseTypeInfo,
    PurchaseTypeInfoAudit,
    QualificationInfo
  },
  props: {
    obj: {
      type: Object,
      required: true
    },
    type: {
      type: String,
      required: true
    }
  },
  data: function () {
    return {}
  }
}
</script>
