<template>
  <div class="picture-card">
    <template v-for="(fileId, index) of value">
      <OnePicture :editable="editable" :fileId="fileId" @remove="remove(index)"></OnePicture>
    </template>
    <template v-for="(picture, index) of fileList">
      <PictureProgress v-if="picture.progressWidth < 100" :progress="picture.progressWidth"></PictureProgress>
    </template>
    <PictureAdd v-if="editable" @start="onStartUpload(arguments[0], arguments[1])"></PictureAdd>
  </div>
</template>

<script>
require('./picture.less')
import BatchUploadMixin from '../BatchUploadMixin.js'
import OnePicture from './OnePicture.vue'
import PictureAdd from './PictureAdd.vue'
import PictureProgress from './PictureProgress.vue'

export default {
  name: 'PictureCard',
  mixins: [BatchUploadMixin],
  components: {
    OnePicture,
    PictureAdd,
    PictureProgress
  },
  props: {
    editable: {
      value: Boolean,
      default: true
    }
  }
}
</script>
