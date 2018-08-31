<template>
  <pre><code class="language-java" v-html="codeHighlight"></code></pre>
</template>

<script>
import Prism from 'prismjs'
require('prismjs/components/prism-java')
require('prismjs/themes/prism.css')

export default {
  name: 'CodeView',
  props: {
    code: {
      type: String,
      required: true
    },
    language: {
      type: String,
      default: 'java'
    }
  },
  computed: {
    codeHighlight: function () {
      if (this.code) {
        return Prism.highlight(this.code, Prism.languages.java)
      } else {
        return ''
      }
    }
  }
}
</script>
