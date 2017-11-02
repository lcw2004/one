<template>
  <span>
    <template v-if="!isHideMore">
      {{ text }}
    </template>
    <template v-if="isHideMore">
      <PopoverContainer :title="title">
        <span slot="outer">{{ text | limitLength(maxLength) }}</span>
        <div class="row">
          <div class="col-md-12">
            {{ text }}
          </div>
        </div>
      </PopoverContainer>
    </template>
  </span>
</template>

<script>
  export default {
    name: 'LongText',
    props: {
      length: {
        default: '15'
      },
      title: {
        type: String,
        default: '内容'
      },
      text: {
        type: String,
        required: false
      }
    },
    data: function () {
      return {}
    },
    computed: {
      maxLength: function () {
        return Number.parseInt(this.length)
      },
      isHideMore: function () {
        if (!this.text) {
          return false
        }
        return this.text.length > this.maxLength
      }
    }
  }
</script>
