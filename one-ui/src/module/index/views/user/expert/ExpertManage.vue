<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <form class="form-inline">
              <div class="col-md-3"><label class="control-label">姓名</label> <input type="text" placeholder="姓名" class="form-control inline-block">
              </div>
            </form>
          </div>

          <div class="box-body">
            <table class="table table-bordered table-hover">
              <tbody>
              <tr>
                <th style="width: 10px">#</th>
                <th>姓名</th>
                <th>单位</th>
                <th>电话</th>
                <th>状态</th>
                <th>操作</th>
              </tr>
              <tr>
                <td>1</td>
                <td>
                  <router-link to="/expert/info">张三</router-link>
                </td>
                <td>某某公司</td>
                <td>13900000000</td>
                <td>
                  <span class="label label-warning">审核驳回</span>
                </td>
                <td>
                  <a>删除</a>
                </td>
              </tr>
              <tr>
                <td>2</td>
                <td>
                  <router-link to="/expert/info">李四</router-link>
                </td>
                <td>某某公司</td>
                <td>13900000000</td>
                <td>
                  <span class="label label-danger">停用</span>
                </td>
                <td>
                  <a>启用</a>
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
  export default {
    components: {},
    data: () => {
      return {}
    }
  }
</script>
