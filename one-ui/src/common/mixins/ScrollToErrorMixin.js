/**
 * 滚动到表单里面有验证错误的地方
 */
export default {
  methods: {
    scrollToError () {
      setTimeout(() => {
        const errorFirst = this.$el.querySelector('.has-error')
        if (errorFirst) {
          const errorFirstInput = errorFirst.querySelector('input, select')
          if (errorFirstInput) {
            errorFirstInput.focus()
            errorFirstInput.scrollIntoView({behavior: 'smooth'})
          } else {
            errorFirst.scrollIntoView({behavior: 'smooth'})
          }
        }
      }, 100)
    }
  }
}
