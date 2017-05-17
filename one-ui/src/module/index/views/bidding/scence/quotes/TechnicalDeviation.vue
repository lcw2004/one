<template>
<section class="content">
  <div class="row">
    <div class="col-md-6">
      <h4>某某供应商技术偏离列表</h4>
    </div>
    <div class="col-md-6">
      <div class=" pull-right">
        <button type="button" class="btn btn-info" @click="$router.go(-1)">
          返回
        </button>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <tbody>
          <tr>
            <th style="width: 10px">#</th>
            <th>招标文件条目</th>
            <th>物料名称</th>
            <th>偏离</th>
            <th>说明</th>
          </tr>
          <tr>
            <td>1</td>
            <td>1.1</td>
            <td>xxxxxx</td>
            <td>xxxx</td>
            <td>xxxxx</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</section>
</template>

<script>
export default {}
</script>
