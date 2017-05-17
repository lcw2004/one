<template>
<section class="content">
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">申请抽取专家</h3>
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
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-4">拟抽取人数</label>
                  <div class="col-md-7">
                    <input type="text" class="form-control">
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
                          <th>操作</th>
                        </tr>
                        <tr>
                          <td>1</td>
                          <td>001</td>
                          <td>精通什么什么</td>
                          <td>3</td>
                          <td>
                            <a>删除</a> | <a>编辑</a>
                          </td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>001</td>
                          <td>xxx</td>
                          <td>2</td>
                          <td>
                            <a>删除</a> | <a>编辑</a>
                          </td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>
                            <button type="button" class="btn btn-info btn-sm pull-left" @click="config.show = true">
                              选择专业
                            </button
                          </td>
                          <td>
                            <input type="text" class="form-control input-sm">
                          </td>
                          <td>
                            <input type="text" class="form-control input-sm" style="width: 80px">
                          </td>
                          <td>
                            <a>删除</a> | <a>编辑</a>
                          </td>
                        </tr>
                        <tr>
                          <td colspan="5"><FullButton></FullButton></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>

        <div class="box-footer">
          <div class="col-md-2 col-sm-0"></div>
          <div class="col-md-2 col-sm-2">
            <button type="submit" class="btn btn-block btn-primary">保存</button>
          </div>
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
