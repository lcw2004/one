<template>
  <a v-if="fileId" @click="download">
    <slot></slot>
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
        window.open(this.downloadUrl)
      }
    },
    computed: {
      downloadUrl: function () {
        let url = '/api/sys/file/' + this.fileId + '/download'
        if (this.fileName) {
          url += '?fileName=' + this.fileName
        }
        return url
      }
    }
  }
</script>
