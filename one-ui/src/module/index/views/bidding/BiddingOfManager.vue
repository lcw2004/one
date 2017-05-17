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
                    <a>归档</a>
                    <a @click="reAward">重新授标</a>
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
                    <a><router-link to='/bidding/archives'>查看</router-link></a>
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
              <li>项目有如下几种状态
                <ol>
                  <li>0 - 待提交</li>
                  <li>1 - 已提交审核（提交之后进入审核流程）</li>
                  <li>2 - 审核通过，等待开标（审核通过之后进入发布状态，发布状态可以澄清说明）</li>
                  <li>3 - 开标中（到开标时间之后，进入开标现场，将状态改为开标中，其他用户的状态也改为开标中，可以进入开标现场）</li>
                  <li>4 - 开标结束，预售标</li>
                  <li>5 - 授标结束（定了结果，开始发布中标公告，进入中标公示期）</li>
                  <li>6 - 项目归档（项目归档前还可以重新授标，重新授标需要清空之前的授标数据，记录操作日志）</li>
                  <li>-1 - 流标</li>
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
export default {
  components: {
  },
  data: function () {
    return {
    }
  },
  methods: {
    reAward: function () {
      this.$confirm('确认重新授标吗？确认之后将会清空现在的授标记录', () => {
        this.$notify.warn('操作成功，请重新点击授标按钮进行授标')
      })
    }
  }
}
</script>
