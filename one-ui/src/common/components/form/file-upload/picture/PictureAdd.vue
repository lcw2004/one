<template>
  <div class="picture-add" @click="selectFile()">
    <i class="el-icon-plus"></i>
    <input type="file" style="display: none" :id="id" @change="uploadFile($event)">
  </div>
</template>

<script>
import { uuid } from '@utils/common'

export default {
  components: {
  },
  data: function () {
    return {
      id: uuid()
    }
  },
  methods: {
    /**
     * 选择文件
     */
    selectFile () {
      document.getElementById(this.id).click()
      this.progressWidth = 0
    },

    /**
     * 文件路径变更
     * @param event
     */
    uploadFile (event) {
      let file = event.target.files[0]
      if (file) {
        this.$emit('start', event, file)
      }
    }
  }
}
</script>
