<template>
  <a v-if="fileId" @click="download">
    <slot></slot>

    <form method="GET" :action="downloadUrl" :id="fileId" style="display: none"></form>
  </a>
</template>

<script>
export default {
  name: 'FileDownloader',
  props: {
    fileId: {
      type: String,
      required: true
    },
    fileName: {
      type: String,
      required: false
    }
  },
  methods: {
    download () {
      this.$el.querySelector('form').submit()
    }
  },
  computed: {
    downloadUrl: function () {
      let url = '/api/sys/file/' + this.fileId + '/download'
      if (this.fileName) {
        url += '?fileName=' + encodeURI(this.fileName)
      }
      return url
    }
  }
}
</script>
