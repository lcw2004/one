<template>
  <div class="row">
    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <tbody>
        <tr>
          <th style="width: 10px">#</th>
          <th>资质类型</th>
          <th>颁发机构</th>
          <th>颁发日期</th>
          <th>有效日期</th>
          <th>影印件</th>
          <th>备注</th>
        </tr>
        <tr v-for="(q, index) of obj.qualificationList">
          <td>{{ index + 1}}</td>
          <td>{{ q.type.qualificationName }}</td>
          <td>{{ q.issueAgency }}</td>
          <td>{{ q.issueDate }}</td>
          <td>{{ q.invalidDate }}</td>
          <td>
            <ImageView v-if="q.fileId" :src="'/api/sys/file/' + q.fileId + '/download'"></ImageView>
          </td>
          <td><LongText :text="q.remark"></LongText></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  export default {
    props: {
      obj: {
        type: Object,
        required: true
      }
    },
    data: () => {
      return {
        qualifications: []
      }
    }
  }
</script>
