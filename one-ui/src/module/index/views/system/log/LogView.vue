<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">日志详情</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="操作名称">{{ obj.operationName }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="URI">{{ obj.httpMethod }} : {{ obj.httpUri }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="操作用户">{{ obj.userInfo ? obj.userInfo.name : '' }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="执行耗时">{{ obj.executeTime }}ms</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="操作时间">{{ obj.createTime }}</FormGroupStatic>
            </div>
            <div class="col-md-6">
              <FormGroupStatic label="操作IP">{{ obj.httpRemoteHost }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <FormGroupStatic label="执行类" width="2">{{ obj.className }}.{{ obj.methodName }}()</FormGroupStatic>
            </div>
          </div>
          <div class="row" v-if="hasParam">
            <div class="col-md-12">
              <FormGroupStatic label="执行参数" width="2">
                <table class="table table-bordered table-hover">
                  <thead>
                    <tr>
                      <td>#</td>
                      <td>参数</td>
                      <td>参数值</td>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(key, value, index) of params">
                      <td>{{ index + 1 }}</td>
                      <td>{{ value }}</td>
                      <td>{{ key }}</td>
                    </tr>
                  </tbody>
                </table>
              </FormGroupStatic>
            </div>
          </div>
          <div class="row" v-if="obj.remark">
            <div class="col-md-12">
              <FormGroupStatic label="错误信息" width="2">
                <CodeView :code="obj.remark"></CodeView>
              </FormGroupStatic>
            </div>
          </div>
        </form>
      </div>
      <div class="box-footer">
        <div class="row">
          <div class="col-md-2 col-md-offset-2">
            <a class="btn btn-block btn-default" @click="$router.go(-1)">返回</a>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { sortMapByKey } from '@utils/common'

export default {
  data: function () {
    return {
      obj: {
        logId: null,
        userId: null,
        httpMethod: null,
        httpUri: null,
        httpUserAgent: null,
        httpRemoteHost: null,
        httpParamters: null,
        executeTime: null,
        createTime: null,
        className: null,
        methodName: null,
        remark: null,
        userInfo: {}
      }
    }
  },
  computed: {
    params: function () {
      return sortMapByKey(JSON.parse(this.obj.httpParamters))
    },
    hasParam: function () {
      return Object.keys(this.params).length > 0
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.getSysLog(this.$route.params.id).then((response) => {
        const result = response.data
        if (result.ok && result.data) {
          this.obj = result.data
        }
      })
    }
  }
}
</script>
