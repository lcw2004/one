<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <table class="table table-bordered table-hover">
          <tbody>
            <tr>
              <th style="width: 10px">#</th>
              <th>调查人</th>
              <th>调查时间</th>
              <th>评论</th>
              <th>附件</th>
            </tr>
            <tr>
              <td>1</td>
              <td>张三</td>
              <td>2017-01-01 11:11:11</td>
              <td></td>
              <td><a>下载</a></td>
            </tr>
            <tr>
              <td>1</td>
              <td>李四</td>
              <td>2017-02-01 11:11:11</td>
              <td></td>
              <td><a>下载</a></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  props: {
    obj: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
    }
  }
}
</script>
