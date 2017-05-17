<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <h3 class="box-title">项目澄清</h3>
          </div>
          <div class="box-body">
            <table class="table table-bordered table-hover">
              <tbody>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>标题</th>
                  <th>发布人</th>
                  <th>发布时间</th>
                  <th>附件</th>
                  <th>阅读次数</th>
                </tr>
                <tr>
                  <td>1</td>
                  <td><a><router-link to="/bidding/clarify/clarify-info">关于某某项目的澄清</router-link></a></td>
                  <td>李四</td>
                  <td>2017-01-16 00:00:00</td>
                  <td>
                    <a><i class="fa fa-paperclip"></i> 附件一（点击下载）</a>
                  </td>
                  <td>5</td>
                </tr>
              </tbody>
            </table>
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
