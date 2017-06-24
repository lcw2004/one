<script>
  import SelectTreeModal from '../../../../../common/components/tree/SelectTreeModal'

  export default {
    mixins: [SelectTreeModal],
    methods: {
      loadTree () {
        let actions = {
          getTree: {method: 'get', url: '/api/sys/menu/tree'}
        }
        let resource = this.$resource(null, {}, actions)
        resource.getTree().then((response) => {
          let result = response.body
          if (result.ok) {
            this.topElement = result.data
          }
        })
      }
    }
  }
</script>
