<template>
  <div class="tab-content">
    <div class="tab-pane active">
      <div class="row">
        <div class="col-md-12">
          <form class="form-inline">
            <div class="col-md-6">
            </div>
            <div class="col-md-6">
            </div>
          </form>
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
                <td><span class="label label-warning">未回答</span></td>
                <td>
                  <a><router-link to="/bidding/clarify/question-reply">查看并回答</router-link></a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
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
