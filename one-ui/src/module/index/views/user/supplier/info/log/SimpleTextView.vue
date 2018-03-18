<template>
  <div class="row">
    <div class="col-md-12">
      <div class="callout callout-default" v-if="log.remark">
        <p>
          {{ log.remark }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    log: {
      type: Object,
      required: true
    }
  }
}
</script>
