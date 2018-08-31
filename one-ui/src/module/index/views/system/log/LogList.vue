<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <LogQueryCondition v-model="param"></LogQueryCondition>
        <!--Query End-->

        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>操作用户</th>
                  <th>HTTP方法</th>
                  <th>URI</th>
                  <th>操作时间</th>
                  <th>操作IP</th>
                  <th>执行耗时</th>
                  <th>是否异常</th>
                  <th>执行类</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="obj of page.list">
                  <td><template v-if="obj.userInfo">{{ obj.userInfo.name }}</template></td>
                  <td>{{ obj.httpMethod }}</td>
                  <td>{{ obj.httpUri | limitLength(20) }}</td>
                  <td>{{ obj.createTime }}</td>
                  <td>{{ obj.httpRemoteHost }}</td>
                  <td>{{ obj.executeTime }}</td>
                  <td>
                    <span class="label label-warning" v-if="obj.isFail == 1">异常</span>
                    <span class="label label-default" v-else>正常</span>
                  </td>
                  <td>{{ simplifyClassName(obj.className) | limitLength(20) }}</td>
                  <td>
                    <router-link :to='"/system/log/" + obj.logId + "/view"'>查看</router-link>
                  </td>
                </tr>
              </tbody>
            </table>
            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import PageMixin from '@mixins/PageMixin'
import LogQueryCondition from './LogQueryCondition.vue'

export default {
  mixins: [PageMixin],
  components: {
    LogQueryCondition
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/sys/log'}
      },
      param: {
      }
    }
  },
  methods: {
    simplifyClassName (className) {
      if (className.length < 20) {
        return className
      } else {
        return className.substring(className.lastIndexOf('.') + 1)
      }
    }
  }
}
</script>
