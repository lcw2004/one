/**
 * 分页mixin，主要功能如下：
 *   1. 定义了pageNo\pageSize\page三个属性，其他地方不再需要定义一遍
 *   2. 根据actions注入resource，引用方可以直接调用
 *   3. 分页变化的时候自动调用 queryForPage 方法查询数据
 *   4. 提供 query 接口供引用方调用，该方法会清除分页
 *
 * 使用：
 *   1. 定义actions
 *   2. 定义param，如果没有则不需要定义
 *   3. 引入分页组件：<Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
 */
import axios from 'axios'
import { fillUrlTemplate } from '@utils/common'

let PageMixin = {

  mounted: function () {
    // 设置了loadAfterMounted属性，并且为false的时候不加载数据
    if (this.param.loadAfterMounted !== undefined && this.param.loadAfterMounted === false) {
      // ignore
    } else {
      this.queryForPage()
    }
  },

  data: function () {
    return {
      pageNo: 1,
      pageSize: 10,
      page: {}
    }
  },

  methods: {
    /**
     * 将传入的分页数据和查询条件数据混合为一个对象，并查询数据
     */
    queryByPageAndParam (pageNo, pageSize) {
      let param = this.param
      if (!param) {
        param = {}
      }
      param.pageNo = pageNo
      param.pageSize = pageSize
      axios.get(fillUrlTemplate(this.actions.list.url, param), {params: param}).then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.page = result.data
        }
      })
    },

    /**
     * 切换分页数据的时候调用该接口进行查询，不会清空分页信息
     */
    queryForPage: function () {
      this.queryByPageAndParam(this.pageNo, this.pageSize)
    },

    /**
     * 清空分页信息并查询数据，用于监听param变化并查询数据
     */
    query () {
      this.queryByPageAndParam(1, this.pageSize)
    },

    handlerPage (args) {
      this.pageNo = args[0]
      this.pageSize = args[1]
      this.queryForPage()
    }
  },
  watch: {
    'param': {
      handler: function () {
        this.query()
      },
      deep: true
    }
  }
}

export default PageMixin
