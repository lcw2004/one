<template>
  <div>
    <div class="row">
      <div class="col-md-12" style="margin-bottom: 5px;">
        由 <span class="label label-primary">{{ info.oldLevel }}</span> 调整为 <span class="label label-primary">{{ info.newLevel }}</span>
      </div>
      <div class="col-md-12">
        <div class="callout callout-default">
          <p>
            {{ info.remark }}
          </p>
        </div>
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
  },
  computed: {
    info: function () {
      return JSON.parse(this.log.remark)
    }
  }
}
</script>
