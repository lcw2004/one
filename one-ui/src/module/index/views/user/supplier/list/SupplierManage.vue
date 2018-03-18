<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box box-primary">
          <div class="box-header">
            <SupplierQueryCondition v-model="param"></SupplierQueryCondition>
          </div>

          <div class="box-body">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>供应商名称</th>
                  <th>注册时间</th>
                  <th>联系人</th>
                  <th>手机号码</th>
                  <th>评分</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(obj, index) of page.list">
                  <td>{{ index + 1}}</td>
                  <td>
                    <router-link :to='"/user/supplier/" + obj.supplierId + "/view"'>{{ obj.name }}</router-link>
                  </td>
                  <td>{{ obj.createTime }}</td>
                  <td>
                    <UserInfoSimpleView :user="obj.principalUser"/>
                  </td>
                  <td>{{ obj.principalUser.userContactInfo.mobile }}</td>
                  <td>{{ obj.score == null ? '--' : obj.score }}</td>
                  <td>
                    <SupplierStatusLabel :status="obj.status" :desc="obj.statusCn"/>
                  </td>
                  <td>
                    <router-link :to='"/user/supplier/" + obj.supplierId + "/edit"'>修改</router-link>
                    <a @click="deleteData(obj)">删除</a>
                    <a v-if="obj.status == -2" @click="start(obj)">启用</a>
                    <a v-if="obj.status == 2" @click="stop(obj)">停用</a>
                    <a @click="moveInBlackList(obj)">拉黑</a>
                    <router-link :to='"/user/supplier/appraisal/" + obj.supplierId + "/view"'>评价</router-link>
                  </td>
                </tr>
              </tbody>
            </table>
            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
        </div>
      </div>
    </div>

    <MoveSupplierInBalckListModal v-if="inBlackListconfig.show" :config="inBlackListconfig" :supplier="supplier" @ok="query()"></MoveSupplierInBalckListModal>
  </section>
</template>

<script>
import PageMixin from '@mixins/PageMixin'
import UserInfoSimpleView from '../../common/UserInfoSimpleView'
import SupplierStatusLabel from '../common/SupplierStatusLabel'
import MoveSupplierInBalckListModal from '../modal/MoveSupplierInBalckListModal.vue'

export default {
  mixins: [PageMixin],
  components: {
    UserInfoSimpleView,
    SupplierStatusLabel,
    MoveSupplierInBalckListModal
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/supplier'}
      },
      param: {},
      inBlackListconfig: {
        show: false,
        title: '移入黑名单'
      },
      supplier: {}
    }
  },
  methods: {
    deleteData (obj) {
      this.$confirm('确认删除供应商吗？', () => {
        this.$api.user.deleteSupplier(obj.supplierId).then((response) => {
          if (response.data.ok) {
            this.query()
            this.$notify.success('删除成功！')
          }
        })
      })
    },
    stop (obj) {
      this.$confirm('确认停用供应商吗？', () => {
        this.$api.user.stopSupplier(obj.supplierId).then((response) => {
          if (response.data.ok) {
            this.query()
            this.$notify.success('停用成功！')
          }
        })
      })
    },
    start (obj) {
      this.$confirm('确认启用供应商吗？', () => {
        this.$api.user.startSupplier(obj.supplierId).then((response) => {
          if (response.data.ok) {
            this.query()
            this.$notify.success('启用成功！')
          }
        })
      })
    },
    moveInBlackList (obj) {
      this.supplier = obj
      this.inBlackListconfig.show = true
    }
  }
}
</script>
