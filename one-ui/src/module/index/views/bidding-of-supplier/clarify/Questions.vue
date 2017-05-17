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
                <table class="table table-bordered table-hover">
                  <tbody>
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>标题</th>
                      <th>供应商</th>
                      <th>提问时间</th>
                      <th>是否回答</th>
                    </tr>
                    <tr>
                      <td>1</td>
                      <td><a>关于某某项目的疑问</a></td>
                      <td>李四</td>
                      <td>2017-01-16 00:00:00</td>
                      <td><span class="label label-warning">未回答</span></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="box-footer">
            <div class="col-md-2"><button class="btn btn-block btn-default" @click="$router.go(-1)">返回</button></div>
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
