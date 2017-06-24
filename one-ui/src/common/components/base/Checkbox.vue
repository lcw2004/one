<template>
  <input type="checkbox" :value="data" v-model="ckboxValue">
</template>

<script>
  import $ from 'jquery'

  export default {
    name: 'Checkbox',
    props: {
      data: {},
      value: {}
    },
    beforeMounted () {
      let self = this
      $(self.el).iCheck({
        checkboxClass: 'icheckbox_flat-blue',
        radioClass: 'iradio_flat-blue',
        increaseArea: '20%' // optional
      })
      $(self.el).on('check', function (event) {
        self.addSelf()
      })
      $(self.el).on('uncheck', function (event) {
        self.removeSelf()
      })
    },
    data: () => {
      return {
        ckboxValue: ''
      }
    },
    methods: {
      indexOf () {
        let index = -1
        for (let i = 0; i < this.copyValue.length; i++) {
          let v = this.copyValue[i]
          if (v === this.ckboxValue) {
            index = i
          }
        }
        return index
      },
      removeSelf () {

      },
      addSelf () {
        if (this.indexOf() <= 0) {
          this.copyValue.push(this.ckboxValue)
        }
      }
    },
    watch: {
      'ckboxValue': function () {
        this.$emit('ckboxValue')
      }
    },
    computed: {
      copyValue: {
        get: function () {
          return this.value
        }
      }
    }
  }
</script>
