<template>
  <div class="picture" @mouseover="showAction = true" @mouseout="showAction = false">
    <img :src="imageUrl" style="width: 165px">
    <span class="picture-action" v-show="showAction">
      <i class="el-icon-zoom-in" @click="preview()"></i>
      <i class="el-icon-delete" @click="remove()" v-if="editable"></i>
    </span>
  </div>
</template>

<script>
export default {
  name: 'OnePicture',
  props: {
    fileId: {
      type: String,
      require: true
    },
    editable: {
      value: Boolean,
      default: true
    }
  },
  computed: {
    imageUrl: function () {
      return '/api/sys/file/' + this.fileId + '/download'
    }
  },
  data: function () {
    return {
      showAction: false
    }
  },
  methods: {
    remove () {
      this.$emit('remove')
    },
    preview () {
      this.$preview.openImg(this.imageUrl)
    }
  }
}
</script>
