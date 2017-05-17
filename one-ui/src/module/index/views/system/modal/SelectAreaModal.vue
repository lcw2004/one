<script>
import SelectTreeModal from '../../../../../common/components/tree/SelectTreeModal'

export default {
  mixins: [SelectTreeModal],
  methods: {
    loadTree () {
      let actions = {
        getTree: {method: 'get', url: '/api/sys/area/tree'}
      }
      let resource = this.$resource(null, {}, actions)
      resource.getTree().then(function (response) {
        let result = response.body
        if (result.ok) {
          this.topElement = result.data
        }
      })
    }
  }
}
</script>
