<template>
  <div class="box-body">
    <button type="button" class="btn btn-info btn-sm pull-right" @click="config.show = true">
      <i class="fa fa-plus"></i>  添加标书文件
    </button>
    <table class="table table-bordered table-hover">
      <tbody>
        <tr>
          <th style="width: 10px">#</th>
          <th>类型</th>
          <th>标题</th>
          <th>内容</th>
          <th>文件</th>
          <th>操作</th>
        </tr>
        <tr>
          <td>1</td>
          <td>技术部分电子版</td>
          <td>技术部分标书文件</td>
          <td></td>
          <td>123.pdf</td>
          <td>
            <a>删除</a>
          </td>
        </tr>
        <tr>
          <td>2</td>
          <td>商务部分电子版</td>
          <td>商务部分标书文件</td>
          <td></td>
          <td>456.pdf</td>
          <td>
            <a>删除</a>
          </td>
        </tr>
      </tbody>
    </table>

    <UploadFile :config="config"></UploadFile>
  </div>
</template>

<script>
import UploadFile from '../../modal/UploadFile'

export default {
  components: {
    UploadFile
  },
  data: function () {
    return {
      config: {
        show: false,
        title: '上传标书文件'
      }
    }
  }
}
</script>
