<template>
  <div>
    <div class="row" v-for="chapter of notice.chapterList">
      <div class="col-md-12">
        <section>
          <h4 class="page-header">{{ chapter.title }}</h4>
          <Editor :toolbar="[['Format']]" v-model="chapter.content"></Editor>
        </section>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data: function () {
    return {}
  },
  computed: {
    notice: function () {
      return this.$store.state.data.notice
    }
  }
}
</script>
