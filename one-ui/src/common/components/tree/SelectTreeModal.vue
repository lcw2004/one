<template>
<transition name="zoom">
  <div class="modal" v-show="config.show" style="display: block">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="config.show = false">
            <span aria-hidden="true">×</span>
          </button>
          <h4 class="modal-title" v-text="config.title"></h4>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-md-12 modal-scrollable">
              <Tree :element="topElement" v-model="value" select-type="radio"></Tree>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal" @click="config.show = false">取消</button>
          <button type="button" class="btn btn-primary" @click="ok()">确认</button>
        </div>
      </div>
    </div>
  </div>
</transition>
</template>

<script>
export default {
  props: {
    config: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      topElement: {},
      value: {}
    }
  },
  mounted () {
    this.loadTree()
  },
  methods: {
    ok () {
      this.config.show = false
      let value = this.value
      value.childList = null
      this.$emit('input', value)
    }
  }
}
</script>
