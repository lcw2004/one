<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <div class="pull-right">
          <a class="btn btn-primary" @click="toggleAll()"><span v-if="toggleStatus">收缩</span><span v-else>展开</span>全部</a>
          <router-link class="btn btn-primary" to="/system/purchaseType/add">添加</router-link>
        </div>
      </div>
      <div class="box-body">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th>名称</th>
              <th>供应商</th>
              <th>物资</th>
              <th>负责人</th>
              <th>备注</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="obj of dataList" v-show="obj.isShowInTable" :key="obj.id">
              <td>
                <TreeTableColPrefix :obj="obj" @toggle="toggle(obj)"></TreeTableColPrefix>
                <span @click="toggle(obj)">{{ obj.name }}</span>
              </td>
              <td>
                <template v-if="obj.supplierCount > 0">
                  <a @click="supplierList(obj)">{{ obj.supplierCount }}</a>
                </template>
              </td>
              <td>
                <template v-if="obj.materielCount > 0">
                  <router-link :to='"/system/purchaseType/" + obj.id + "/materiel"' >{{ obj.materielCount }}</router-link>
                </template>
                <template v-if="obj.materielCount == 0">
                  <router-link :to='"/system/purchaseType/" + obj.id + "/materiel"' >添加</router-link>
                </template>
              </td>
              <td>
                <template v-if="obj.userNameList && obj.userNameList.length > 0">
                  <router-link :to='"/system/purchaseType/" + obj.id + "/user"'>
                    <template v-for="(name, index) of obj.userNameList">
                      {{ name }}
                      <template v-if="index < obj.userNameList.length - 1">, </template>
                    </template>
                  </router-link>
                </template>
                <template v-else>
                  <span class="label label-warning">未设置</span>
                  <router-link :to='"/system/purchaseType/" + obj.id + "/user"'>
                    点击设置
                  </router-link>
                </template>
              </td>
              <td>{{ obj.remark }}</td>
              <td>
                <router-link :to='"/system/purchaseType/" + obj.id + "/form"'>修改</router-link>
                <a @click="deleteData(obj)">删除</a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <PurchaseTypeSupplierListModal v-if="config.show" :config="config" :purchaseType="purchaseType"/>
  </section>
</template>

<script>
import PurchaseTypeSupplierListModal from './PurchaseTypeSupplierListModal'
import TreeTableMiXin from '@mixins/TreeTableMiXin'

export default {
  mixins: [TreeTableMiXin],
  components: {
    PurchaseTypeSupplierListModal
  },
  data: function () {
    return {
      topElement: {},
      config: {
        title: '供应商',
        show: false
      },
      purchaseType: {}
    }
  },
  mounted () {
    this.loadTreeTable()
  },
  methods: {
    loadTreeTable () {
      this.$api.system.getPurchaseTypeTree().then((response) => {
        let result = response.data
        if (result.ok) {
          this.topElement = result.data
        }
      })
    },
    deleteData (obj) {
      this.$confirm('确认删除[ ' + obj.name + ' ]吗？', () => {
        this.$api.system.deletePurchaseType(obj.id).then((response) => {
          if (response.data.ok) {
            this.$notify.success('删除成功')
            this.removeElement(obj)
          }
        })
      })
    },
    supplierList (obj) {
      this.purchaseType = obj
      this.config.show = true
    }
  }
}
</script>
