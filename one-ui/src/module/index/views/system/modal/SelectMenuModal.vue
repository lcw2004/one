<script>
import SelectTreeModal from '@components/form/tree/SelectTreeModal'

export default {
  mixins: [SelectTreeModal],
  data: function () {
    return {
      action: this.$api.system.getMenuTree
    }
  }
}
</script>
