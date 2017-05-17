<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <form class="form-inline">
              <div class="col-md-3"><label class="control-label">项目</label> <input type="text" placeholder="项目编号/项目名称" class="form-control inline-block"></div>
            </form>
          </div>

          <div class="box-body">
            <table class="table table-bordered table-hover">
              <tbody>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>项目编号</th>
                  <th>项目名称</th>
                  <th>项目类型</th>
                  <th>发布日期</th>
                  <th>开标时间</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
                <tr>
                  <td>1</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试1</td>
                  <td>邀请招标</td>
                  <td>2017-01-16</td>
                  <td>2017-03-16 12:00:00</td>
                  <td>编辑中
                    <span class="label label-warning">审核驳回</span>
                  </td>
                  <td>
                    <a><router-link to='/bidding/create'>编辑</router-link></a>
                    <a>删除</a>
                  </td>
                </tr>
                <tr>
                  <td>2</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试2</td>
                  <td>邀请招标</td>
                  <td>2017-01-16</td>
                  <td>2017-03-16 12:00:00</td>
                  <td>已提交审核
                    <span class="label label-default">待审核</span>
                    <!-- <span class="label label-success">审核通过</span> -->
                    <!-- <span class="label label-warning">审核驳回</span> -->
                  </td>
                  <td>
                    <a><router-link to='/bidding/audit-progress'>审核进度</router-link></a>
                  </td>
                </tr>
                <tr>
                  <td>3</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试3</td>
                  <td>邀请招标</td>
                  <td>2017-01-16</td>
                  <td>2017-03-16 12:00:00</td>
                  <td>审核通过，等待开标</td>
                  <td>
                    <a><router-link to='/bidding/clarify'>澄清</router-link></a>
                    <a>负责人签到（开标时间当天显示）</a>
                  </td>
                </tr>
                <tr>
                  <td>4</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试4</td>
                  <td>邀请招标</td>
                  <td>2017-01-16</td>
                  <td>2017-03-16 12:00:00</td>
                  <td>开标中</td>
                  <td>
                    <a><router-link to='/bidding/scene'>开标现场</router-link></a>
                  </td>
                </tr>
                <tr>
                  <td>5</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试5</td>
                  <td>邀请招标</td>
                  <td>2017-01-16</td>
                  <td></td>
                  <td>开标结束，预售标
                    <span class="label label-default">待审核</span>
                  </td>
                  <td>
                    <a><router-link to='/bidding/pre-award'>预授标</router-link></a>
                  </td>
                </tr>
                <tr>
                  <td>6</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试5</td>
                  <td>邀请招标</td>
                  <td>2017-01-16</td>
                  <td></td>
                  <td>授标结束，待归档</td>
                  <td>
                    <a @click="test">归档</a>
                  </td>
                </tr>
                <tr>
                  <td>7</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试5</td>
                  <td>邀请招标</td>
                  <td>2017-01-16</td>
                  <td></td>
                  <td>已归档</td>
                  <td>
                    <a><router-link to='/bidding/info'>查看</router-link></a>
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
              <li></li>
            </ol>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  components: {
  },
  data: function () {
    return {
    }
  },
  methods: {
    test: function () {
      this.$alert('123123')
    }
  }
}
</script>
