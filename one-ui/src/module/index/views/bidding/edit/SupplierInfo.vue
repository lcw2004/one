<template>
<div class="box-body">
  <div class="row" >
    <div class="col-md-12">
      <button type="button" class="btn btn-info btn-sm pull-right" @click="config.show = true">
        <i class="fa fa-plus"></i>  添加投标单位
      </button>
    </div>
  </div>
  <div class="row" >
    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <tbody>
          <tr>
            <th style="width: 10px">#</th>
            <th>公司名称</th>
            <th>联系人</th>
            <th>手机号码</th>
            <th>操作</th>
          </tr>
          <tr>
            <td>1</td>
            <td>Update software</td>
            <td>张三</td>
            <td>12345678901</td>
            <td>
              <a>删除</a>
            </td>
          </tr>
          <tr>
            <td>2</td>
            <td>Clean database</td>
            <td>李四</td>
            <td>12345678901</td>
            <td>
              <a>删除</a>
            </td>
          </tr>
          <tr>
            <td>3</td>
            <td>Cron job running</td>
            <td>王五</td>
            <td>12345678901</td>
            <td>
              <a>删除</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

  <SelectSupplier :config="config"></SelectSupplier>
</div>
</template>

<script>
import SelectSupplier from '../../modal/SelectSupplier'
export default {
  components: {
    SelectSupplier
  },
  data: function () {
    return {
      config: {
        show: false,
        title: '选择投标单位'
      }
    }
  }
}
</script>
