export default {
  methods: {
    scrollToError () {
      const errorFirst = this.$el.querySelector('.has-error')
      if (errorFirst) {
        const errorFirstInput = errorFirst.querySelector('input, select')
        if (errorFirstInput) {
          errorFirstInput.focus()
          errorFirstInput.scrollIntoView({behavior: 'smooth'})
        }
      }
    }
  }
}
