<template>
<section class="content">
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header">
          <div class="row">
            <div class="col-md-12">
              <form class="form-inline">
                <div class="col-md-10">
                  <input type="text" class="form-control" value="模板1" placeholder="模板名称">
                </div>
                <div class="col-md-1">
                  <button type="button" class="btn btn-info pull-right">
                    保存
                  </button>
                </div>
                <div class="col-md-1">
                  <button type="button" onclick="history.go(-1)"class="btn btn-default pull-right">
                    返回
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <div class="box-body">
          <div class="row">
            <div class="col-md-3">
              <ul class="nav nav-pills nav-stacked">
                <li :class="{active : chapter.id == activeChapter.id}" v-for="chapter of notice.chapterList">
                  <a @click="activeChapter = chapter">
                    {{ chapter.title }}
                    <span v-if="chapter.id == activeChapter.id">
                      <i class="fa fa-fw fa-remove pull-right"></i>
                    </span>
                  </a>
                </li>
                <div style="margin-top:10px">
                  <FullButton></FullButton>
                </div>
              </ul>
            </div>
            <div class="col-md-9">
              <form class="form-horizontal">
                <div class="row">
                  <div class="col-md-12">
                    <div class="form-group">
                      <div class="col-md-4">
                        <input type="text" class="form-control" placeholder="章节标题" v-model="activeChapter.title">
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-12">
                    <div class="form-group">
                      <div class="col-md-12">
                        <Editor :toolbar="[['Format']]" height="300px" v-model="activeChapter.content"></Editor>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</template>

<script>
import NoticeChapterInfo from './NoticeChapterInfo'

export default {
  components: {
    NoticeChapterInfo
  },
  data: function () {
    return {
      activeChapter: {}
    }
  },
  mounted: function () {
    if (!this.activeChapter.title) {
      this.activeChapter = this.$store.state.data.notice.chapterList[0]
    }
  },
  computed: {
    notice: function () {
      return this.$store.state.data.notice
    }
  }
}
</script>
