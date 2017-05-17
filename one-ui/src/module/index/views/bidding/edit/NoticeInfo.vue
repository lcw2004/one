<template>
<div class="box-body">
  <div class="row">
    <div class="col-md-6">
      <div class="form-group">
        <label class="control-label col-md-3">投标须知模板</label>
        <div class="col-md-7">
          <select class="form-control">
            <option>模板 1</option>
            <option>模板 2</option>
            <option>模板 3</option>
            <option>模板 4</option>
            <option>模板 5</option>
          </select>
        </div>
      </div>
    </div>
  </div>

  <NoticeChapterInfo></NoticeChapterInfo>
</div>
</template>

<script>
import NoticeChapterInfo from '../../system/notice/NoticeChapterInfo'

export default {
  components: {
    NoticeChapterInfo
  },
  data: function () {
    return {
    }
  },
  computed: {
    notice: function () {
      return this.$store.state.data.notice
    }
  }
}
</script>
