<template>
  <div class="row">
    <div class="col-md-12">
      <div class="row" >
        <div class="col-md-12">
          <div class="pull-right">
            <button type="button" class="btn btn-info">
              导出Excel
            </button>
            <button type="button" class="btn btn-info" @click="$router.go(-1)">
              返回
            </button>
          </div>
        </div>
      </div>

      <BiddingQuotes></BiddingQuotes>
    </div>
  </div>
</template>

<script>
import BiddingQuotes from './BiddingQuotes'

export default {
  components: {
    BiddingQuotes
  }
}
</script>
