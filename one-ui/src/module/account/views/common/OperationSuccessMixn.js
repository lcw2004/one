import OutContainer from '../common/OutContainer.vue'
export default {
  components: {
    OutContainer
  },
  data: function () {
    return {
      leftTime: 10
    }
  },
  mounted () {
    this.startTimer()
  },
  destroyed () {
    clearInterval(this.interval)
  },
  methods: {
    startTimer () {
      this.interval = setInterval(() => {
        if (this.leftTime > 0) {
          this.leftTime--
        } else {
          clearInterval(this.interval)
        }

        if (this.leftTime === 0) {
          window.location.href = 'login.html'
        }
      }, 1000)
    }
  }
}
