<template>
<section class="content">
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header">
          <h3 class="box-title">李四：关于某某项目的疑问</h3>
        </div>
        <div class="box-body">
          <form class="form-horizontal">
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="col-md-1 control-label">答复</label>
                  <div class="col-md-10">
                    <Editor></Editor>
                    <input type="checkbox" name="my-checkbox" checked>
                  </div>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="col-md-1 control-label">附件</label>
                  <div class="col-md-10">
                    <p class="form-control-static"><a><i class="fa fa-paperclip"></i> 下载附件</a></p>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>

        <div class="box-footer">
          <div class="col-md-2 col-sm-0"></div>
          <div class="col-md-2 col-sm-2"><button class="btn btn-block btn-default" @click="$router.go(-1)">返回</button></div>
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
  props: {
  },
  mounted () {
  },
  data: function () {
    return {}
  },
  methods: {
    select: function () {

    }
  }
}
</script>
