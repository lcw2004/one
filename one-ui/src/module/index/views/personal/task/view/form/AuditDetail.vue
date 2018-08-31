<template>
  <div v-if="detailList">
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th style="width: 10px">#</th>
          <th>属性</th>
          <th>修改前</th>
          <th>修改后</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(obj, index) of detailList">
          <td>{{ index + 1 }}</td>
          <td>{{ obj.attributeName }}</td>
          <td>
            <template v-if="obj.attributeType == 4">
              <ImageView v-if="obj.originLabel" :src="'/api/sys/file/' + obj.originLabel + '/download'"></ImageView>
            </template>
            <template v-else>
              {{ obj.originLabel }}
            </template>
          </td>
          <td>
            <template v-if="obj.attributeType == 4">
              <ImageView v-if="obj.targetLabel" :src="'/api/sys/file/' + obj.targetLabel + '/download'"></ImageView>
            </template>
            <template v-else>
              {{ obj.targetLabel }}
            </template>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: {
    auditItemId: {
      type: String,
      required: true
    }
  },
  data: function () {
    return {
      detailList: []
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.listAuditDetail(this.auditItemId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.detailList = result.data
        }
      })
    }
  }
}
</script>
