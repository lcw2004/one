<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
              <span aria-hidden="true">×</span>
            </button>
            <h4 class="modal-title">{{ config.title }}</h4>
          </div>
          <div class="modal-body">
            <!-- 标题 -->
            <div class="row row-margin-bottom">
              <div class="col-md-12">
                <form class="form-inline">
                </form>
              </div>
            </div>
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
                    <tr v-for="obj of page.list">
                      <td><input type="checkbox" :value="obj.userInfo" v-model="selectedUserList" :key="obj.userId"></td>
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
          </div>
          <div class="modal-footer">
            <div class="row">
              <div class="col-md-10">
                <div class="pull-left modal-selected-data">
                  选中
                  <template v-for="user of selectedUserList">
                    <span class="label label-info">{{ user.name }}</span>&nbsp;
                  </template>
                </div>
              </div>
              <div class="col-md-2">
                <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">取消</button>
                <button type="button" class="btn btn-primary" @click="ok()">确认</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </OneTransition>
</template>

<script>
import PageMixin from '@mixins/PageMixin'
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [PageMixin, ModalMixin],
  components: {
    PageMixin
  },
  props: {
    config: {
      type: Object,
      required: true
    },
    value: {}
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/user/manager'}
      },
      param: {},
      selectedUserList: [],
      isSelectAll: false
    }
  },
  methods: {
    ok () {
      this.close()
      if (this.selectedUserList && this.selectedUserList.length > 0) {
        this.$emit('input', this.selectedUserList)
      }
    }
  },
  watch: {
    'isSelectAll': function () {
      if (this.isSelectAll) {
        this.selectedUserList = this.page.list
      } else {
        this.selectedUserList = []
      }
    },
    /**
     * 刷新数据的时候重置选中的用户
     */
    'page': {
      handler: function () {
        this.selectedUserList = []
      },
      deep: true
    }
  }
}
</script>
