<template>
<div>
  <div class="row">
    <div class="col-md-6">
      <div class="box-title">
      </div>
    </div>
    <div class="col-md-3">
      <div class="control-group pull-right">
        <label class="control-label">报价详情&nbsp;</label>
        <div class="pull-right">
          <BootstrapSwitch size="mini" v-model="isShowSubjectDetail"></BootstrapSwitch>
        </div>
      </div>
    </div>
    <div class="col-md-3">
      <div class="control-group pull-right">
        <label class="control-label">报价</label>
        <div class="checkbox inline">
          <label>
            <input type="checkbox" value="1" v-model="showPrices">
            含税价
          </label>
        </div>
        <div class="checkbox inline">
          <label>
            <input type="checkbox" value="2" v-model="showPrices">
            去税价
          </label>
        </div>
        <div class="checkbox inline">
          <label>
            <input type="checkbox" value="3" v-model="showPrices">
            税率
          </label>
        </div>
      </div>
    </div>
  </div>

  <div class="row" style="margin-right: 0px; margin-left: 0px; ">
    <div class="col-md-12" style="overflow:auto;" id="flagDiv">
      <table id="myTable" class="table table-bordered table-hover">
        <thead>
          <tr>
            <td rowspan="2">序号</td>
            <td rowspan="2">标的名称</td>
            <td rowspan="2">拦标价</td>

            <template v-if="isShowSubjectDetail">
              <td rowspan="2">标的编码</td>
              <td rowspan="2">标的数量</td>
              <td rowspan="2">计量单位</td>
              <td rowspan="2">规格型号</td>
            </template>

            <template v-for="supplier of supplierPricesSorted.suppliers">
              <td :colspan="showPrices.length + 1">{{ supplier.name }}</td>
            </template>
          </tr>

          <tr>
            <template v-for="supplier of supplierPricesSorted.suppliers">
              <td>最终报价</td>
              <td v-if="showPriceType('1')">含税价</td>
              <td v-if="showPriceType('2')">去税价</td>
              <td v-if="showPriceType('3')">税率</td>
            </template>
          </tr>
        </thead>

        <tbody>
          <tr v-for="(supplierPrice, index) of supplierPricesSorted.prices">
            <td>{{ index + 1 }}</td>
            <td>{{ supplierPrice.subjectInfo.name }}</td>
            <td>{{ supplierPrice.subjectInfo.floorPrice }}</td>

            <template v-if="isShowSubjectDetail">
              <td>{{ supplierPrice.subjectInfo.code }}</td>
              <td>{{ supplierPrice.subjectInfo.number }}</td>
              <td>{{ supplierPrice.subjectInfo.unit }}</td>
              <td></td>
            </template>

            <template v-for="(value, key, index) of supplierPrice.supplierPrices">
              <td>{{ value.containTax }}<span class="label label-success" v-if="value.sortNum == 1">低</span></td>
              <td v-if="showPriceType('1')">{{ value.containTax }}</td>
              <td v-if="showPriceType('2')">{{ value.notContainTax }}</td>
              <td v-if="showPriceType('3')">{{ value.tax }}</td>
            </template>
          </tr>

          <!-- 总金额 -->
          <tr>
            <td></td>
            <td>总金额</td>
            <td>{{ totalFloorMoney }}</td>
            <td v-if="isShowSubjectDetail" :colspan="isShowSubjectDetail ? 4 : 0"></td>

            <template v-for="supplier of supplierPricesSorted.suppliers">
              <td :colspan="showPrices.length + 1">
                {{ totalMoneySorted.get(supplier.id).total }}
                <SupplierPriceUnit :value="totalMoneySorted.get(supplier.id).sortNum"></SupplierPriceUnit>
                <span class="label label-danger" v-if="totalMoneySorted.get(supplier.id).total > totalFloorMoney">高于拦标价</span>
              </td>
            </template>
          </tr>

          <tr>
            <td></td>
            <td>是否有技术偏离</td>
            <td :colspan="isShowSubjectDetail ? 5 : 0"></td>

            <template v-for="supplier of supplierPricesSorted.suppliers">
              <td :colspan="showPrices.length + 1">
                <span class="label label-warning">存在偏离</span>
                <router-link to="technical-deviation">查看</router-link>
              </td>
            </template>
          </tr>
          </tbody>
        </table>
      </table>
    </div>
  </div>
</div>
</template>

<script>
let $ = require('jquery')
import fixTable from '../../../../../../common/utils/fixTable'
import SupplierPriceUnit from './SupplierPriceUnit'
import { sortTotalMoney, sortSupplierPrices, totalFloorMoney, totalMoney } from './supplier'

export default {
  components: {
    SupplierPriceUnit
  },
  data: function () {
    return {
      isShowSubjectDetail: false,
      showPrices: []
    }
  },
  mounted: function () {
  },
  methods: {
    initTable: function () {
      // 样式切换的时候错乱，暂时屏蔽
      let width = $('#flagDiv').width()
      let heigth = $('#flagDiv').height() + 20
      fixTable('myTable', 2, width, heigth)
    },
    showPriceType: function (index) {
      return $.inArray(index, this.showPrices) !== -1
    }
  },
  computed: {
    supplierPrices: function () {
      return this.$store.state.data.supplierPrice
    },
    supplierPricesSorted: function () {
      return sortSupplierPrices(this.supplierPrices)
    },
    totalMoney: function () {
      return totalMoney(this.supplierPrices)
    },
    totalMoneySorted: function () {
      return sortTotalMoney(this.totalMoney)
    },
    totalFloorMoney: function () {
      return totalFloorMoney(this.supplierPrices)
    }
  }
}
</script>

<style type="text/css">
td {
  word-break: keep-all;
  white-space: nowrap;
}
</style>
