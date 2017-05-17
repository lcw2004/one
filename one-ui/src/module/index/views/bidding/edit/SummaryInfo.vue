<template>
<div class="box-body">
  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">正式发布时间</label>
        <div class="col-md-7">
          <TimePicker/>
        </div>
      </div>
    </div>
    <div class="col-md-6">
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">购买标书截止时间</label>
        <div class="col-md-7">
          <TimePicker/>
        </div>
      </div>
    </div>
    <div class="col-md-6">
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">投标截止时间</label>
        <div class="col-md-7">
          <TimePicker/>
        </div>
      </div>
    </div>
    <div class="col-md-6">
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">开标时间</label>
        <div class="col-md-7">
          <TimePicker/>
        </div>
      </div>
    </div>
    <div class="col-md-6">
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">评标时间</label>
        <div class="col-md-7">
          <TimePicker/>
        </div>
      </div>
    </div>
    <div class="col-md-6">
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">标书售价</label>
        <div class="col-md-7">
          <div class="input-group">
            <span class="input-group-addon">￥</span>
            <input type="text" class="form-control">
            <span class="input-group-addon">元</span>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6">
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">投标保证金</label>
        <div class="col-md-7">
          <div class="input-group">
            <span class="input-group-addon">￥</span>
            <input type="text" class="form-control">
            <span class="input-group-addon">元</span>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6">
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">开标口令</label>
        <div class="col-md-7">
          <input type="text" class="form-control">
        </div>
      </div>
    </div>
    <div class="col-md-6">
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-4">确认口令</label>
        <div class="col-md-7">
          <input type="text" class="form-control">
        </div>
      </div>
    </div>
    <div class="col-md-6">
    </div>
  </div>
</div>
</template>

<script>
export default {
  components: {}
}
</script>
