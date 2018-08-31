<!--
投标项目查询条件：
输入：
  如果父组件里面传入了默认查询参数，在组件加载的时候会用父组件传入的默认查询参数覆盖组件的默认值。

输出：
  合并了父组件和查询条件组件的所有参数
 -->

<template>
  <div>
    <form class="form-horizontal query">
      <div class="row">
        <div class="col-md-4">
          <FormGroup label="URI地址">
            <input type="text" class="form-control" placeholder="URI地址" v-model="param.httpUri"/>
          </FormGroup>
        </div>
        <div class="col-md-4">
          <FormGroup label="执行类名">
            <input type="text" class="form-control" placeholder="执行类名" v-model="param.className"/>
          </FormGroup>
        </div>
        <div class="col-md-4">
          <div class="pull-right">
            <a @click="isShow = true" v-if="!isShow">
              <p class="form-control-static">高级查询 <i class="fa fa-fw fa-chevron-down"></i></p>
            </a>
            <a @click="isShow = false" v-if="isShow">
              <p class="form-control-static">基本查询 <i class="fa fa-fw fa-chevron-up"></i></p>
            </a>
          </div>
        </div>
      </div>

      <template v-if="isShow">
        <div class="row">
          <div class="col-md-4">
            <FormGroup label="HTTP方法">
              <select class="form-control" v-model="param.httpMethod">
                <option value="">全部</option>
                <option value="GET">GET</option>
                <option value="POST">POST</option>
                <option value="PUT">PUT</option>
                <option value="DELETE">DELETE</option>
              </select>
            </FormGroup>
          </div>
          <div class="col-md-4">
            <FormGroup label="是否异常">
              <select class="form-control" v-model="param.isFail">
                <option value="">全部</option>
                <option value="1">接口异常</option>
                <option value="0">接口正常</option>
              </select>
            </FormGroup>
          </div>
          <div class="col-md-4">
            <FormGroup label="执行时间">
              <div class="input-group">
                <span class="input-group-addon">超过</span>
                <input type="tel" class="form-control" v-model.number="param.executeTime" maxlength="5">
                <span class="input-group-addon">毫秒</span>
              </div>
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4">
            <FormGroup label="操作用户">
              <div class="input-group">
                <input type="text" class="form-control" v-model="user.name" readonly @click="selectUserConfig.show = true">
                <span class="input-group-btn">
                  <button type="button" class="btn btn-primary btn-flat btn-query" style="height: 30px" v-if="!user.name" @click="selectUserConfig.show = true">选择</button>
                  <button type="button" class="btn btn-primary btn-flat btn-query" style="height: 30px" v-if="user.name" @click="manager = {}">清空</button>
                </span>
              </div>
            </FormGroup>
          </div>
          <div class="col-md-4">
            <FormGroup label="请求时间">
              <div style="width: 100%">
                <TimeRangePicker :from="param.createTimeFrom" :to="param.createTimeTo"
                                 @from="param.createTimeFrom = arguments[0]" @to="param.createTimeTo = arguments[0]"></TimeRangePicker>
              </div>
            </FormGroup>
          </div>
        </div>
      </template>
    </form>

    <SelectSingleManageUserModal v-if="selectUserConfig.show" :config="selectUserConfig" v-model="user"></SelectSingleManageUserModal>
  </div>
</template>

<script>
import QueryConditionMixin from '@mixins/QueryConditionMixin'
import SelectSingleManageUserModal from '../../user/modal/SelectSingleManageUserModal.vue'

export default {
  mixins: [QueryConditionMixin],
  components: {
    SelectSingleManageUserModal
  },
  data: function () {
    return {
      param: {
        isFail: '',
        userId: '',
        httpMethod: '',
        httpUri: '',
        className: '',
        executeTime: null,
        createTimeFrom: null,
        createTimeTo: null
      },
      selectUserConfig: {
        show: false,
        title: '选择负责人'
      },
      user: {}
    }
  },
  watch: {
    'user': {
      handler: function () {
        this.param.userId = this.user.userId
      },
      deep: true
    }
  }
}
</script>
