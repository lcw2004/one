<template>
  <div class="row">
    <div class="col-md-8">
      <SupplierList></SupplierList>
    </div>
    <div class="col-md-4">
      <div class="row">
        <div class="col-md-12">
          <ProjectInfo></ProjectInfo>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <Chat></Chat>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Chat from './chat/Chat'
import ProjectInfo from './ProjectInfo'
import SupplierList from './SupplierList'

export default {
  components: {
    Chat,
    ProjectInfo,
    SupplierList
  },
  data: function () {
    return {
    }
  }
}
</script>
