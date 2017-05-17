<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <form class="form-inline">
              <div class="col-md-3"><input type="text" placeholder="编号/名称" class="form-control inline-block"></div>
            </form>
          </div>

          <div class="box-body">
            <table class="table table-bordered table-hover">
              <tbody>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>编号</th>
                  <th>名称</th>
                  <th>地点</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
                <tr>
                  <td>1</td>
                  <td>107</td>
                  <td>107</td>
                  <td>1栋107</td>
                  <td>
                    <span class="label label-warning">使用中</span>
                  </td>
                  <td>
                    <a>删除</a>
                  </td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>108</td>
                  <td>108</td>
                  <td>1栋108</td>
                  <td>
                    <span class="label label-success">空闲</span>
                  </td>
                  <td>
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
              <li>后期规划
                <ol>
                  <li>可以根据项目里面配置的招标室来反查这个房间的使用情况（日期视图）</li>
                  <li>可以根据项目规划自动分配招标室</li>
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
  }
}
</script>
