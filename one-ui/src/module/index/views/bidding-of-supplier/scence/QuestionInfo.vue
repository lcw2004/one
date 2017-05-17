<template>
<section class="content">
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header">
          <h3 class="box-title">提问与回答</h3>
        </div>
        <div class="box-body">
          <div class="row">
            <div class="col-md-12">
              <div class="pull-right">
                <a class="btn btn-default" @click="$router.go(-1)">返回</a>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover">
                <tbody>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>标题</th>
                    <th>供应商</th>
                    <th>提问时间</th>
                    <th>是否回答</th>
                    <th>操作</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td><a><router-link to="/bidding/clarify/form">关于某某项目的疑问</router-link></a></td>
                    <td>李四</td>
                    <td>2017-01-16 00:00:00</td>
                    <td><span class="label label-success">已回答</span></td>
                    <td>
                      <a><router-link to="/bidding/s/question-reply">查看回答</router-link></a>
                    </td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td><a><router-link to="/bidding/clarify/form">关于某某项目的疑问</router-link></a></td>
                    <td>李四</td>
                    <td>2017-01-16 00:00:00</td>
                    <td><span class="label label-warning">未回答</span></td>
                    <td>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="box-footer">

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
