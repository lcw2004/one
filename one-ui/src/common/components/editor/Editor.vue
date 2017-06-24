<template>
  <textarea :id="id" :value="value"></textarea>
</template>

<script>
  let CKEDITOR = window.CKEDITOR

  export default {
    name: 'Editor',
    props: {
      value: {},
      toolbar: {
        type: Array,
        default: () => [
          ['Format'],
          ['Bold', 'Italic'],
          ['Undo', 'Redo']
        ]
      },
      height: {
        type: [String, Number],
        default: '200px'
      },
      language: {
        type: String,
        default: 'en'
      },
      extraplugins: {
        type: String,
        default: ''
      },
      type: {
        type: String,
        default: 'nomal'
      }
    },
    data: () => {
      return {
        id: Math.random() + ''
      }
    },
    beforeUpdate () {
      const ckeditorId = this.id
      if (this.value !== CKEDITOR.instances[ckeditorId].getData()) {
        CKEDITOR.instances[ckeditorId].setData(this.value)
      }
    },
    mounted () {
      this.init()
    },
    destroyed () {
      const ckeditorId = this.id
      if (CKEDITOR.instances[ckeditorId]) {
        CKEDITOR.instances[ckeditorId].destroy()
      }
    },
    methods: {
      init: function () {
        let self = this
        const ckeditorId = self.id
        const ckeditorConfig = {
          toolbar: self.toolbar,
          language: self.language,
          height: self.height,
          extraPlugins: self.extraplugins
        }

        if (self.type === 'inline') {
          CKEDITOR.inline(ckeditorId, ckeditorConfig)
        } else if (self.type === 'nomal') {
          CKEDITOR.replace(ckeditorId, ckeditorConfig)
        }

        CKEDITOR.instances[ckeditorId].on('change', () => {
          let ckeditorData = CKEDITOR.instances[ckeditorId].getData()
          if (ckeditorData !== self.value) {
            self.$emit('input', ckeditorData)
          }
        })
      },
      getData: function () {
        const data = CKEDITOR.instances[this.id].getData()
        console.log(data)
      }
    }
  }
</script>
