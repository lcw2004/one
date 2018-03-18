<template>
  <OneModal :title="config.title" :show.sync="config.show" size="large">
    <img :src="imageUrl" style="width: 100%; height: 100%">
  </OneModal>
</template>

<script>
import ModalMixin from '@mixins/ModalSimpleMixin.js'

export default {
  mixins: [ModalMixin],
  props: {
    imageUrl: {
      type: String,
      required: true
    }
  }
}
</script>
