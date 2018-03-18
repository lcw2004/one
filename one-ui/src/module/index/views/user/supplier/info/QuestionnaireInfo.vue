<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th style="width: 10px">#</th>
              <th>记录人</th>
              <th>调查人</th>
              <th>调查时间</th>
              <th>评论</th>
              <th>附件</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(inquire, index) of inquireList">
              <td>{{ index + 1}}</td>
              <td>{{ inquire.userInfo.name }}</td>
              <td>
                <template v-for="user of inquire.inquireUserList">
                  <span class="label label-info">{{ user.name }}</span>&nbsp;
                </template>
              </td>
              <td>{{ inquire.inquireTime }}</td>
              <td><LongText :text="inquire.remark"></LongText></td>
              <td><FileDownloader :fileId="inquire.fileId">下载</FileDownloader></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  props: {
    obj: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      inquireList: []
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.user.listUserSupplierInquire(this.obj.supplierId).then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.inquireList = result.data
        }
      })
    }
  }
}
</script>
