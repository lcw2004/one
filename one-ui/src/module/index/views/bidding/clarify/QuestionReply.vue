<template>
<section class="content">
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header">
          <h3 class="box-title">李四：关于某某项目的疑问</h3>
        </div>
        <div class="box-body">
          <blockquote>
            <p>关于这个项目我在某些地方有些疑问。。。。</p>
          </blockquote>

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
                    <button type="button" class="btn btn-primary"><i class="fa fa-paperclip"></i> 上传附件</button>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>

        <div class="box-footer">
          <div class="col-md-2 col-sm-0"></div>
          <div class="col-md-2 col-sm-2"><button class="btn btn-block btn-primary">回复</button></div>
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
