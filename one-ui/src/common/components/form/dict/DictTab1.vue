<template>
  <FormControllStatic>
    <div class="row">
      <div class="col-md-tag" @click="dictValue = ''" v-if="showEmptyValue">
        <div class="one-tag-item" :class="dictValue == '' ? 'active' : ''">全部</div>
      </div>
      <template v-if="!showMore">
        <template v-for="(dict, index) of dictList">
          <div class="col-md-tag" @click="dictValue = dict.value" v-if="index < 6">
            <div class="one-tag-item" :class="dictValue == dict.value ? 'active' : ''">{{ dict.label }}</div>
          </div>
        </template>
        <div class="col-md-tag" v-if="dictList.length > 7" @click="showMore = !showMore">
          <div class="one-tag-item-more"><a>更多</a></div>
        </div>
      </template>
      <template v-if="showMore">
        <template v-for="(dict, index) of dictList">
          <div class="col-md-tag" @click="dictValue = dict.value">
            <div class="one-tag-item" :class="dictValue == dict.value ? 'active' : ''">{{ dict.label }}</div>
          </div>
        </template>
        <div class="col-md-tag" v-if="dictList.length > 7" @click="showMore = !showMore">
          <div class="one-tag-item-more"><a>收起</a></div>
        </div>
      </template>
    </div>
  </FormControllStatic>
</template>

<script>
export default {
  name: 'DictTab1',
  data: function () {
    return {
      showMore: false
    }
  },
  props: {
    showEmptyValue: {
      type: Boolean,
      default: false
    },
    data: {
      type: Array,
      required: true
    },
    value: {}
  },
  computed: {
    dictValue: {
      get: function () {
        return this.value === null || this.value === undefined ? '' : this.value + ''
      },
      set: function (newValue) {
        this.$emit('input', newValue)
      }
    },
    dictList: function () {
      return this.data
    }
  },
  methods: {
    onChange () {
      this.$emit('change')
    }
  }
}
</script>
