<template>
<section class="content">
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">抽取专家条件</h3>
        </div>

        <div class="box-body">
          <form class="form-horizontal">
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-4">项目编号</label>
                  <div class="col-md-7">
                    <a><p class="form-control-static">1111111</p></a>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">拟抽取人数</label>
                  <div class="col-md-7">
                    <p class="form-control-static">5</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="control-label col-md-2">专家回避要求</label>
                  <div class="col-md-9">
                    <Editor height="80px"></Editor>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="control-label col-md-2">专业要求</label>
                  <div class="col-md-9">
                    <table class="table table-bordered table-hover">
                      <tbody>
                        <tr>
                          <th style="width: 10px">#</th>
                          <th>专业</th>
                          <th>要求</th>
                          <th>人数</th>
                        </tr>
                        <tr>
                          <td>1</td>
                          <td>001</td>
                          <td>精通什么什么</td>
                          <td>3</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>001</td>
                          <td>xxx</td>
                          <td>2</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header with-border">
          <div class="row">
            <div class="col-md-9">
              <h3 class="box-title">抽取专家</h3>
            </div>
            <div class="col-md-3">
              <button type="submit" class="btn btn-primary">自动抽取</button>
              <button type="submit" class="btn btn-primary" @click="extract">手动抽取</button>
            </div>
          </div>
        </div>

        <div class="box-body">
            <div class="row">
              <div class="col-md-12">
                <table class="table table-bordered table-hover">
                  <tbody>
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>姓名</th>
                      <th>账号</th>
                      <th>手机</th>
                      <th>类别</th>
                      <th>抽取时间</th>
                      <th>抽取方式</th>
                      <th>操作</th>
                    </tr>
                    <tr>
                      <td>1</td>
                      <td>张三</td>
                      <td>001</td>
                      <td>12345678901</td>
                      <td></td>
                      <td>2017-01-01 12:00:00</td>
                      <td>自动</td>
                      <td><a>删除</a></td>
                    </tr>
                    <tr>
                      <td>2</td>
                      <td>李四</td>
                      <td>002</td>
                      <td>12345678901</td>
                      <td></td>
                      <td>2017-01-01 12:00:00</td>
                      <td>自动</td>
                      <td><a>删除</a></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
        </div>
      </div>
    </div>
  </div>

  <SelectExpertModal :config="config"></SelectExpertModal>
</section>
</template>

<script>
import SelectExpertModal from './SelectExpertModal'

export default {
  components: {
    SelectExpertModal
  },
  data: function () {
    return {
      config: {
        show: false,
        title: '选择专家'
      }
    }
  },
  methods: {
    extract () {
      this.config.show = true
    }
  }
}
</script>
