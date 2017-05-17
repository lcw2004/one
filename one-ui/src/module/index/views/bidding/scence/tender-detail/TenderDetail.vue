<template>

  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <BiddingQuotes></BiddingQuotes>
      </div>
    </div>

    <div class="row">
      <div class="col-md-12">
        &nbsp;
      </div>
    </div>

    <div class="row">
      <div class="col-md-4">
        <div class="row">
          <div class="col-md-12">
            <button type="button" class="btn btn-info">
              下载标书文件
            </button>
            <button type="button" class="btn btn-info">
              导出Excel
            </button>
            <button type="button" class="btn btn-info" @click="$router.go(-1)">
              返回
            </button>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="box box-solid">
              <div class="box-header with-border">
                <i class="fa fa-text-width"></i>
                <h3 class="box-title">备注</h3>
              </div>
              <div class="box-body">
                <ol>
                  <li>此处的报价与开标一览表的报价页面类似，直接复用了，不浪费时间写了</li>
                </ol>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-8">
        <ClarifyList></ClarifyList>
      </div>
    </div>
  </section>
</template>

<script>
import ClarifyList from './ClarifyList'
import BiddingQuotes from '../quotes/BiddingQuotes'

export default {
  components: {
    ClarifyList,
    BiddingQuotes
  },
  data: function () {
    return {
    }
  },
  methods: {
  }
}
</script>
