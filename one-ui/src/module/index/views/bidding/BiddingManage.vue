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
                  <th>开标</th>
                  <th>状态</th>
                  <th>操作</th>
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
                  </td>
                </tr>
                <tr>
                  <td>5</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试5</td>
                  <td>邀请招标</td>
                  <td>2017-01-16</td>
                  <td>2017-03-16 12:00:00</td>
                  <td>开标结束，预售标</td>
                  <td>
                  </td>
                </tr>
                <tr>
                  <td>6</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试5</td>
                  <td>邀请招标</td>
                  <td>2017-01-16</td>
                  <td>2017-03-16 12:00:00</td>
                  <td>授标结束，待归档</td>
                  <td>
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
            <p class="text-muted">该模块功能与我的招标项目相同，这个地方显示所有的可查看的数据，按钮不再单独列出</p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
}
</script>
