<template>
  <section class="content" :id="id">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header with-border">
            <h3 class="box-title">某某招标项目（ZB201701150003）</h3>
            <div class="box-tools pull-right">
              <FullScreenBtn :id="id"></FullScreenBtn>
            </div>
          </div>
          <div class="box-body">
            <BiddingQuotes></BiddingQuotes>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6">
        <ChooseSupplier @selected="supplierSelected = arguments[0]"></ChooseSupplier>
      </div>
      <div class="col-md-6">
        <PreAdwardSupplier :supplier="supplierSelected"></PreAdwardSupplier>
      </div>
    </div>


    <div class="row">
      <div class="col-md-6">
        <ExpertSuggestion></ExpertSuggestion>
      </div>
    </div>
  </section>
</template>

<script>
import BiddingQuotes from '../scence/quotes/BiddingQuotes'
import ExpertSuggestion from './ExpertSuggestion'
import ChooseSupplier from './ChooseSupplier'
import PreAdwardSupplier from './PreAdwardSupplier'

export default {
  components: {
    BiddingQuotes,
    ExpertSuggestion,
    ChooseSupplier,
    PreAdwardSupplier
  },
  data () {
    return {
      id: Math.random() + '',
      supplierSelected: {}
    }
  }
}
</script>
