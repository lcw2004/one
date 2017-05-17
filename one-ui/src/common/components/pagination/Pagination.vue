<template>
<div class='row'>
  <div class='col-md-6'>
    <ul class='pagination'>
      <li :class="{'disabled' : pageNo <= 1}">
        <a @click='goLastPage'>上一页</a>
      </li>
      <li v-for='showPageNo of showPageNos' :class="{'active': showPageNo == page.pageNo}">
        <a @click='pageNo = showPageNo'>{{ showPageNo }}</a>
      </li>
      <li :class="{'disabled' : pageNo >= pageCount}">
        <a @click='goNextPage'>下一页</a>
      </li>
    </ul>
  </div>
  <div class='col-md-6'>
    <ul class='pagination pull-right navbar-static-top'>
      <li>
        共 {{ count }} 条，每页
        <label>
          <select class="form-control input-sm" v-model="pageSize">
            <option value="10">10</option>
            <option value="25">25</option>
            <option value="50">50</option>
            <option value="100">100</option>
          </select>
        </label>
        条
      </li>
    </ul>
  </div>
</div>
</template>

<script>
export default {
  name: 'Pagination',
  props: {
    page: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      offset: 3
    }
  },
  computed: {
    pageNo: {
      get: function () {
        let pageNo = 1
        if (this.page.pageNo) {
          pageNo = this.page.pageNo
        }
        return pageNo
      },
      set: function (newValue) {
        this.$emit('page', newValue, this.pageSize)
      }
    },
    pageSize: {
      get: function () {
        let pageSize = 10
        if (this.page.pageSize) {
          pageSize = this.page.pageSize
        }
        return pageSize
      },
      set: function (newValue) {
        // 切换每页显示条数的时候，清空页码
        this.$emit('page', 1, newValue)
      }
    },
    count: function () {
      let count = 0
      if (this.page.count) {
        count = this.page.count
      }
      return count
    },
    pageCount: function () {
      let pageCount = 0
      if (this.page.pageCount) {
        pageCount = this.page.pageCount
      }
      return pageCount
    },
    lastPageNo: function () {
      // 上一页
      if (this.page.pageNo === 1) {
        return 1
      } else {
        return this.page.pageNo - 1
      }
    },
    nextPageNo: function () {
      // 下一页
      if (this.page.pageNo < this.page.pageCount) {
        return this.page.pageNo + 1
      } else {
        return this.page.pageCount
      }
    },
    showPageNos: function () {
      var from = this.page.pageNo - this.offset
      if (from < 1) {
        from = 1
      }

      var to = from + (this.offset * 2)
      if (to >= this.page.pageCount) {
        to = this.page.pageCount
      }

      var showPageNos = []
      for (var i = from; i <= to; i++) {
        showPageNos.push(i)
      }
      return showPageNos
    }
  },
  methods: {
    goLastPage () {
      if (this.pageNo <= 1) {
        return
      } else {
        this.pageNo = this.lastPageNo
      }
    },
    goNextPage () {
      if (this.pageNo >= this.pageCount) {
        return
      } else {
        this.pageNo = this.nextPageNo
      }
    }
  }
}
</script>
