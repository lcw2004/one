<template>
<div class="row">
  <div class="col-md-12">

    <div class="box box-solid">
      <div class="box-header">
        <h3 class="box-title">第 {{index}} 轮澄清
          <span v-if="status == 1" class="label label-default">已结束</span>
          <span v-if="status == 0" class="label label-warning">未结束</span>
        </h3>
        <div class="box-tools pull-right">
          <button type="button" class="btn btn-default btn-sm" data-widget="collapse" @click="show = !show"><i class="fa" :class="{'fa-minus': isShow, 'fa-plus': !isShow}"></i></button>
        </div>
      </div>
      <div class="box-body" v-show="isShow">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-4">
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-5">澄清类型</label>
                    <div class="col-md-7">
                      <p class="form-control-static">公开(含价格澄清)</p>
                    </div>
                  </div>
                </div>
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-5">附件</label>
                    <div class="col-md-7">
                      <p class="form-control-static"><a><i class="fa fa-paperclip"></i> 下载</a></p>
                    </div>
                  </div>
                </div>
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-5">发布时间</label>
                    <div class="col-md-7">
                      <p class="form-control-static">2017-01-01 10:00:00</p>
                    </div>
                  </div>
                </div>
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-5">回应截止时间</label>
                    <div class="col-md-7">
                      <p class="form-control-static">2017-01-01 15:00:00</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-8">
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-2">说明</label>
                    <div class="col-md-9">
                      <Editor :height="100"></Editor>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="pull-right">
                <button type="button" class="btn btn-primary btn-sm" data-widget="remove">修改时间</button>
                <button type="button" class="btn btn-primary btn-sm" data-widget="remove">结束本轮澄清</button>
              </div>
            </div>
          </div>
        </form>

        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <tbody>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>投标单位</th>
                  <th>是否回应</th>
                  <th>回应时间</th>
                  <th>操作</th>
                </tr>
                <tr>
                  <td>1</td>
                  <td>投标单位1</td>
                  <td><span class="label label-default">已回应</span></td>
                  <td>2017-01-01 11:11:11</td>
                  <td>
                  </td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>投标单位2</td>
                  <td><span class="label label-warning">未回应</span></td>
                  <td></td>
                  <td>
                  </td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>投标单位3 </td>
                  <td><span class="label label-default">已回应</span></td>
                  <td>2017-01-01 11:11:11</td>
                  <td>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  props: ['index', 'status'],
  data: function () {
    return {
      show: false
    }
  },
  computed: {
    isShow: function () {
      return this.show || this.status === '0'
    }
  }
}
</script>
