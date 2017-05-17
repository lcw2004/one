<template>
  <div class="box">
    <div class="box-header with-border">
      <h3 class="box-title">供应商价格排名</h3>
    </div>
    <div class="box-body">
      <table class="table table-bordered table-hover">
        <tbody>
          <tr>
            <th style="width: 10px">#</th>
            <th>供应商</th>
            <th>价格排名</th>
            <th style="width: 40px">操作</th>
          </tr>

          <tr v-for="supplier of supplierSorted">
            <td><SupplierPriceUnit :value="supplier.sortNum"></SupplierPriceUnit></td>
            <td>{{ supplier.name }}</td>
            <td>
              {{ supplier.total }}
            </td>
            <td>
              <button type="button" class="btn btn-block btn-primary btn-xs" @click="select(supplier)">选择</button>
              <button type="button" class="btn btn-block btn-primary btn-xs" @click="select(supplier)">调整价格</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { sortTotalMoney, sortSupplierPrices, totalMoney } from '../scence/quotes/supplier'
import SupplierPriceUnit from '../scence/quotes/SupplierPriceUnit'

export default {
  components: {
    SupplierPriceUnit
  },
  data: function () {
    return {
    }
  },
  methods: {
    select: function (supplier) {
      this.$emit('selected', supplier)
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
    supplierSorted: function () {
      let suppliers = this.supplierPrices.suppliers
      for (let supplier of suppliers) {
        let total = this.totalMoneySorted.get(supplier.id)
        supplier.sortNum = total.sortNum
        supplier.total = total.total
      }
      suppliers.sort(function (a, b) {
        let aTotal = a.sortNum
        let bTotal = b.sortNum
        if (aTotal < bTotal) return -1
        if (aTotal > bTotal) return 1
        return 0
      })

      return suppliers
    }
  }
}
</script>
