<template>
  <li>
    <i :class="iconClass"></i>
    <div class="timeline-item">
      <span class="time">
        <a @click="showDetail = !showDetail">{{ explandText }} <i :class="expandClass"></i></a>
        <i class="fa fa-clock-o" v-if="time"></i> {{ time }}
      </span>

      <!-- timeline-header -->
      <h3 :class="headerClass">
        <slot name="header"></slot>
      </h3>

      <!-- timeline-body -->
      <div class="timeline-body" v-show="showDetail">
        <slot name="body"></slot>
      </div>

      <!-- timeline-footer -->
      <div class="timeline-footer">
        <slot name="footer"></slot>
      </div>
    </div>
  </li>
</template>

<script>
  export default {
    name: 'TimeItem',
    props: {
      icon: {
        type: String,
        required: true
      },
      iconColor: {
        default: 'bg-gray'
      },
      time: {
        type: String,
        required: false
      }
    },
    data: () => {
      return {
        showDetail: true
      }
    },
    computed: {
      explandText: function () {
        return this.showDetail ? '收起' : '展开'
      },
      iconClass: function () {
        return [this.icon, this.iconColor]
      },
      expandClass: function () {
        if (this.showDetail) {
          return ['fa fa-fw fa-caret-up']
        } else {
          return ['fa fa-fw fa-caret-down']
        }
      },
      headerClass: function () {
        return {
          'timeline-header': true,
          'no-border': !this.showDetail
        }
      }
    }
  }
</script>

<style>
  .time > a {
    margin-right: 20px
  }
</style>
