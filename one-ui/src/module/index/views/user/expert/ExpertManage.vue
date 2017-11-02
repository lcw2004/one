<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <ExpertQueryCondition v-model="param"></ExpertQueryCondition>

            <div class="row">
              <div class="col-md-12">
                <div class="pull-right">
                  <router-link to="/user/expert/add" class="btn btn-primary btn-sm">添加专家</router-link>
                </div>
              </div>
            </div>
          </div>

          <div class="box-body">
            <table class="table table-bordered table-hover">
              <thead>
              <tr>
                <th style="width: 10px">#</th>
                <th>姓名</th>
                <th>登录名</th>
                <th>单位</th>
                <th>电话</th>
                <th>状态</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(obj, index) of page.list">
                <td>{{ index + 1}}</td>
                <td>
                  <router-link :to="'/user/expert/' + obj.userId + '/view'">{{ obj.userInfo.name }}</router-link>
                </td>
                <td>{{ obj.userInfo.account }}</td>
                <td>某某公司</td>
                <td>{{ obj.userInfo.userContactInfo.mobile }}</td>
                <td>
                  <UserStatusLabel :status="obj.userInfo.status" :desc="obj.userInfo.statusCn"/>
                </td>
                <td>
                  <router-link :to="'/user/expert/' + obj.userId + '/edit'">修改</router-link>
                  <a>删除</a>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-12">
        <div class="box box-solid">
          <div class="box-header with-border">
            <i class="fa fa-text-width"></i>
            <h3 class="box-title">备注</h3>
          </div>
          <div class="box-body">
            <ol>
              <li>用户有如下几种状态
                <ol>
                  <li>1 - 正常（只有该状态的供应商可以参与投标）</li>
                  <li>2 - 待审核</li>
                  <li>3 - 审核驳回</li>
                  <li>-1 - 已删除</li>
                  <li>-2 - 停用</li>
                </ol>
              </li>
            </ol>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
  import PageMixin from 'mixins/PageMixin.js'
  import UserInfoSimpleView from '../common/UserInfoSimpleView'
  import UserStatusLabel from '../common/UserStatusLabel'
  export default {
    mixins: [PageMixin],
    components: {
      UserInfoSimpleView,
      UserStatusLabel
    },
    data: () => {
      return {
        actions: {
          list: {method: 'get', url: '/api/user/expert'}
        },
        param: {}
      }
    }
  }
</script>
