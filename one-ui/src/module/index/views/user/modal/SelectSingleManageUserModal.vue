<template>
  <OneModal :title="config.title" :show.sync="config.show" size="large">
    <!--Query Start-->
    <form class="form-horizontal">
      <div class="row margin-bottom-10">
        <div class="col-md-4">
          <FormGroup label="查询条件">
            <input class="form-control" type="text" placeholder="登录账号/姓名/电话/手机" v-model.trim="param.userLikeName">
          </FormGroup>
        </div>
      </div>
    </form>
    <!--Query End-->

    <div class="row">
      <div class="col-md-12">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th style="width: 50px">选择</th>
              <th>姓名</th>
              <th>账户</th>
              <th>手机号码</th>
              <th>座机</th>
              <th>邮箱</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="obj of page.list" @click="selectedUser = obj">
              <td><input type="radio" :value="obj" v-model="selectedUser" :key="obj.userId"></td>
              <td>{{ obj.userInfo.name }}</td>
              <td>{{ obj.userInfo.account }}</td>
              <td>{{ obj.userInfo.userContactInfo.mobile }}</td>
              <td>{{ obj.userInfo.userContactInfo.phone }}</td>
              <td>{{ obj.userInfo.userContactInfo.email }}</td>
            </tr>
          </tbody>
        </table>
        <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
      </div>
    </div>

    <div class="modal-footer" slot="footer">
      <div class="row">
        <div class="col-md-10">
          <div class="pull-left modal-selected-data">
            选中
            <template v-if="selectedUser && selectedUser.userInfo"><span class="label label-info">{{ selectedUser.userInfo.name }}</span>
            </template>
          </div>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-default " @click="close()">取消</button>
          <button type="button" class="btn btn-primary" @click="ok()">确认</button>
        </div>
      </div>
    </div>
  </OneModal>
</template>

<script>
import PageMixin from '@mixins/PageMixin'
import ModalMixin from '@mixins/ModalMixin'

/**
 * type：
 *  0 - 直接返回 userInfo 对象
 *  1 - 直接返回 usermanager 对象
 * @type {Array}
 */

export default {
  mixins: [PageMixin, ModalMixin],
  props: {
    value: {},
    type: {
      type: Number,
      default: 0
    },
    officeId: {
      type: String
    },
    roleIdList: {
      type: String
    }
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/manager'}
      },
      param: {
        userLikeName: '',
        officeId: '',
        roleIdList: ''
      },
      selectedUser: {}
    }
  },
  created () {
    this.param.officeId = this.officeId
    this.param.roleIdList = this.roleIdList
  },
  methods: {
    ok () {
      if (this.selectedUser) {
        if (this.type === 0) {
          this.$emit('input', this.selectedUser.userInfo)
          this.$emit('ok', this.selectedUser.userInfo)
        } else {
          this.$emit('input', this.selectedUser)
          this.$emit('ok', this.selectedUser)
        }
        this.close()
      }
    }
  }
}
</script>
