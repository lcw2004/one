<template>
  <OneTransition name="fade">
    <div class="tooltip right" :style="tooltipStyle" :id="id">
      <div class="tooltip-arrow" :style="tooltipArrowStyle"></div>
      <div class="tooltip-inner" :style="tooltipInnerStyle">
        <slot></slot>
      </div>
    </div>
  </OneTransition>
</template>

<script>
  export default {
    name: 'Tooltip',
    props: {
      width: {
        type: Number,
        default: 300
      },
      backgroundColor: {
        type: String,
        default: '#3c8dbc'
      }
    },
    data: function () {
      return {
        id: '' + Math.random(),
        containerHeigth: 0
      }
    },
    mounted () {
      this.containerHeigth = document.getElementById(this.id).offsetHeight
    },
    computed: {
      iconClass: function () {
        return ['fa', 'fa-fw', this.isShow ? 'fa-angle-down' : 'fa-angle-right']
      },
      tooltipStyle: function () {
        return {
          'margin-left': '0px',
          'top': this.top + 'px',
          'display': 'inline',
          'opacity': 0.9
        }
      },
      tooltipArrowStyle: function () {
        return {
          'border-right-color': this.backgroundColor
        }
      },
      tooltipInnerStyle: function () {
        return {
          'background-color': this.backgroundColor,
          'width': this.width + 'px'
        }
      },
      top: function () {
        return (0 - this.containerHeigth + 28) / 2
      }
    }
  }
</script>
